package frogcraftrebirth.common.registry;

import frogcraftrebirth.common.block.*;
import frogcraftrebirth.common.item.*;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import frogcraftrebirth.common.tile.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegFrogItemsBlocks {

	public static final Block HSU = new BlockHSU();
	public static final Block MPS = new BlockMPS();
	public static final Block CONDENSE_TOWER = new BlockCondenseTower();
	public static final Block GENERATOR = new BlockGenerator();
	public static final Block MACHINE = new BlockMachine();
	public static final Block ORE = new BlockFrogOre();
	public static final Block TIBERIUM = new BlockTiberium();

	public static final Item AMMONIA_COOLANT_60K = new ItemAmmoniaCoolant("60K", 6000).setRegistryName("ammonia_coolant_60k");
	public static final Item AMMONIA_COOLANT_180K = new ItemAmmoniaCoolant("180K", 18000).setRegistryName("ammonia_coolant_180k");
	public static final Item AMMONIA_COOLANT_360K = new ItemAmmoniaCoolant("360K", 36000).setRegistryName("ammonia_coolant_360k");
	public static final Item INGOT = new ItemResources("Item_Ingots", "K", "P", "NaturalGasHydrate", "Briquette", "CoalCokeShattered").setRegistryName("ingot");
	public static final Item DUST = new ItemResources("Item_Dusts", "Al2O3", "CaF2", "CaO", "CaOH2", "Carnallite", "CaSiO3", "Dewalquite", "Fluorapatite", "KCl", "Magnalium", "MgBr2", "NH4NO3", "TiO2", "Urea", "V2O5").setRegistryName("dust");
	public static final Item CRUSHED_DUST = new ItemResources("crushedOre", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("crushed");
	public static final Item PURIFIED_DUST = new ItemResources("purifiedOre", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("purified");
	public static final Item SMALL_PILE_DUST = new ItemResources("smallDust", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("small_pile_dust");
	public static final Item REACTION_MODULE = new ItemResources("reactionModule", "Heating", "Electrolyze", "Ammonia", "V2O5").setRegistryName("catalyst_module");
	public static final Item ION_CANNON = new ItemIonCannon(1000000).setRegistryName("ion_cannon");
	public static final Item ION_CANNON_FRAME = new ItemResources(false, "ionCannonFrame").setMaxStackSize(1).setRegistryName("ion_cannon_frame");
	public static final Item DECAY_BATTERY_URANIUM = new ItemDecayBattery("U").setRegistryName("uranium_decay_battery");
	public static final Item DECAY_BATTERY_THORIUM = new ItemDecayBattery("Th").setRegistryName("thorium_decay_battery");
	public static final Item DECAY_BATTERY_PLOTONIUM = new ItemDecayBattery("Pu").setRegistryName("plutoium_decay_battery");
	public static final Item JINKELA = new ItemJinkela().setRegistryName("jinkela");
	public static final Item TIBERIUM_ITEM = new ItemTiberium().setRegistryName("tiberium");
	public static final Item FLUID_ARMOR = new ItemFluidArmor(12000).setRegistryName("fluid_armor");

	/**
	 * @deprecated Use RegistryEvent.Register instead.
	 */
	@Deprecated
	public static void init() {
		initTileEntity();
	}

	@SubscribeEvent
	public static void initBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ORE, TIBERIUM, GENERATOR, MACHINE, CONDENSE_TOWER, HSU, MPS);
		ItemFrogBlock.registerItemBlockFor(CONDENSE_TOWER, aStack -> BlockCondenseTower.Part.values()[aStack.getMetadata() & 0b11].getName());
		ItemFrogBlock.registerItemBlockFor(ORE, aStack -> BlockFrogOre.Type.values()[aStack.getMetadata()].getName());
		ItemFrogBlock.registerItemBlockFor(GENERATOR, aStack -> "combustionFurnace");
		ItemFrogBlock.registerItemBlockFor(HSU, aStack -> BlockHSU.Level.values()[aStack.getMetadata() % 2].getName());
		ItemFrogBlock.registerItemBlockFor(MACHINE, aStack -> BlockMachine.Type.values()[aStack.getMetadata() & 0b11].getName());
		ItemFrogBlock.registerItemBlockFor(TIBERIUM, aStack -> BlockTiberium.Color.values()[aStack.getMetadata()].getName());
		ItemFrogBlock.registerItemBlockFor(MPS, new ItemMPS((BlockMPS)MPS));
	}

	@SubscribeEvent
	public static void initItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(DECAY_BATTERY_URANIUM, DECAY_BATTERY_THORIUM, DECAY_BATTERY_PLOTONIUM,
				AMMONIA_COOLANT_60K, AMMONIA_COOLANT_180K, AMMONIA_COOLANT_360K,
				INGOT, DUST, CRUSHED_DUST, PURIFIED_DUST, SMALL_PILE_DUST,
				REACTION_MODULE, ION_CANNON, ION_CANNON_FRAME,
				JINKELA, TIBERIUM_ITEM, FLUID_ARMOR);
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
