/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@ParametersAreNonnullByDefault
public final class PotassiumExplosionTrigger implements ICriterionTrigger<PotassiumExplosionTrigger.Instance> {

	private static final ResourceLocation ID = new ResourceLocation(FrogAPI.MODID, "potassium_explosion");

	private final Map<PlayerAdvancements, PotassiumExplosionTrigger.Listeners> listeners = new HashMap<>();

	@Nonnull
	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<PotassiumExplosionTrigger.Instance> listener) {
		this.listeners.computeIfAbsent(playerAdvancementsIn, Listeners::new).add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<PotassiumExplosionTrigger.Instance> listener) {
		Listeners listeners = this.listeners.get(playerAdvancementsIn);

		if (listeners != null) {
			listeners.remove(listener);

			if (listeners.isEmpty()) {
				this.listeners.remove(playerAdvancementsIn);
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
		PotassiumExplosionTrigger.Listeners listeners = this.listeners.get(player.getAdvancements());
		if (listeners != null) {
			listeners.trigger(state);
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

	private static class Listeners {
		private final PlayerAdvancements playerAdvancements;
		private final Set<Listener<Instance>> listeners = new HashSet<>();

		Listeners(PlayerAdvancements playerAdvancementsIn) {
			this.playerAdvancements = playerAdvancementsIn;
		}

		boolean isEmpty() {
			return this.listeners.isEmpty();
		}

		void add(Listener<PotassiumExplosionTrigger.Instance> listener) {
			this.listeners.add(listener);
		}

		void remove(Listener<PotassiumExplosionTrigger.Instance> listener) {
			this.listeners.remove(listener);
		}

		void trigger(IBlockState state) {
			List<Listener<Instance>> validListenerList = new ArrayList<>();
			for (Listener<PotassiumExplosionTrigger.Instance> listener : listeners) {
				if (listener.getCriterionInstance().test(state)) {
					validListenerList.add(listener);
				}
			}
			for (Listener<PotassiumExplosionTrigger.Instance> listener : validListenerList) {
				listener.grantCriterion(this.playerAdvancements);
			}
		}
	}
}
