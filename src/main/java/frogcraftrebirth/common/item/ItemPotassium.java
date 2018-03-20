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

package frogcraftrebirth.common.item;

import frogcraftrebirth.common.advancement.PotassiumExplosionTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;

public final class ItemPotassium extends ItemResource {

	private static final PotassiumExplosionTrigger POTASSIUM_EXPLOSION_TRIGGER = CriteriaTriggers.register(new PotassiumExplosionTrigger());

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (!entityItem.getEntityWorld().isRemote && !entityItem.getItem().isEmpty() && entityItem.getItem().getItem() == this) {
			IBlockState state = entityItem.getEntityWorld().getBlockState(entityItem.getPosition());
			if (state.getMaterial() == Material.WATER) { // Generalization
				// Modification comparing with original FrogCraft:
				// Strength is fixed at 2.7F * square_root(entityItem.getItem().getCount()),
				// where as the magic constant, in FrogCraft, is 0.9F when it's water, and 3.0F when it's nitric acid.
				entityItem.world.createExplosion(entityItem, entityItem.posX, entityItem.posY, entityItem.posZ, 2.7F * MathHelper.sqrt(entityItem.getItem().getCount()), false);
				entityItem.setDead();
				EntityPlayer player = entityItem.getEntityWorld().getClosestPlayer(entityItem.posX, entityItem.posY, entityItem.posZ, -1, false);
				if (player instanceof EntityPlayerMP) {
					POTASSIUM_EXPLOSION_TRIGGER.trigger((EntityPlayerMP)player, state);
				}
				return true;
			}
		}
		return false;
	}
}
