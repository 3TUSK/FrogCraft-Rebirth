package frogcraftrebirth.common.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import frogcraftrebirth.common.FrogBlocks;

public class FrogWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int xCor = chunkX * 16 + random.nextInt(16);
		int zCor = chunkZ * 16 + random.nextInt(16);
		
		new WorldGenClayFake(FrogBlocks.frogOres, 2, 5).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
		new WorldGenMinable(FrogBlocks.frogOres, 3, 15, Blocks.stone).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
		new WorldGenMinable(FrogBlocks.frogOres, 4, 15, Blocks.stone).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
		//new WorldGenNGH(FrogBlocks.frogOres, 5).genOre(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
		new WorldGenMinable(FrogBlocks.frogOres, 6, 5, Blocks.stone).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
		new WorldGenMinable(FrogBlocks.frogOres, 7, 5, Blocks.stone).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
		new WorldGenMinable(FrogBlocks.frogOres, 8, 5, Blocks.stone).generate(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
	} 

}
