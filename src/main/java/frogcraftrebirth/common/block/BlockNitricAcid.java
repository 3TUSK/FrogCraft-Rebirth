package frogcraftrebirth.common.block;

import java.util.Random;

import frogcraftrebirth.api.FrogRegistees;
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
		this.setUnlocalizedName("nitricAcid");
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
			ItemStack stack = ((EntityItem)entity).getEntityItem();
			if (stack.getItem() == FrogRegistees.INGOT && stack.getMetadata() == 0) {
				world.createExplosion(entity, pos.getX(), pos.getY(), pos.getZ(), 15F, true);
			}
		}
	}

}
