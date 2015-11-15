package frogcraftrewrite.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachine extends BlockFrog {

	protected BlockMachine() {
		super(Material.iron);
		setBlockName("FrogCarftMachine");
		setHardness(5.0F);
		setResistance(10.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		
		}
		return null;
	}

}
