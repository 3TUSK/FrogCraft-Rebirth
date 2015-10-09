package frogcraftrewrite.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrewrite.api.FrogBlocks;
import frogcraftrewrite.api.FrogItems;
import frogcraftrewrite.common.block.BlockHybridEStorage;
import frogcraftrewrite.common.block.BlockNGH;
import frogcraftrewrite.common.block.acwindmill.ACWindmillFan;
import frogcraftrewrite.common.block.acwindmill.BlockACWindmill;
import frogcraftrewrite.common.block.acwindmill.ItemBlockACWindmill;
import frogcraftrewrite.common.block.acwindmill.TileACWindmillBase;
import frogcraftrewrite.common.block.acwindmill.TileACWindmillTurbine;
import frogcraftrewrite.common.item.ItemAmmoniaCoolant;
import frogcraftrewrite.common.item.ItemCell;
import frogcraftrewrite.common.item.ItemDecayBattery;
import frogcraftrewrite.common.item.ItemIngot;
import frogcraftrewrite.common.item.ItemRailgun;
import frogcraftrewrite.common.tile.TileHSU;
import frogcraftrewrite.common.tile.TileUHSU;
import frogcraftrewrite.common.item.ItemDust;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegFrogItemsBlocks {
	
	public static void init() {
		initBlocks();
		initItems();
		initOreDict();
		initTileEntity();
	}
	
	public static void initBlocks() {
		FrogBlocks.naturalGasHydrate = new BlockNGH();
		FrogBlocks.acwindmill = new BlockACWindmill();
		FrogBlocks.hybridStorageUnit = new BlockHybridEStorage("HSU");
		FrogBlocks.hybridStorageUnitUltra = new BlockHybridEStorage("UHSU");
		
		GameRegistry.registerBlock(FrogBlocks.naturalGasHydrate, "naturalgasHydrate");
		GameRegistry.registerBlock(FrogBlocks.acwindmill, ItemBlockACWindmill.class, "academyWindmill");
		GameRegistry.registerBlock(FrogBlocks.hybridStorageUnit, "frogHSU");
		GameRegistry.registerBlock(FrogBlocks.hybridStorageUnitUltra, "frogUHSU");
	}

	public static void initItems() {
		FrogItems.railgun = new ItemRailgun(1000000);
		FrogItems.decayBatteryUranium = new ItemDecayBattery("U");
		FrogItems.decayBatteryThorium = new ItemDecayBattery("Th");
		FrogItems.decayBatteryPlutoium = new ItemDecayBattery("Pu");
		FrogItems.coolantAmmonia60K = new ItemAmmoniaCoolant("60K", 6000);
		FrogItems.coolantAmmonia180K = new ItemAmmoniaCoolant("180K", 18000);
		FrogItems.coolantAmmonia360K = new ItemAmmoniaCoolant("360K", 36000);
		FrogItems.itemCell = new ItemCell();
		FrogItems.itemIngot = new ItemIngot();
		FrogItems.itemDust = new ItemDust();
		FrogItems.acwinmillFan = new ACWindmillFan();
		
		GameRegistry.registerItem(FrogItems.railgun, "railgun");
		GameRegistry.registerItem(FrogItems.decayBatteryUranium, "decayBatteryUranium");
		GameRegistry.registerItem(FrogItems.decayBatteryThorium, "decayBatteryThorium");
		GameRegistry.registerItem(FrogItems.decayBatteryPlutoium, "decayBatteryPlutoium");
		GameRegistry.registerItem(FrogItems.coolantAmmonia60K, "coolantAmmonia60K");
		GameRegistry.registerItem(FrogItems.coolantAmmonia180K, "coolantAmmonia180K");
		GameRegistry.registerItem(FrogItems.coolantAmmonia360K, "coolantAmmonia360K");
		GameRegistry.registerItem(FrogItems.itemCell, "frogCells");
		GameRegistry.registerItem(FrogItems.itemIngot, "frogIngots");
		GameRegistry.registerItem(FrogItems.itemDust, "frogDusts");
		GameRegistry.registerItem(FrogItems.acwinmillFan, "academyWindmillFan");
	}
	
	public static void initOreDict() {
		OreDictionary.registerOre("gemRuby", new ItemStack(FrogItems.itemIngot, 1, 2));
		OreDictionary.registerOre("gemSapphire", new ItemStack(FrogItems.itemIngot, 1, 3));
		OreDictionary.registerOre("gemGreenSapphire", new ItemStack(FrogItems.itemIngot, 1, 4));
	}
	
	public static void initTileEntity() {
		GameRegistry.registerTileEntity(TileACWindmillBase.class, "tileFrogAcademyWindmill");
		GameRegistry.registerTileEntity(TileACWindmillTurbine.class, "tileFrogAcademyTurbine");
		GameRegistry.registerTileEntity(TileHSU.class, "tileHybridStorageUnit");
		GameRegistry.registerTileEntity(TileUHSU.class, "tileUltraHybridStorageUnit");
	}
}
