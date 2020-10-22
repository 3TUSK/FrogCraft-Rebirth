/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.advancement;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import frogcraftrebirth.api.FrogAPI;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class PotassiumExplosionTrigger implements ICriterionTrigger<PotassiumExplosionTrigger.Instance> {

	private static final ResourceLocation ID = new ResourceLocation(FrogAPI.MODID, "potassium_explosion");

	private final Map<PlayerAdvancements, Set<Listener<Instance>>> listeners = new HashMap<>();

	@Nonnull
	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancements, Listener<PotassiumExplosionTrigger.Instance> listener) {
		this.listeners.computeIfAbsent(playerAdvancements, ignored -> new HashSet<>()).add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancements, Listener<PotassiumExplosionTrigger.Instance> listener) {
		Set<Listener<PotassiumExplosionTrigger.Instance>> listeners = this.listeners.get(playerAdvancements);

		if (listeners != null) {
			listeners.remove(listener);
			if (listeners.isEmpty()) {
				this.listeners.remove(playerAdvancements);
			}
		}
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
		this.listeners.remove(playerAdvancementsIn);
	}

	@Override
	public PotassiumExplosionTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		ResourceLocation blockIdentifier = new ResourceLocation(JsonUtils.getString(json, "block"));
		return new PotassiumExplosionTrigger.Instance(blockIdentifier);
	}

	public final void trigger(EntityPlayerMP player, IBlockState state) {
		PlayerAdvancements advancements = player.getAdvancements();
		Set<Listener<PotassiumExplosionTrigger.Instance>> listeners = this.listeners.get(advancements);
		if (listeners != null) {
			List<Listener<Instance>> validListenerList = new ArrayList<>();
			for (Listener<PotassiumExplosionTrigger.Instance> listener : listeners) {
				if (listener.getCriterionInstance().test(state)) {
					validListenerList.add(listener);
				}
			}
			for (Listener<PotassiumExplosionTrigger.Instance> listener : validListenerList) {
				listener.grantCriterion(advancements);
			}
		}
	}

	static class Instance extends AbstractCriterionInstance {

		private final Block block;

		Instance(ResourceLocation identifier) {
			super(PotassiumExplosionTrigger.ID);
			this.block = ForgeRegistries.BLOCKS.getValue(identifier);
		}

		boolean test(IBlockState state) {
			return state.getBlock() == this.block;
		}

	}

}
