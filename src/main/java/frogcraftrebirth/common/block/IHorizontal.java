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

package frogcraftrebirth.common.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A restricted version of {@link IRotatable}, in which only EAST, WEST, NORTH, SOUTH orientation are permitted.
 */
public interface IHorizontal extends IRotatable {

	@Override
	default void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING_HORIZONTAL, placer.getHorizontalFacing().getOpposite()));
	}

	@Override
	default EnumFacing getFacing(World world, BlockPos pos) {
		return world.getBlockState(pos).getValue(FACING_HORIZONTAL);
	}

	@Override
	default boolean setFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
		if (newDirection.getAxis() != EnumFacing.Axis.Y) { // Only UP and DOWN have EnumFacing.Axis.Y
			world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING_HORIZONTAL, newDirection), 2);
			return true;
		} else {
			return false;
		}
	}

	PropertyDirection FACING_HORIZONTAL = BlockHorizontal.FACING;
}
