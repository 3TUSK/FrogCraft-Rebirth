package frogcraftrewrite.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrewrite.common.lib.FrogBlocks;
import frogcraftrewrite.common.block.BlockHybridEStorage;
import frogcraftrewrite.api.FrogFuelHandler;
import frogcraftrewrite.common.block.BlockACWindmill;
import frogcraftrewrite.common.block.BlockFrogOre;
import frogcraftrewrite.common.item.ItemACWindmillFan;
import frogcraftrewrite.common.item.ItemAmmoniaCoolant;
import frogcraftrewrite.common.item.ItemCatalystModule;
import frogcraftrewrite.common.item.ItemCell;
import frogcraftrewrite.common.item.ItemDecayBattery;
import frogcraftrewrite.common.item.ItemIngot;
import frogcraftrewrite.common.item.ItemJinkela;
import frogcraftrewrite.common.item.ItemRailgun;
import frogcraftrewrite.common.item.itemblock.ItemBlockACWindmill;
import frogcraftrewrite.common.item.itemblock.ItemBlockOre;
import frogcraftrewrite.common.lib.FrogItems;
import frogcraftrewrite.common.tile.TileACWindmillBase;
import frogcraftrewrite.common.tile.TileACWindmillTurbine;
import frogcraftrewrite.common.tile.TileHSU;
import frogcraftrewrite.common.tile.TileUHSU;
import frogcraftrewrite.common.item.ItemDust;
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
		//Register crafting recipe!
		FrogFuelHandler.FUEL_REG.reg(new ItemStack(FrogItems.itemIngot, 1, 6), 4800);
		FrogFuelHandler.FUEL_REG.reg(new ItemStack(FrogItems.itemIngot, 1, 7), 400);
	}
	
	static void initBlocks() {
		FrogBlocks.frogOres = new BlockFrogOre();
		FrogBlocks.acwindmill = new BlockACWindmill();
		FrogBlocks.hybridStorageUnit = new BlockHybridEStorage("HSU");
		FrogBlocks.hybridStorageUnitUltra = new BlockHybridEStorage("UHSU");
		
		GameRegistry.registerBlock(FrogBlocks.frogOres, ItemBlockOre.class, "naturalgasHydrate");
		GameRegistry.registerBlock(FrogBlocks.acwindmill, ItemBlockACWindmill.class, "academyWindmill");
		GameRegistry.registerBlock(FrogBlocks.hybridStorageUnit, "frogHSU");
		GameRegistry.registerBlock(FrogBlocks.hybridStorageUnitUltra, "frogUHSU");
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
		FrogItems.acwinmillFan = new ItemACWindmillFan();
		FrogItems.railgun = new ItemRailgun(1000000);
		FrogItems.jinkela = new ItemJinkela();
		
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
		GameRegistry.registerItem(FrogItems.acwinmillFan, "academyWindmillFan");
		GameRegistry.registerItem(FrogItems.railgun, "railgun");
		GameRegistry.registerItem(FrogItems.jinkela, "jinkela");
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("gemRuby", new ItemStack(FrogItems.itemIngot, 1, 2));
		OreDictionary.registerOre("gemSapphire", new ItemStack(FrogItems.itemIngot, 1, 3));
		OreDictionary.registerOre("gemGreenSapphire", new ItemStack(FrogItems.itemIngot, 1, 4));
		
		OreDictionary.registerOre("cellAmmonia", new ItemStack(FrogItems.itemCell, 1, 0));
	}
	
	static void initTileEntity() {
		GameRegistry.registerTileEntity(TileACWindmillBase.class, "tileFrogAcademyWindmill");
		GameRegistry.registerTileEntity(TileACWindmillTurbine.class, "tileFrogAcademyTurbine");
		GameRegistry.registerTileEntity(TileHSU.class, "tileHybridStorageUnit");
		GameRegistry.registerTileEntity(TileUHSU.class, "tileUltraHybridStorageUnit");
	}
}
