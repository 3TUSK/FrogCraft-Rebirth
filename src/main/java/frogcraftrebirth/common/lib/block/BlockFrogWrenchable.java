/**
 * This file is a part of FrogCraftRebirth, 
 * created 3TUSK at 9:40:01 AM, Jul 24, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib.block;

import java.util.Arrays;
import java.util.List;

import ic2.api.tile.IWrenchable;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockFrogWrenchable extends BlockFrog implements IWrenchable {
	
	public static final PropertyDirection FACING_ALL = BlockDirectional.FACING;
	public static final PropertyDirection FACING_HORIZONTAL = BlockHorizontal.FACING;
	
	protected final boolean allowVerticalRotation;

	protected BlockFrogWrenchable(Material material, String registryName, boolean allowVerticalRotation, int... metaForDisplay) {
		super(material, registryName, metaForDisplay);
		this.allowVerticalRotation = allowVerticalRotation;
	}

	@Override
	public EnumFacing getFacing(World world, BlockPos pos) {
		return world.getBlockState(pos).getValue(allowVerticalRotation ? FACING_ALL : FACING_HORIZONTAL);
	}

	@Override
	public boolean setFacing(World world, BlockPos pos, EnumFacing newDirection, EntityPlayer player) {
		if (!allowVerticalRotation) {
			switch (newDirection) {
				case UP: return false;
				case DOWN: return false;
				default: {
					world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING_HORIZONTAL, newDirection), 2);
					return true;
				}
			}
		} else {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(FACING_ALL, newDirection), 2);
			return true;
		}
	}

	@Override
	public boolean wrenchCanRemove(World world, BlockPos pos, EntityPlayer player) {
		return true;
	}

	@Override
	public List<ItemStack> getWrenchDrops(World world, BlockPos pos, IBlockState state, TileEntity te, EntityPlayer player, int fortune) {
		return Arrays.asList(new ItemStack(state.getBlock(), 1, state.getBlock().damageDropped(state)));
	}

}
