package frogcraftrebirth.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
/**
 * Identical to WorldGenClay, but allow user to generate their own blocks.
 */
public class WorldGenClayFake extends FrogWorldGenerator {
	
	final int numberOfBlocks;
	
	final Block block;
	final int meta;
	
	public WorldGenClayFake(Block block, int meta, int numberOfBlocks) {
		this.block = block;
		this.meta = meta;
		this.numberOfBlocks = numberOfBlocks;
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial() != Material.water) {
			return false;
		} else {
			int l = rand.nextInt(this.numberOfBlocks - 2) + 2;
			byte b0 = 1;
			
			for (int i1 = x - l; i1 <= x + l; ++i1) {
				for (int j1 = z - l; j1 <= z + l; ++j1) {
					int k1 = i1 - x;
					int l1 = j1 - z;
					if (k1 * k1 + l1 * l1 <= l * l) {
						for (int i2 = y - b0; i2 <= y + b0; ++i2) {
							Block block = world.getBlock(i1, i2, j1);
							if (block == Blocks.dirt || block == Blocks.clay) {
								world.setBlock(i1, i2, j1, this.block, meta, 2);
							}
						}
					}
				}
			}
			return true;
		}
	}

}
