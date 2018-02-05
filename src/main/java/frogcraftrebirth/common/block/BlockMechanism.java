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

import frogcraftrebirth.common.lib.block.BlockFrog;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockMechanism extends BlockFrog implements IWrenchable {

	private final Class<? extends TileFrog> type;

	/**
	 * {@inheritDoc}
	 */
	public BlockMechanism(@Nonnull Class<? extends TileFrog> glass) {
		super(Material.IRON);
		this.type = glass;
	}

	// Explicitly override this, forcing us not relying on metadata anymore
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		try {
			return type.newInstance();
		} catch (Exception ignored) {
			return null;
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileFrog) {
			((TileFrog)tileEntity).onBlockDestroyed(worldIn, pos, state); // TODO Do we need a separate interface for this guy?
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public EnumFacing getFacing(World world, BlockPos blockPos) {
		return null;
	}

	@Override
	public boolean setFacing(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
		return false;
	}

	@Override
	public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
		return null;
	}
}
