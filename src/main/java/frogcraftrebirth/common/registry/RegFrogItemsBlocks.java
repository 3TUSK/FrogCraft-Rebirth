package frogcraftrebirth.common.registry;

import java.util.List;

import net.minecraftforge.fml.common.registry.GameRegistry;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.block.*;
import frogcraftrebirth.common.item.*;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import frogcraftrebirth.common.tile.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegFrogItemsBlocks {
	
	public static void preInit() {
		initBlocks();
		initItems();
		initTileEntity();
	}
	
	public static void init() {
		initOreDict();
	}
	
	public static void postInit() {
		// NOTHING
	}
	
	static void initBlocks() {
		FrogBlocks.frogOres = new BlockFrogOre();
		FrogBlocks.tiberium = new BlockTiberium();
		FrogBlocks.generators = new BlockGenerator();
		FrogBlocks.machines = new BlockMachine();
		FrogBlocks.inductionalMachines = new BlockInductionMachine();
		FrogBlocks.condenseTowerPart = new BlockCondenseTower();
		FrogBlocks.hybridStorageUnit = new BlockHybridEStorage();
		//FrogBlocks.mobilePowerStation = new BlockMPS();
		
		GameRegistry.registerBlock(FrogBlocks.frogOres, ItemFrogBlock.class, "oreFrog");
		GameRegistry.registerBlock(FrogBlocks.tiberium, ItemFrogBlock.class, "tiberiumCrystal");
		GameRegistry.registerBlock(FrogBlocks.generators, ItemFrogBlock.class, "generator");
		GameRegistry.registerBlock(FrogBlocks.machines, ItemFrogBlock.class, "machines");
		GameRegistry.registerBlock(FrogBlocks.inductionalMachines, ItemFrogBlock.class, "machinesInductional");
		GameRegistry.registerBlock(FrogBlocks.condenseTowerPart, ItemFrogBlock.class, "condenseTower");
		GameRegistry.registerBlock(FrogBlocks.hybridStorageUnit, ItemFrogBlock.class, "hybridStorageUnit");
		//GameRegistry.registerBlock(FrogBlocks.mobilePowerStation, ItemMPS.class, "mobilePowerStation");

	}

	static void initItems() {
		FrogItems.decayBatteryUranium = new ItemDecayBattery("U");
		FrogItems.decayBatteryThorium = new ItemDecayBattery("Th");
		FrogItems.decayBatteryPlutoium = new ItemDecayBattery("Pu");
		FrogItems.coolantAmmonia60K = new ItemAmmoniaCoolant("60K", 6000);
		FrogItems.coolantAmmonia180K = new ItemAmmoniaCoolant("180K", 18000);
		FrogItems.coolantAmmonia360K = new ItemAmmoniaCoolant("360K", 36000);
		FrogItems.itemCell = new ItemCell();
		FrogItems.itemIngot = new ItemIngot();
		FrogItems.itemDust = new ItemDust();
		FrogItems.itemReactionModule = new ItemCatalystModule();
		FrogItems.ionCannon = new ItemIonCannon(1000000);
		FrogItems.ionCannonFrame = new ItemFrogCraft(false) {

			@Override
			public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
				return java.util.Arrays.asList("Unimplement yet");
			}
			
		}.setCreativeTab(FrogAPI.frogTab).setMaxStackSize(1);
		FrogItems.jinkela = new ItemJinkela();
		FrogItems.tiberium = new ItemTiberium();
		
		GameRegistry.registerItem(FrogItems.decayBatteryUranium, "decayBatteryUranium");
		GameRegistry.registerItem(FrogItems.decayBatteryThorium, "decayBatteryThorium");
		GameRegistry.registerItem(FrogItems.decayBatteryPlutoium, "decayBatteryPlutoium");
		GameRegistry.registerItem(FrogItems.coolantAmmonia60K, "coolantAmmonia60K");
		GameRegistry.registerItem(FrogItems.coolantAmmonia180K, "coolantAmmonia180K");
		GameRegistry.registerItem(FrogItems.coolantAmmonia360K, "coolantAmmonia360K");
		GameRegistry.registerItem(FrogItems.itemCell, "frogCells");
		GameRegistry.registerItem(FrogItems.itemIngot, "frogIngots");
		GameRegistry.registerItem(FrogItems.itemDust, "frogDusts");
		GameRegistry.registerItem(FrogItems.itemReactionModule, "frogModule");
		GameRegistry.registerItem(FrogItems.ionCannon, "railgun");
		GameRegistry.registerItem(FrogItems.ionCannonFrame, "railgunRail");
		GameRegistry.registerItem(FrogItems.jinkela, "jinkela");
		GameRegistry.registerItem(FrogItems.tiberium, "tiberium");
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("railgun", FrogItems.ionCannon);
		OreDictionary.registerOre("jinkela", FrogItems.jinkela);
		
		OreDictionary.registerOre("gemRuby", new ItemStack(FrogItems.itemIngot, 1, 2));
		OreDictionary.registerOre("gemSapphire", new ItemStack(FrogItems.itemIngot, 1, 3));
		OreDictionary.registerOre("gemGreenSapphire", new ItemStack(FrogItems.itemIngot, 1, 4));
		
		OreDictionary.registerOre("cellAmmonia", new ItemStack(FrogItems.itemCell, 1, 0));
		
		OreDictionary.registerOre("stoneBasalt", new ItemStack(FrogBlocks.frogOres, 1, 0));
		OreDictionary.registerOre("stoneMarble", new ItemStack(FrogBlocks.frogOres, 1, 1));
		OreDictionary.registerOre("oreCarnallite", new ItemStack(FrogBlocks.frogOres, 1, 2));
		OreDictionary.registerOre("oreDewalquite", new ItemStack(FrogBlocks.frogOres, 1, 3));
		OreDictionary.registerOre("oreFluorapatite", new ItemStack(FrogBlocks.frogOres, 1, 4));
		OreDictionary.registerOre("oreRuby", new ItemStack(FrogBlocks.frogOres, 1, 6));
		OreDictionary.registerOre("oreSapphire", new ItemStack(FrogBlocks.frogOres, 1, 7));
		OreDictionary.registerOre("oreGreenSapphire", new ItemStack(FrogBlocks.frogOres, 1, 8));
		
	}
	
	static void initTileEntity() {
		//GameRegistry.registerTileEntity(TileMobilePowerStation.class, "tileMobilePowerStation");
		GameRegistry.registerTileEntity(TileHSU.class, "tileHybridStorageUnit");
		GameRegistry.registerTileEntity(TileHSUUltra.class, "tileUltraHybridStorageUnit");
		GameRegistry.registerTileEntity(TileAirPump.class, "tileAirPump");
		GameRegistry.registerTileEntity(TileCondenseTower.class, "tileCondenseTowerCore");
		GameRegistry.registerTileEntity(TileFluidOutputHatch.class, "tileCondenseTowerFluidOutput");
		GameRegistry.registerTileEntity(TileCombustionFurnace.class, "tileCombustionFurnace");
		GameRegistry.registerTileEntity(TilePyrolyzer.class, "tileThermalCracker");
		GameRegistry.registerTileEntity(TileAdvChemReactor.class, "tileAdvancedChemicalReactor");
	}
}
