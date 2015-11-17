package frogcraftrewrite.common.compat.gregtech;

import frogcraftrewrite.common.lib.block.BlockFrogContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPneumaticCompressor extends BlockFrogContainer {

	protected BlockPneumaticCompressor() {
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;//to be continued
	}
	

}
