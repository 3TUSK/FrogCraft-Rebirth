/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 9:05:39 AM, Aug 5, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.techreborn;

import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPneumaticCompressor extends BlockFrogWrenchable implements ITileEntityProvider {

	protected BlockPneumaticCompressor() {
		super(MACHINE, "pneumatic_compressor", false, 0);
	}

	@Override
	protected IProperty<?>[] getPropertyArray() {
		return new IProperty[] {};
	}	

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePneumaticCompressor();
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

}
