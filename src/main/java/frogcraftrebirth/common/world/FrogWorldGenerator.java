package frogcraftrebirth.common.world;

import frogcraftrebirth.api.FrogRegistees;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import frogcraftrebirth.common.block.BlockFrogOre;

public final class FrogWorldGenerator {

	private static final IBlockState CARNALLITE = FrogRegistees.ORE.getDefaultState().withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.CARNALLITE);
	private static final IBlockState DEWALQUITE = FrogRegistees.ORE.getDefaultState().withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.DEWALQUITE);
	private static final IBlockState FLUORAPATITE = FrogRegistees.ORE.getDefaultState().withProperty(BlockFrogOre.TYPE, BlockFrogOre.Type.FLUORAPATITE);
	private static final WorldGenerator CARNALLITE_GEN = new WorldGenClayFake(CARNALLITE, 5);
	private static final WorldGenerator DEWALQUITE_GEN = new WorldGenMinable(DEWALQUITE, 15);
	private static final WorldGenerator FLUORAPATITE_GEN = new WorldGenMinable(FLUORAPATITE, 15);
	
	@SubscribeEvent
	public void onDecorate(DecorateBiomeEvent event) {
		if (TerrainGen.decorate(event.getWorld(), event.getRand(), event.getPos(), DecorateBiomeEvent.Decorate.EventType.CLAY)) {
			int xCor = event.getPos().getX() * 16 + event.getRand().nextInt(16);
			int zCor = event.getPos().getZ() * 16 + event.getRand().nextInt(16);
			CARNALLITE_GEN.generate(event.getWorld(), event.getRand(), event.getWorld().getTopSolidOrLiquidBlock(new BlockPos(xCor, 60, zCor)));
		}
	}

	@SubscribeEvent
	public void onOreGen(OreGenEvent.Post event) {
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), DEWALQUITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			int xCor = event.getPos().getX() * 16 + event.getRand().nextInt(16);
			int zCor = event.getPos().getZ() * 16 + event.getRand().nextInt(16);
			DEWALQUITE_GEN.generate(event.getWorld(), event.getRand(), new BlockPos(xCor, 60, zCor));
		}
		if (TerrainGen.generateOre(event.getWorld(), event.getRand(), FLUORAPATITE_GEN, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			int xCor = event.getPos().getX() * 16 + event.getRand().nextInt(16);
			int zCor = event.getPos().getZ() * 16 + event.getRand().nextInt(16);
			FLUORAPATITE_GEN.generate(event.getWorld(), event.getRand(), new BlockPos(xCor, 60, zCor));
		}
	}
	
}
