package frogcraftrewrite.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCondenseTower extends BlockContainer {

	protected BlockCondenseTower() {
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		
		}
		
		return null;
	}

}
