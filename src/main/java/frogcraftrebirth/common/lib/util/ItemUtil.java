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

package frogcraftrebirth.common.lib.util;

import java.util.*;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public final class ItemUtil {
	
	private static final Random RAND = new Random();

	public static final Predicate<ItemStack> EMPTY_PREDICATE = ItemStack::isEmpty;

	public static final Predicate<ItemStack> NON_EMPTY_PREDICATE = stack -> !stack.isEmpty();

	/**
	 * Identical to {@link InventoryHelper#dropInventoryItems}, except this one is designed for {@link IItemHandler}.
	 * @param worldIn The world that block is in
	 * @param pos The position of block
	 * @param inv An array of IItemHandler implementation. Note: all IItemHandler here are assumed to start index from zero.
	 */
	public static void dropInventoryItems(World worldIn, BlockPos pos, IItemHandler... inv) {
		for (IItemHandler invSingle : inv) {
			final int slots = invSingle.getSlots();
			for (int index = 0; index < slots; index++) {
				ItemStack stack = invSingle.getStackInSlot(index);
				if (!stack.isEmpty())
					dropItemStackAsEntityInsanely(worldIn, pos, stack);
			}
		}
	}
	
	/**
	 * Identical to {@link InventoryHelper#spawnItemStack} in terms of functionality,
	 * except that this one has insane implementation.
	 * @param worldIn The world that item stack will drop.
	 * @param pos The position reference.
	 * @param toDrop The item stack to drop.
	 */
	public static void dropItemStackAsEntityInsanely(World worldIn, BlockPos pos, @Nonnull ItemStack toDrop) {
		EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), toDrop);
		entityItem.motionX = RAND.nextGaussian() * 0.05D;
		entityItem.motionY = RAND.nextGaussian() * 0.05D + 0.2D;
		entityItem.motionZ = RAND.nextGaussian() * 0.05D;
		worldIn.spawnEntity(entityItem);
	}

}
