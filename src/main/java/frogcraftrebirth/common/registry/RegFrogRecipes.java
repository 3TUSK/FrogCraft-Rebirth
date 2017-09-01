package frogcraftrebirth.common.registry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.config.ConfigMain;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegFrogRecipes {
	
	public static void init() {
		initOreDict();
		if (!ConfigMain.enableModpackCreationMode) {
			FrogAPI.managerCT.add(new CondenseTowerRecipe(100, 75, new FluidStack(FrogFluids.coalTar, 5), new FluidStack[] { new FluidStack(FrogFluids.benzene, 2), new FluidStack(FrogFluids.ammonia, 1), new FluidStack(FrogFluids.carbonOxide, 2) }));
			FrogAPI.managerCT.add(new CondenseTowerRecipe(10, 75, new FluidStack(FluidRegistry.getFluid("ic2air"), 10), new FluidStack[] { new FluidStack(FrogFluids.argon, 1), new FluidStack(FrogFluids.oxygen, 7), new FluidStack(FrogFluids.carbonDioxide, 2) }));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(IC2Items.getItem("dust", "coal"), new ItemStack(FrogRegistees.INGOT, 1, 4), new FluidStack(FrogFluids.coalTar, 50), 80, 48));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(FrogRegistees.DUST, 1, 2), new FluidStack(FrogFluids.carbonDioxide, 50), 100, 64));
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

		Recipes.advRecipes.addRecipe(new ItemStack(FrogRegistees.AMMONIA_COOLANT_60K), " T ", "TCT", " T ", 'T', "plateTin", 'C', new FluidStack(FrogFluids.ammonia, 1000));
		
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE, 1, 0)), null, true, new ItemStack(FrogRegistees.CRUSHED_DUST, 3, 0));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE, 1, 1)), null, true, new ItemStack(FrogRegistees.CRUSHED_DUST, 3, 1));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE, 1, 2)), null, true, new ItemStack(FrogRegistees.CRUSHED_DUST, 3, 2));
		
		NBTTagCompound oreWashingMetadata = new NBTTagCompound();
		oreWashingMetadata.setInteger("amount", 500);
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.CRUSHED_DUST, 1, 0)), oreWashingMetadata, true, new ItemStack(FrogRegistees.PURIFIED_DUST, 1, 0), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 2, 0), new ItemStack(Blocks.SAND));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.CRUSHED_DUST, 1, 1)), oreWashingMetadata, true, new ItemStack(FrogRegistees.PURIFIED_DUST, 1, 1), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 2, 1), IC2Items.getItem("dust", "stone"));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.CRUSHED_DUST, 1, 2)), oreWashingMetadata, true, new ItemStack(FrogRegistees.PURIFIED_DUST, 1, 2), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 2, 2), IC2Items.getItem("dust", "stone"));
	
		NBTTagCompound centrifugeMetadata = new NBTTagCompound();
		centrifugeMetadata.setInteger("minHeat", 500);
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.PURIFIED_DUST, 1, 0)), centrifugeMetadata, true, new ItemStack(FrogRegistees.DUST, 1, 4), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 1, 0));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.PURIFIED_DUST, 1, 1)), centrifugeMetadata, true, new ItemStack(FrogRegistees.DUST, 1, 6), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 1, 1));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.PURIFIED_DUST, 1, 2)), centrifugeMetadata, true, new ItemStack(FrogRegistees.DUST, 1, 7), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 1, 2));
		centrifugeMetadata.setInteger("minHeat", 1000);
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.CRUSHED_DUST, 1, 0)), centrifugeMetadata, true, new ItemStack(FrogRegistees.DUST, 1, 4), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 2, 0), IC2Items.getItem("dust", "small_lithium"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.CRUSHED_DUST, 1, 1)), centrifugeMetadata, true, new ItemStack(FrogRegistees.DUST, 1, 6), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 2, 1), IC2Items.getItem("dust", "stone"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.CRUSHED_DUST, 1, 2)), centrifugeMetadata, true, new ItemStack(FrogRegistees.DUST, 1, 7), new ItemStack(FrogRegistees.SMALL_PILE_DUST, 2, 2), IC2Items.getItem("dust", "stone"));
	
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.INGOT, 8, 4)), null, true, new ItemStack(FrogRegistees.INGOT, 1, 3));
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("jinkela", FrogRegistees.JINKELA);
		
		OreDictionary.registerOre("crystalTiberiumRed", new ItemStack(FrogRegistees.TIBERIUM, 1, 0));
		OreDictionary.registerOre("crystalTiberiumBlue", new ItemStack(FrogRegistees.TIBERIUM, 1, 1));
		OreDictionary.registerOre("crystalTiberiumGreen", new ItemStack(FrogRegistees.TIBERIUM, 1, 2));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(FrogRegistees.TIBERIUM, 1, 0));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(FrogRegistees.TIBERIUM, 1, 1));
		OreDictionary.registerOre("crystalTiberium", new ItemStack(FrogRegistees.TIBERIUM, 1, 2));
		
		OreDictionary.registerOre("oreCarnallite", new ItemStack(FrogRegistees.ORE, 1, 0));
		OreDictionary.registerOre("oreDewalquite", new ItemStack(FrogRegistees.ORE, 1, 1));
		OreDictionary.registerOre("oreFluorapatite", new ItemStack(FrogRegistees.ORE, 1, 2));
		
		OreDictionary.registerOre("dustCarnallite", new ItemStack(FrogRegistees.DUST, 1, 4));
		OreDictionary.registerOre("dustDewalquite", new ItemStack(FrogRegistees.DUST, 1, 6));
		OreDictionary.registerOre("dustFluorapatite", new ItemStack(FrogRegistees.DUST, 1, 7));
		
		OreDictionary.registerOre("dustTinyCarnallite", new ItemStack(FrogRegistees.SMALL_PILE_DUST, 1, 0));
		OreDictionary.registerOre("dustTinyDewalquite", new ItemStack(FrogRegistees.SMALL_PILE_DUST, 1, 1));
		OreDictionary.registerOre("dustTinyFluorapatite", new ItemStack(FrogRegistees.SMALL_PILE_DUST, 1, 2));
		
		OreDictionary.registerOre("dustVanadiumPentoxide", new ItemStack(FrogRegistees.DUST, 1, 14));
	}
}
