package frogcraftrewrite.common.block;

import frogcraftrewrite.common.tile.TileInductionalCompressor;
import frogcraftrewrite.common.tile.TileInductionalEFurnace;
import frogcraftrewrite.common.tile.TileInductionalExtractor;
import frogcraftrewrite.common.tile.TileInductionalMacerator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIndustrialMachine extends BlockInductionMachine{
	
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
				return new TileInductionalEFurnace();
			case 1:
				return new TileInductionalMacerator();
			case 2:
				return new TileInductionalExtractor();
			case 3:
				return new TileInductionalCompressor();
			default:
				return null;
		}
	}

}
