package frogcraftrewrite.common.block;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockACWindmillCylineder extends BlockFence {

	public BlockACWindmillCylineder() {
		super("iron_block", Material.iron);
		setResistance(50.0F);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) instanceof BlockACWindmill || world.getBlock(x, y, z) instanceof BlockACWindmillCylineder;
	}


}
