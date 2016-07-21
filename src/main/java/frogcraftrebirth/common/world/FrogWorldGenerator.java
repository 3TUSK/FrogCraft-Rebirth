package frogcraftrebirth.common.world;

import java.util.Random;

//import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
//import frogcraftrebirth.common.FrogBlocks;

public class FrogWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		//int xCor = chunkX * 16 + random.nextInt(16);
		//int zCor = chunkZ * 16 + random.nextInt(16);
		
		//new WorldGenClayFake(FrogBlocks.frogOres, 0, 5).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(), zCor);
		//new WorldGenMinable(FrogBlocks.frogOres, 1, 15, Blocks.STONE).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(), zCor);
		//new WorldGenMinable(FrogBlocks.frogOres, 2, 15, Blocks.STONE).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(), zCor);
		//new WorldGenNGH(FrogBlocks.frogOres, 3).genOre(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
		//new WorldGenMinable(FrogBlocks.tiberium, 0, 5, Blocks.STONE).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(), zCor);
		//new WorldGenMinable(FrogBlocks.tiberium, 1, 5, Blocks.STONE).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(), zCor);
		//new WorldGenMinable(FrogBlocks.tiberium, 2, 5, Blocks.STONE).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(), zCor);
	}

}
