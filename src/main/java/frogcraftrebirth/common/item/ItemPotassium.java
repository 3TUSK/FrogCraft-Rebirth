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

import frogcraftrebirth.api.FrogGameObjects;
import frogcraftrebirth.common.advancement.PotassiumExplosionTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public final class ItemPotassium extends ItemResource {

	private static final PotassiumExplosionTrigger POTASSIUM_EXPLOSION_TRIGGER = CriteriaTriggers.register(new PotassiumExplosionTrigger());

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (!entityItem.getEntityWorld().isRemote && !entityItem.getItem().isEmpty() && entityItem.getItem().getItem() == this) {
			if (entityItem.getEntityWorld().getBlockState(entityItem.getPosition()).getBlock() == FrogGameObjects.NITRIC_ACID) {
				entityItem.world.createExplosion(entityItem, entityItem.posX, entityItem.posY, entityItem.posZ, 16.0F, true);
				entityItem.lifespan = 0; // TODO How to actually remove it?
				EntityPlayer player = entityItem.getEntityWorld().getClosestPlayer(entityItem.posX, entityItem.posY, entityItem.posZ, 8.0, false);
				if (player instanceof EntityPlayerMP) {
					POTASSIUM_EXPLOSION_TRIGGER.trigger((EntityPlayerMP)player);
				}
				return true;
			}
		}
		return false;
	}
}
