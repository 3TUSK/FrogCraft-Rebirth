package frogcraftrebirth.common.world;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.block.BlockFrogOre;

public class FrogWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int xCor = chunkX * 16 + random.nextInt(16);
		int zCor = chunkZ * 16 + random.nextInt(16);
		
		IBlockState theOre = FrogBlocks.frogOres.getDefaultState().withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.CARNALLITE);
		new WorldGenClayFake(theOre, 5).generate(world, random, world.getTopSolidOrLiquidBlock(new BlockPos(xCor, 60, zCor)));
		
		theOre = theOre.withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.DEWALQUITE);
		new WorldGenMinable(theOre, 15).generate(world, random, world.getTopSolidOrLiquidBlock(new BlockPos(xCor, 60, zCor)));
		
		theOre = theOre.withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.FLUORAPATITE);
		new WorldGenMinable(theOre, 15).generate(world, random, world.getTopSolidOrLiquidBlock(new BlockPos(xCor, 60, zCor)));
	}

}
