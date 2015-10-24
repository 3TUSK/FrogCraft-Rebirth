package frogcraftrewrite.common.world;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldGenNGH extends WorldGenerator{

	private Block block;
	private int meta;
	
	ArrayList<Block> vaildBlocks;
	
	public WorldGenNGH(Block aOre, int meta) {
		this.block = aOre;
		this.meta = meta;
		vaildBlocks = new ArrayList<Block>();
		vaildBlocks.add(Blocks.stone);
		vaildBlocks.add(Blocks.dirt);
	}
	
	//I gave up! Generated as a "cube".
	public boolean genOre(World world, Random rand, int x, int y, int z) {
		if (world.getBiomeGenForCoords(x, z) == BiomeGenBase.deepOcean) {
			if (world.getBlock(x, y, z) == Blocks.gravel && y < 50) {
				int m = rand.nextInt(100);
				Size size = m > 98 ? Size.LARGE : m > 95 ? Size.MEDIUM : m > 90 ? Size.SMALL : null;
				if (size != null){
					for (int n = -1; n > -9; n--) {
						if (vaildBlocks.contains(world.getBlock(x,y+n,z))) {
							world.setBlock(x, y+n, z, block, meta, 3);
						}
						for (int o = -size.value(); o < size.value(); o++) {
							if (vaildBlocks.contains(world.getBlock(x+o, y+n, z+o))) {
								world.setBlock(x+o, y+n, z+o, block);
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	//What.
	private enum Size {
		SMALL(12), MEDIUM(32), LARGE(72);
		
		private int size;
		private Size(int size) {
			this.size = size;
		}
		public int value() {
			return size;
		}
	}
}
