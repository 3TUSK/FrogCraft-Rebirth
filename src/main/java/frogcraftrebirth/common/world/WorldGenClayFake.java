/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenClay;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Identical to WorldGenClay, but allow user to generate their own blocks.
 */
@ParametersAreNonnullByDefault
class WorldGenClayFake extends WorldGenClay {

	private final int numberOfBlocks;

	private final IBlockState blockState;

	WorldGenClayFake(IBlockState blockState, int numberOfBlocks) {
		super(0);
		this.blockState = blockState;
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
								worldIn.setBlockState(blockpos, blockState, 2);
							}
						}
					}
				}
			}

			return true;
		}
	}

}
