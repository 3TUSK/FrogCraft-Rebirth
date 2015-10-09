package frogcraftrewrite.common.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import frogcraftrewrite.common.block.BlockNGH;

public class WorldGenerator implements IWorldGenerator{

	//I gave up totally.
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int xCor = chunkX * 16 + random.nextInt(16);
		int zCor = chunkZ * 16 + random.nextInt(16);
		
		new WorldGenNGH(new BlockNGH()).genOre(world, random, xCor, world.getTopSolidOrLiquidBlock(xCor, zCor), zCor);
	}

}
