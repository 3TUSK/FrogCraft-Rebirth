package frogcraftrewrite.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachine extends BlockFrog {

	protected BlockMachine() {
		super(Material.iron);
		setBlockName("FrogCarftMachine");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

}
