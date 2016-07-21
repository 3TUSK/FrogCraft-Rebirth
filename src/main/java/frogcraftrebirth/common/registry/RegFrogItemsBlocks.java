package frogcraftrebirth.common.registry;

import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.block.BlockCondenseTower;
import frogcraftrebirth.common.block.BlockFrogOre;
import frogcraftrebirth.common.block.BlockGenerator;
import frogcraftrebirth.common.block.BlockHybridEStorage;
import frogcraftrebirth.common.block.BlockMachine;
import frogcraftrebirth.common.block.BlockTiberium;
import frogcraftrebirth.common.item.ItemAmmoniaCoolant;
import frogcraftrebirth.common.item.ItemCatalystModule;
import frogcraftrebirth.common.item.ItemDecayBattery;
import frogcraftrebirth.common.item.ItemDust;
import frogcraftrebirth.common.item.ItemIngot;
import frogcraftrebirth.common.item.ItemIonCannon;
import frogcraftrebirth.common.item.ItemJinkela;
import frogcraftrebirth.common.item.ItemTiberium;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import frogcraftrebirth.common.tile.TileHSU;
import frogcraftrebirth.common.tile.TileHSUUltra;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
		FrogBlocks.condenseTowerPart = new BlockCondenseTower();
		FrogBlocks.hybridStorageUnit = new BlockHybridEStorage();
		//FrogBlocks.mobilePowerStation = new BlockMPS();
		
		GameRegistry.<Block>register(FrogBlocks.frogOres);
		GameRegistry.<Block>register(FrogBlocks.tiberium);
		GameRegistry.<Block>register(FrogBlocks.generators);
		GameRegistry.<Block>register(FrogBlocks.machines);
		GameRegistry.<Block>register(FrogBlocks.inductionalMachines);
		GameRegistry.<Block>register(FrogBlocks.condenseTowerPart);
		GameRegistry.<Block>register(FrogBlocks.hybridStorageUnit);
		//GameRegistry.<Block>register(FrogBlocks.mobilePowerStation);

	}

	static void initItems() {
		FrogItems.decayBatteryUranium = new ItemDecayBattery("U");
		FrogItems.decayBatteryThorium = new ItemDecayBattery("Th");
		FrogItems.decayBatteryPlutoium = new ItemDecayBattery("Pu");
		FrogItems.coolantAmmonia60K = new ItemAmmoniaCoolant("60K", 6000);
		FrogItems.coolantAmmonia180K = new ItemAmmoniaCoolant("180K", 18000);
		FrogItems.coolantAmmonia360K = new ItemAmmoniaCoolant("360K", 36000);
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
		
		GameRegistry.<Item>register(FrogItems.decayBatteryUranium);
		GameRegistry.<Item>register(FrogItems.decayBatteryThorium);
		GameRegistry.<Item>register(FrogItems.decayBatteryPlutoium);
		GameRegistry.<Item>register(FrogItems.coolantAmmonia60K);
		GameRegistry.<Item>register(FrogItems.coolantAmmonia180K);
		GameRegistry.<Item>register(FrogItems.coolantAmmonia360K);
		GameRegistry.<Item>register(FrogItems.itemIngot);
		GameRegistry.<Item>register(FrogItems.itemDust);
		GameRegistry.<Item>register(FrogItems.itemReactionModule);
		GameRegistry.<Item>register(FrogItems.ionCannon);
		GameRegistry.<Item>register(FrogItems.ionCannonFrame);
		GameRegistry.<Item>register(FrogItems.jinkela);
		GameRegistry.<Item>register(FrogItems.tiberium);
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("railgun", FrogItems.ionCannon);
		OreDictionary.registerOre("jinkela", FrogItems.jinkela);
		
		OreDictionary.registerOre("gemRuby", new ItemStack(FrogItems.itemIngot, 1, 2));
		OreDictionary.registerOre("gemSapphire", new ItemStack(FrogItems.itemIngot, 1, 3));
		OreDictionary.registerOre("gemGreenSapphire", new ItemStack(FrogItems.itemIngot, 1, 4));
		
		OreDictionary.registerOre("oreCarnallite", new ItemStack(FrogBlocks.frogOres, 1, 0));
		OreDictionary.registerOre("oreDewalquite", new ItemStack(FrogBlocks.frogOres, 1, 1));
		OreDictionary.registerOre("oreFluorapatite", new ItemStack(FrogBlocks.frogOres, 1, 2));
		
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
