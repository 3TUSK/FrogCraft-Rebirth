package frogcraftrebirth.common.world;

import frogcraftrebirth.api.FrogRegistees;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import frogcraftrebirth.common.block.BlockFrogOre;

public final class FrogWorldGenerator {

	private static final IBlockState CARNALLITE = FrogRegistees.ORE.getDefaultState().withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.CARNALLITE);
	private static final IBlockState DEWALQUITE = FrogRegistees.ORE.getDefaultState().withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.DEWALQUITE);
	private static final IBlockState FLUORAPATITE = FrogRegistees.ORE.getDefaultState().withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.FLUORAPATITE);
	private static final WorldGenerator CARNALLITE_GEN = new WorldGenClayFake(CARNALLITE, 3);
	private static final WorldGenerator DEWALQUITE_GEN = new WorldGenMinable(DEWALQUITE, 15);
	private static final WorldGenerator FLUORAPATITE_GEN = new WorldGenMinable(FLUORAPATITE, 15);

	@SubscribeEvent
	public void onOreGen(OreGenEvent.Post event) {
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), CARNALLITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			CARNALLITE_GEN.generate(event.getWorld(), event.getRand(), event.getWorld().getTopSolidOrLiquidBlock(event.getPos().add(8, 0, 8)));
		}
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), DEWALQUITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			for (int i = 0; i < 20; i++) {
				DEWALQUITE_GEN.generate(event.getWorld(), event.getRand(), event.getPos().add(event.getRand().nextInt(16), event.getRand().nextInt(127), event.getRand().nextInt(16)));
			}
		}
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), FLUORAPATITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			for (int i = 0; i < 20; i++) {
				FLUORAPATITE_GEN.generate(event.getWorld(), event.getRand(), event.getPos().add(event.getRand().nextInt(16), event.getRand().nextInt(127), event.getRand().nextInt(16)));
			}
		}
	}
	
}
