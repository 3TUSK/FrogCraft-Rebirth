package frogcraftrebirth.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenClay;

/** Identical to WorldGenClay, but allow user to generate their own blocks. */
class WorldGenClayFake extends WorldGenClay {

	private final int numberOfBlocks;

	private final IBlockState blockstate;

	public WorldGenClayFake(IBlockState blockstate, int numberOfBlocks) {
		super(0);
		this.blockstate = blockstate;
		this.numberOfBlocks = numberOfBlocks;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.getBlockState(position).getMaterial() != Material.WATER) {
			return false;
		} else {
			int i = rand.nextInt(this.numberOfBlocks - 2) + 2;

			for (int x = position.getX() - i; x <= position.getX() + i; ++x) {
				for (int z = position.getZ() - i; z <= position.getZ() + i; ++z) {
					int i1 = x - position.getX();
					int j1 = z - position.getZ();

					if (i1 * i1 + j1 * j1 <= i * i) {
						for (int y = position.getY() - 1; y <= position.getY() + 1; ++y) {
							BlockPos blockpos = new BlockPos(x, y, z);
							Block target = worldIn.getBlockState(blockpos).getBlock();
							if (target == Blocks.DIRT || target == Blocks.CLAY) {
								worldIn.setBlockState(blockpos, blockstate, 2);
							}
						}
					}
				}
			}

			return true;
		}
	}

}
