package frogcraftrewrite.common.block;

import frogcraftrewrite.common.lib.block.BlockFrogContainer;
import frogcraftrewrite.common.tile.TileAdvChemReactor;
import frogcraftrewrite.common.tile.TileAirPump;
import frogcraftrewrite.common.tile.TileThermalCracker;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachine extends BlockFrogContainer {

	protected BlockMachine() {
		super(Material.iron);
		setBlockName("FrogCarftMachine");
		setHardness(5.0F);
		setResistance(10.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
			case 0:
				return new TileAdvChemReactor();
			case 1:
				return new TileAirPump();
			case 2:
				return new TileThermalCracker();
			default:
				return null;
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister r) {
		this.iconArray[0] = new net.minecraft.util.IIcon[6];
		//to be continued
	}
}
