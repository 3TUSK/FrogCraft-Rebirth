package frogcraftrebirth.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
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

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.getBlockState(position).getMaterial() != Material.WATER) {
			return false;
		} else {
			int i = rand.nextInt(this.numberOfBlocks - 2) + 2;
			//int j = 1; why there is an unused variable?

			for (int k = position.getX() - i; k <= position.getX() + i; ++k) {
				for (int l = position.getZ() - i; l <= position.getZ() + i; ++l) {
					int i1 = k - position.getX();
					int j1 = l - position.getZ();

					if (i1 * i1 + j1 * j1 <= i * i) {
						for (int k1 = position.getY() - 1; k1 <= position.getY() + 1; ++k1) {
							BlockPos blockpos = new BlockPos(k, k1, l);
							Block block = worldIn.getBlockState(blockpos).getBlock();

							if (block == Blocks.DIRT || block == Blocks.CLAY) {
								worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
							}
						}
					}
				}
			}

			return true;
		}
	}

}
