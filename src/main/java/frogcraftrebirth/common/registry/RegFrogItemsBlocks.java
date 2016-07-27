package frogcraftrebirth.common.registry;

import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.FrogBlocks;
import frogcraftrebirth.common.FrogItems;
import frogcraftrebirth.common.block.BlockCondenseTower;
import frogcraftrebirth.common.block.BlockFrogOre;
import frogcraftrebirth.common.block.BlockGenerator;
import frogcraftrebirth.common.block.BlockHSU;
import frogcraftrebirth.common.block.BlockMPS;
import frogcraftrebirth.common.block.BlockMachine;
import frogcraftrebirth.common.block.BlockTiberium;
import frogcraftrebirth.common.item.ItemAmmoniaCoolant;
import frogcraftrebirth.common.item.ItemCatalystModule;
import frogcraftrebirth.common.item.ItemDecayBattery;
import frogcraftrebirth.common.item.ItemDust;
import frogcraftrebirth.common.item.ItemIngot;
import frogcraftrebirth.common.item.ItemIonCannon;
import frogcraftrebirth.common.item.ItemJinkela;
import frogcraftrebirth.common.item.ItemMPS;
import frogcraftrebirth.common.item.ItemTiberium;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import frogcraftrebirth.common.lib.item.ItemFrogCraft;
import frogcraftrebirth.common.tile.TileAdvChemReactor;
import frogcraftrebirth.common.tile.TileAirPump;
import frogcraftrebirth.common.tile.TileCombustionFurnace;
import frogcraftrebirth.common.tile.TileCondenseTower;
import frogcraftrebirth.common.tile.TileFluidOutputHatch;
import frogcraftrebirth.common.tile.TileHSU;
import frogcraftrebirth.common.tile.TileHSUUltra;
import frogcraftrebirth.common.tile.TileLiquefier;
import frogcraftrebirth.common.tile.TileMobilePowerStation;
import frogcraftrebirth.common.tile.TilePyrolyzer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegFrogItemsBlocks {
	
	public static void preInit() {
		initBlocks();
		initItems();
		initTileEntity();
	}
	
	public static void init() {
		//NOTHING
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
		FrogBlocks.hybridStorageUnit = new BlockHSU();
		FrogBlocks.mobilePowerStation = new BlockMPS();
		
		GameRegistry.<Block>register(FrogBlocks.frogOres);
		GameRegistry.<Block>register(FrogBlocks.tiberium);
		GameRegistry.<Block>register(FrogBlocks.generators);
		GameRegistry.<Block>register(FrogBlocks.machines);
		GameRegistry.<Block>register(FrogBlocks.condenseTowerPart);
		GameRegistry.<Block>register(FrogBlocks.hybridStorageUnit);
		GameRegistry.<Block>register(FrogBlocks.mobilePowerStation);
		
		ItemFrogBlock.registerItemBlockFor(FrogBlocks.condenseTowerPart, new ItemFrogBlock(FrogBlocks.condenseTowerPart, (ItemStack aStack) -> {
			return BlockCondenseTower.Part.values()[aStack.getMetadata() & 0b11].getName();
		}));
		ItemFrogBlock.registerItemBlockFor(FrogBlocks.frogOres, new ItemFrogBlock(FrogBlocks.frogOres, (ItemStack aStack) -> {
			return BlockFrogOre.Type.values()[aStack.getMetadata()].getName();
		}));
		ItemFrogBlock.registerItemBlockFor(FrogBlocks.generators, new ItemFrogBlock(FrogBlocks.generators, (ItemStack aStack) -> {
			return "combustionFurnace";
		}));
		ItemFrogBlock.registerItemBlockFor(FrogBlocks.hybridStorageUnit, new ItemFrogBlock(FrogBlocks.hybridStorageUnit, (ItemStack aStack) -> {
			return BlockHSU.Level.values()[aStack.getMetadata() / 6].getName();
		}));
		ItemFrogBlock.registerItemBlockFor(FrogBlocks.machines, new ItemFrogBlock(FrogBlocks.machines, (ItemStack aStack) -> {
			return BlockMachine.Type.values()[aStack.getMetadata() & 0b11].getName();
		}));
		ItemFrogBlock.registerItemBlockFor(FrogBlocks.tiberium, new ItemFrogBlock(FrogBlocks.tiberium, (ItemStack aStack) -> {
			return BlockTiberium.Color.values()[aStack.getMetadata()].getName();
		}));
		ItemFrogBlock.registerItemBlockFor(FrogBlocks.mobilePowerStation, new ItemMPS((BlockMPS)FrogBlocks.mobilePowerStation));
	}

	static void initItems() {
		FrogItems.decayBatteryUranium = new ItemDecayBattery("U").setRegistryName("uranium_decay_battery");
		FrogItems.decayBatteryThorium = new ItemDecayBattery("Th").setRegistryName("thorium_decay_battery");
		FrogItems.decayBatteryPlutoium = new ItemDecayBattery("Pu").setRegistryName("plutoium_decay_battery");
		FrogItems.coolantAmmonia60K = new ItemAmmoniaCoolant("60K", 6000).setRegistryName("ammonia_coolant_60k");
		FrogItems.coolantAmmonia180K = new ItemAmmoniaCoolant("180K", 18000).setRegistryName("ammonia_coolant_180k");
		FrogItems.coolantAmmonia360K = new ItemAmmoniaCoolant("360K", 36000).setRegistryName("ammonia_coolant_360k");
		FrogItems.itemIngot = new ItemIngot().setRegistryName("ingots");
		FrogItems.itemDust = new ItemDust().setRegistryName("dusts");
		FrogItems.itemReactionModule = new ItemCatalystModule().setRegistryName("catalyst_module");
		FrogItems.ionCannon = new ItemIonCannon(1000000).setRegistryName("ion_cannon");
		FrogItems.ionCannonFrame = new ItemFrogCraft(false) {

			@Override
			public List<String> getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
				return java.util.Arrays.asList("Unimplement yet");
			}
			
		}.setUnlocalizedName("ionCannonFrame").setCreativeTab(FrogAPI.frogTab).setMaxStackSize(1).setRegistryName("ion_cannon_frame");
		FrogItems.jinkela = new ItemJinkela().setRegistryName("jinkela");
		FrogItems.tiberium = new ItemTiberium().setRegistryName("tiberium");
		
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
	
	static void initTileEntity() {
		GameRegistry.registerTileEntity(TileMobilePowerStation.class, "FrogCraft_MobilePowerStation");
		GameRegistry.registerTileEntity(TileHSU.class, "FrogCraft_HybridStorageUnit");
		GameRegistry.registerTileEntity(TileHSUUltra.class, "FrogCraft_UltraHybridStorageUnit");
		GameRegistry.registerTileEntity(TileAirPump.class, "FrogCraft_AirPump");
		GameRegistry.registerTileEntity(TileCondenseTower.class, "FrogCraft_CondenseTowerCore");
		GameRegistry.registerTileEntity(TileFluidOutputHatch.class, "FrogCraft_CondenseTowerFluidOutput");
		GameRegistry.registerTileEntity(TileCombustionFurnace.class, "FrogCraft_CombustionFurnace");
		GameRegistry.registerTileEntity(TilePyrolyzer.class, "FrogCraft_ThermalCracker");
		GameRegistry.registerTileEntity(TileAdvChemReactor.class, "FrogCraft_AdvancedChemicalReactor");
		GameRegistry.registerTileEntity(TileLiquefier.class, "FrogCraft_Liquefier");
	}
}
