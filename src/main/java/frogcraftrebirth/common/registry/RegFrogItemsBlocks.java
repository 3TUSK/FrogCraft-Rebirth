package frogcraftrebirth.common.registry;

import frogcraftrebirth.common.block.*;
import frogcraftrebirth.common.item.*;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import frogcraftrebirth.common.tile.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegFrogItemsBlocks {
	
	public static void init() {
		initBlocks();
		initItems();
		initTileEntity();
	}
	
	private static void initBlocks() {
		final BlockFrogOre ore = new BlockFrogOre();
		final BlockTiberium tiberium = new BlockTiberium();
		final BlockGenerator generator = new BlockGenerator();
		final BlockMachine machine = new BlockMachine();
		final BlockCondenseTower condenseTower = new BlockCondenseTower();
		final BlockHSU hsu = new BlockHSU();
		final BlockMPS mps = new BlockMPS();
		GameRegistry.register(ore);
		GameRegistry.register(tiberium);
		GameRegistry.register(generator);
		GameRegistry.register(machine);
		GameRegistry.register(condenseTower);
		GameRegistry.register(hsu);
		GameRegistry.register(mps);
		ItemFrogBlock.registerItemBlockFor(condenseTower, aStack -> BlockCondenseTower.Part.values()[aStack.getMetadata() & 0b11].getName());
		ItemFrogBlock.registerItemBlockFor(ore, aStack -> BlockFrogOre.Type.values()[aStack.getMetadata()].getName());
		ItemFrogBlock.registerItemBlockFor(generator, aStack -> "combustionFurnace");
		ItemFrogBlock.registerItemBlockFor(hsu, aStack -> BlockHSU.Level.values()[aStack.getMetadata() % 2].getName());
		ItemFrogBlock.registerItemBlockFor(machine, aStack -> BlockMachine.Type.values()[aStack.getMetadata() & 0b11].getName());
		ItemFrogBlock.registerItemBlockFor(tiberium, aStack -> BlockTiberium.Color.values()[aStack.getMetadata()].getName());
		ItemFrogBlock.registerItemBlockFor(mps, new ItemMPS(mps));
	}

	private static void initItems() {		
		GameRegistry.register(new ItemDecayBattery("U").setRegistryName("uranium_decay_battery"));
		GameRegistry.register(new ItemDecayBattery("Th").setRegistryName("thorium_decay_battery"));
		GameRegistry.register(new ItemDecayBattery("Pu").setRegistryName("plutoium_decay_battery"));
		GameRegistry.register(new ItemAmmoniaCoolant("60K", 6000).setRegistryName("ammonia_coolant_60k"));
		GameRegistry.register(new ItemAmmoniaCoolant("180K", 18000).setRegistryName("ammonia_coolant_180k"));
		GameRegistry.register(new ItemAmmoniaCoolant("360K", 36000).setRegistryName("ammonia_coolant_360k"));
		GameRegistry.register(new ItemResources("Item_Ingots", "K", "P", "NaturalGasHydrate", "Briquette", "CoalCokeShattered").setRegistryName("ingot"));
		GameRegistry.register(new ItemResources("Item_Dusts", "Al2O3", "CaF2", "CaO", "CaOH2", "Carnallite", "CaSiO3", "Dewalquite", "Fluorapatite", "KCl", "Magnalium", "MgBr2", "NH4NO3", "TiO2", "Urea", "V2O5").setRegistryName("dust"));
		GameRegistry.register(new ItemResources("crushedOre", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("crushed"));
		GameRegistry.register(new ItemResources("purifiedOre", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("purified"));
		GameRegistry.register(new ItemResources("smallDust", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("small_pile_dust"));
		GameRegistry.register(new ItemResources("reactionModule", "Heating", "Electrolyze", "Ammonia", "V2O5").setRegistryName("catalyst_module"));
		GameRegistry.register(new ItemIonCannon(1000000).setRegistryName("ion_cannon"));
		GameRegistry.register(new ItemResources(false, "ionCannonFrame").setMaxStackSize(1).setRegistryName("ion_cannon_frame"));
		GameRegistry.register(new ItemJinkela().setRegistryName("jinkela"));
		GameRegistry.register(new ItemTiberium().setRegistryName("tiberium"));
		GameRegistry.register(new ItemFluidArmor(12000).setRegistryName("fluid_armor"));
	}
	
	private static void initTileEntity() {
		GameRegistry.registerTileEntity(TileMobilePowerStation.class, "frogcraft_mobile_power_station");
		GameRegistry.registerTileEntity(TileHSU.class, "frogcraft_hybrid_storage_unit");
		GameRegistry.registerTileEntity(TileHSUUltra.class, "frogcraft_ultra_hybrid_storage_unit");
		GameRegistry.registerTileEntity(TileAirPump.class, "frogcraft_air_pump");
		GameRegistry.registerTileEntity(TileCondenseTower.class, "frogcraft_condense_tower_core");
		GameRegistry.registerTileEntity(TileCondenseTowerStructure.class, "frogcraft_condense_tower_cylinder");
		GameRegistry.registerTileEntity(TileFluidOutputHatch.class, "frogcraft_condense_tower_outlet");
		GameRegistry.registerTileEntity(TileCombustionFurnace.class, "frogcraft_combustion_furnace");
		GameRegistry.registerTileEntity(TilePyrolyzer.class, "frogcraft_thermal_cracker");
		GameRegistry.registerTileEntity(TileAdvChemReactor.class, "frogcraft_advanced_chemical_reactor");
		GameRegistry.registerTileEntity(TileLiquefier.class, "frogcraft_liquefier");
	}
}
