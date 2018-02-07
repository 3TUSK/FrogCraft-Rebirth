/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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

package frogcraftrebirth.common.block;

import java.util.Random;

import frogcraftrebirth.api.FrogGameObjects;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockNitricAcid extends BlockFluidClassic {
	
	public BlockNitricAcid(Fluid fluid) {
		super(fluid, Material.WATER);
		this.setUnlocalizedName("nitric_acid");
		this.setDensity(fluid.getDensity());
		this.setQuantaPerBlock(8);
		this.setTickRate(20);
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.displaceIfPossible(world, pos);
	}

	private int corrosion;
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (rand.nextBoolean()) return;
		
		for (int m=-3;m<3;m++) {
			for (int n=-3;n<3;n++) {
				int randInt = rand.nextInt(10);
				if (world.getBlockState(pos.north(m).east(n)) == Blocks.GRASS.getDefaultState() && randInt < 7) {
					world.setBlockState(pos.north(m).east(n), Blocks.DIRT.getDefaultState());
					++corrosion;
					checkCorrosion(world, pos);
				}
				if (world.getBlockState(pos.north(m).east(n)) == Blocks.DIRT.getDefaultState() && randInt < 5) {
					world.setBlockState(pos.north(m).east(n), Blocks.SAND.getDefaultState());
					++corrosion;
					checkCorrosion(world, pos);
				}
				if (world.getBlockState(pos.north(m).east(n)) == Blocks.STONE.getDefaultState() && randInt < 5) {
					world.setBlockState(pos.north(m).east(n), Blocks.COBBLESTONE.getDefaultState());
					++corrosion;
					checkCorrosion(world, pos);
				}
				if (world.getBlockState(pos.north(m).east(n)) == Blocks.COBBLESTONE.getDefaultState() && randInt < 8) {
					world.setBlockState(pos.north(m).east(n), Blocks.GRAVEL.getDefaultState());
					++corrosion;
					checkCorrosion(world, pos);
				}
				if (world.getBlockState(pos.north(m).east(n)) == Blocks.GRAVEL.getDefaultState() && randInt < 6) {
					world.setBlockState(pos.north(m).east(n), Blocks.SAND.getDefaultState());
					++corrosion;
					checkCorrosion(world, pos);	
				}
			}
		}
	}
	
	private void checkCorrosion(World world, BlockPos pos) {
		if (corrosion > 20)
			world.setBlockToAir(pos);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		super.onEntityCollidedWithBlock(world, pos, state, entity);
		if (entity instanceof EntityItem) {
			ItemStack stack = ((EntityItem)entity).getItem();
			if (stack.getItem() == FrogGameObjects.POTASSIUM) {
				world.createExplosion(entity, pos.getX(), pos.getY(), pos.getZ(), 15F, true);
			}
		}
	}

}
