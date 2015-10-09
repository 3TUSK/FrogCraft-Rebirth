package frogcraftrewrite.common.block;

import frogcraftrewrite.common.tile.TileEntityIndustrialCompressor;
import frogcraftrewrite.common.tile.TileEntityIndustrialEFurnace;
import frogcraftrewrite.common.tile.TileEntityIndustrialExtractor;
import frogcraftrewrite.common.tile.TileEntityIndustrialMacerator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIndustrialMachine extends BlockMachine{
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
			case 0:
				return new TileEntityIndustrialEFurnace();
			case 1:
				return new TileEntityIndustrialMacerator();
			case 2:
				return new TileEntityIndustrialExtractor();
			case 3:
				return new TileEntityIndustrialCompressor();
			default:
				return null;
		}
	}

}
