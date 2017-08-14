package frogcraftrebirth.common.registry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.config.ConfigMain;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RegFrogRecipes {
	
	public static void init() {
		initOreDict();
		if (!ConfigMain.enableModpackCreationMode) {
			defaultCraftingRecipe();
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(IC2Items.getItem("dust", "coal"), new ItemStack(RegFrogItemsBlocks.INGOT, 1, 4), new FluidStack(FrogFluids.coalTar, 50), 80, 48));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(RegFrogItemsBlocks.DUST, 1, 2), new FluidStack(FrogFluids.carbonDioxide, 50), 100, 64));
			FrogAPI.FUEL_REG.regFuelByproduct(new ItemStack(Items.COAL, 1, 0), FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct(new ItemStack(Items.COAL, 1, 1), FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct("dustCoal", FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct("dustCharcoal", FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct("dustSulfur", FrogFluids.sulfurDioxide);
		}
	}
	
	public static void postInit() {
		MPSUpgradeManager.INSTANCE.registerSolarUpgrade(IC2Items.getItem("te", "solar_generator"));
		MPSUpgradeManager.INSTANCE.registerStorageUpgrade(IC2Items.getItem("upgrade", "energy_storage"), 10000);
		MPSUpgradeManager.INSTANCE.registerVoltageUpgrades(IC2Items.getItem("upgrade", "transformer"), 1);
		
		FrogAPI.FUEL_REG.regFuel(new ItemStack(RegFrogItemsBlocks.INGOT, 1, 3), 16000);
		FrogAPI.FUEL_REG.regFuel(new ItemStack(RegFrogItemsBlocks.INGOT, 1, 4), 1800);
		
		FrogAPI.FUEL_REG.regFuel(IC2Items.getItem("dust", "sulfur"), ConfigMain.enableClassicMode ? 1600 : 1200);
		
		Recipes.advRecipes.addRecipe(new ItemStack(RegFrogItemsBlocks.AMMONIA_COOLANT_60K), " T ", "TCT", " T ", 'T', "plateTin", 'C', new FluidStack(FrogFluids.ammonia, 1000));
		
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.ORE, 1, 0)), null, true, new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 3, 0));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.ORE, 1, 1)), null, true, new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 3, 1));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.ORE, 1, 2)), null, true, new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 3, 2));
		
		NBTTagCompound oreWashingMetadata = new NBTTagCompound();
		oreWashingMetadata.setInteger("amount", 500);
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 1, 0)), oreWashingMetadata, true, new ItemStack(RegFrogItemsBlocks.PURIFIED_DUST, 1, 0), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 2, 0), new ItemStack(Blocks.SAND));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 1, 1)), oreWashingMetadata, true, new ItemStack(RegFrogItemsBlocks.PURIFIED_DUST, 1, 1), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 2, 1), IC2Items.getItem("dust", "stone"));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 1, 2)), oreWashingMetadata, true, new ItemStack(RegFrogItemsBlocks.PURIFIED_DUST, 1, 2), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 2, 2), IC2Items.getItem("dust", "stone"));
	
		NBTTagCompound centrifugeMetadata = new NBTTagCompound();
		centrifugeMetadata.setInteger("minHeat", 500);
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.PURIFIED_DUST, 1, 0)), centrifugeMetadata, true, new ItemStack(RegFrogItemsBlocks.DUST, 1, 4), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 1, 0));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.PURIFIED_DUST, 1, 1)), centrifugeMetadata, true, new ItemStack(RegFrogItemsBlocks.DUST, 1, 6), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 1, 1));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.PURIFIED_DUST, 1, 2)), centrifugeMetadata, true, new ItemStack(RegFrogItemsBlocks.DUST, 1, 7), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 1, 2));
		centrifugeMetadata.setInteger("minHeat", 1000);
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 1, 0)), centrifugeMetadata, true, new ItemStack(RegFrogItemsBlocks.DUST, 1, 4), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 2, 0), IC2Items.getItem("dust", "small_lithium"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 1, 1)), centrifugeMetadata, true, new ItemStack(RegFrogItemsBlocks.DUST, 1, 6), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 2, 1), IC2Items.getItem("dust", "stone"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.CRUSHED_DUST, 1, 2)), centrifugeMetadata, true, new ItemStack(RegFrogItemsBlocks.DUST, 1, 7), new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 2, 2), IC2Items.getItem("dust", "stone"));
	
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(RegFrogItemsBlocks.INGOT, 8, 4)), null, true, new ItemStack(RegFrogItemsBlocks.INGOT, 1, 3));
	}
	
	private static void defaultCraftingRecipe() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 0), "PMP", "CEC", "PAP", 'P', "plateDenseSteel", 'C', "circuitAdvanced", 'M', IC2Items.getItem("te", "magnetizer"), 'E', IC2Items.getItem("te", "extractor"), 'A', IC2Items.getItem("resource", "advanced_machine")));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 1), "#C#", "#P#", "#A#", '#', IC2Items.getItem("fluid_cell"), 'C', "circuitAdvanced", 'P', IC2Items.getItem("te", "pump"), 'A', IC2Items.getItem("resource", "advanced_machine")));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 2), "#C#", "#E#", "HAH", '#', IC2Items.getItem("fluid_cell"), 'C', "circuitAdvanced", 'E', IC2Items.getItem("te", "extractor"), 'A', IC2Items.getItem("resource", "advanced_machine"), 'H', new ItemStack(RegFrogItemsBlocks.REACTION_MODULE, 1, 0)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 3), "#P#", "#Z#", "CAC", '#', IC2Items.getItem("fluid_cell"), 'P', IC2Items.getItem("te", "pump"), 'Z', IC2Items.getItem("te", "compressor"), 'C', "circuitBasic", 'A', IC2Items.getItem("resource", "advanced_machine")));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.MPS), "ICI", "IBI", "IFI", 'I', "plateIron", 'C', "circuitAdvanced", 'B', IC2Items.getItem("te", "batbox"), 'F', IC2Items.getItem("te", "electric_furnace")));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.GENERATOR), "SSS", "PEP", "CGC", 'S', "plateSteel", 'P', IC2Items.getItem("crafting", "alloy"), 'E', IC2Items.getItem("te", "extractor"), 'C', IC2Items.getItem("fluid_cell"), 'G', IC2Items.getItem("te", "generator")));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1, 0), "MAM", "CPC", "CYC", 'M', IC2Items.getItem("mining_pipe", "pipe"), 'A', "circuitAdvanced", 'C', IC2Items.getItem("fluid_cell"), 'P', IC2Items.getItem("te", "pump"), 'Y', new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1, 1)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1 ,1), "SPS", "SPS", "SAS", 'S', "plateSteel", 'P', IC2Items.getItem("mining_pipe", "pipe"), 'A', IC2Items.getItem("resource", "advanced_machine")));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1 ,2), "SGS", "SPS", "SAS", 'S', "plateSteel", 'G', IC2Items.getItem("glass", "reinforced"), 'P', IC2Items.getItem("mining_pipe", "pipe"), 'A', IC2Items.getItem("resource", "advanced_machine")));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.AMMONIA_COOLANT_180K), "TTT", "CCC", "TTT", 'T', "plateTin", 'C', new ItemStack(RegFrogItemsBlocks.AMMONIA_COOLANT_60K)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.AMMONIA_COOLANT_360K), "TCT", "TDT", "TCT", 'T', "plateTin", 'D', "plateDenseCopper", 'C', new ItemStack(RegFrogItemsBlocks.AMMONIA_COOLANT_180K)));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.REACTION_MODULE, 1, 0), " B ", "SCS", " B ", 'B', IC2Items.getItem("crafting", "copper_boiler"), 'S', IC2Items.getItem("casing", "steel"), 'C', "circuitAdvanced"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.REACTION_MODULE, 1, 1), " M ", " C ", " E ", 'M', IC2Items.getItem("te", "magnetizer"), 'C', "circuitAdvanced", 'E', IC2Items.getItem("te", "electrolyzer")));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.REACTION_MODULE, 1, 2), "AVA", "VMV", "AVA", 'A', IC2Items.getItem("crafting", "alloy"), 'M', new ItemStack(RegFrogItemsBlocks.REACTION_MODULE, 1, 0), 'V', "dustVanadiumPentoxide"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.REACTION_MODULE, 1, 3), " I ", "IMI", " I ", 'I', "plateIron", 'M', new ItemStack(RegFrogItemsBlocks.REACTION_MODULE, 1, 0)));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.DUST, 1, 4), "DDD", "DDD", "DDD", 'D', "dustTinyCarnallite"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.DUST, 1, 6), "DDD", "DDD", "DDD", 'D', "dustTinyDewalquite"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RegFrogItemsBlocks.DUST, 1, 7), "DDD", "DDD", "DDD", 'D', "dustTinyFluorapatite"));
	
		GameRegistry.addShapelessRecipe(new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 9, 0), new ItemStack(RegFrogItemsBlocks.DUST, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 9, 1), new ItemStack(RegFrogItemsBlocks.DUST, 1, 6));
		GameRegistry.addShapelessRecipe(new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 9, 2), new ItemStack(RegFrogItemsBlocks.DUST, 1, 7));
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("railgun", RegFrogItemsBlocks.ION_CANNON);
		OreDictionary.registerOre("jinkela", RegFrogItemsBlocks.JINKELA);
		
		OreDictionary.registerOre("crystalTiberiumRed", new ItemStack(RegFrogItemsBlocks.TIBERIUM, 1, 0));
		OreDictionary.registerOre("crystalTiberiumBlue", new ItemStack(RegFrogItemsBlocks.TIBERIUM, 1, 1));
		OreDictionary.registerOre("crystalTiberiumGreen", new ItemStack(RegFrogItemsBlocks.TIBERIUM, 1, 2));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(RegFrogItemsBlocks.TIBERIUM, 1, 0));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(RegFrogItemsBlocks.TIBERIUM, 1, 1));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(RegFrogItemsBlocks.TIBERIUM, 1, 2));
		
		OreDictionary.registerOre("oreCarnallite", new ItemStack(RegFrogItemsBlocks.ORE, 1, 0));
		OreDictionary.registerOre("oreDewalquite", new ItemStack(RegFrogItemsBlocks.ORE, 1, 1));
		OreDictionary.registerOre("oreFluorapatite", new ItemStack(RegFrogItemsBlocks.ORE, 1, 2));
		
		OreDictionary.registerOre("dustCarnallite", new ItemStack(RegFrogItemsBlocks.DUST, 1, 4));
		OreDictionary.registerOre("dustDewalquite", new ItemStack(RegFrogItemsBlocks.DUST, 1, 6));
		OreDictionary.registerOre("dustFluorapatite", new ItemStack(RegFrogItemsBlocks.DUST, 1, 7));
		
		OreDictionary.registerOre("dustTinyCarnallite", new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 1, 0));
		OreDictionary.registerOre("dustTinyDewalquite", new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 1, 1));
		OreDictionary.registerOre("dustTinyFluorapatite", new ItemStack(RegFrogItemsBlocks.SMALL_PILE_DUST, 1, 2));
		
		OreDictionary.registerOre("dustVanadiumPentoxide", new ItemStack(RegFrogItemsBlocks.DUST, 1, 14));
	}
}
