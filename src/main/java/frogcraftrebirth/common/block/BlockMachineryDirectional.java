/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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

import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockMachineryDirectional extends BlockMachinery implements IWrenchable, IHorizontal {

	public BlockMachineryDirectional(@Nonnull Class<? extends TileFrog> glass) {
		super(glass);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(WORKING, Boolean.FALSE)
				.withProperty(FACING_HORIZONTAL, EnumFacing.NORTH));
	}

	@Nonnull
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, WORKING, FACING_HORIZONTAL);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((state.getValue(WORKING) ? 1 : 0) << 2 ) + state.getValue(FACING_HORIZONTAL).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(WORKING, (meta >> 2) == 0 ? Boolean.FALSE : Boolean.TRUE)
				.withProperty(FACING_HORIZONTAL, EnumFacing.getHorizontal(meta & 3));
	}

	@Override
	public EnumFacing getFacing(World world, BlockPos pos) {
		return IHorizontal.super.getFacing(world, pos);
	}

	@Override
	public boolean setFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
		return IHorizontal.super.setFacing(world, pos, newDirection, player);
	}

	@Override
	public boolean wrenchCanRemove(World world, BlockPos pos, EntityPlayer player) {
		return IHorizontal.super.wrenchCanRemove(world, pos, player);
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te, EntityPlayer player, int fortune) {
		return IHorizontal.super.getWrenchDrops(world, pos, state, te, player, fortune);
	}
}
