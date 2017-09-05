package frogcraftrebirth.common.registry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.FrogConfig;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.lib.AdvChemRecRecipe;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputFluidStack;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputItemStack;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputUniversalFluidCell;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputs;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Collections;

public class RegFrogRecipes {
	
	public static void init() {
		initOreDict();
		if (!FrogConfig.modpackMode) {
			// CaO + H2O -> Ca(OH)2
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.DUST, 1,2)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(new ItemStack(FrogRegistees.DUST, 1,3)), ItemStack.EMPTY, 20, 100, 0, 1));
			// Ca(OH)2 + CO2 -> CaCO3 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.DUST, 1, 3)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.carbonDioxide, 1000))), Arrays.asList(ItemStack.EMPTY, IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY, 20, 10, 0, 0));
			// N2 + 3H2 -> 2NH3
			ItemStack hydrogenCells = IC2Items.getItem("fluid_cell", "ic2hydrogen");
			hydrogenCells.setCount(3);
			ItemStack ammoniaCell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(ammoniaCell).fill(new FluidStack(FrogFluids.ammonia, 1000), true);
			ammoniaCell.setCount(2);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.nitrogen, 1000)), new FrogRecipeInputItemStack(hydrogenCells)), Collections.singleton(ammoniaCell), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 2), 150, 128, 0, 2));
			// CO2 + 2NH3 -> CO(NH2)2 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.carbonDioxide, 1000)), new FrogRecipeInputItemStack(ammoniaCell.copy())), Arrays.asList(new ItemStack(FrogRegistees.DUST, 1, 13), IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY,50, 40, 0, 2));
			// HNO3 + NH3 -> NH4NO3
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.nitricAcid, 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.ammonia, 1000))), Collections.singleton(new ItemStack(FrogRegistees.DUST, 1, 11)), ItemStack.EMPTY, 10, 10, 0, 2));
			//C+O2=CO2
			//Ca3(PO4)2 + 3SiO2 + 5 C == 3 CaSiO3 + 5CO + 2P
			//2CO+O2=2CO2
			//CaSiO3=CaO+SiO2
			//C->raw carbon fiber
			//Mg+Br2=MgBr2
			//Cl2+2K=2KCl
			//CaF2=Ca+F2
			//Ca+F2=CaF2
			//SO3+H2O->H2SO4  TODO: utilize oleum - Vexatos, I am looking at you!
			//2SO2+O2->2SO3

			FrogAPI.managerCT.add(new CondenseTowerRecipe(100, 75, new FluidStack(FrogFluids.coalTar, 25), new FluidStack[] { new FluidStack(FrogFluids.benzene, 2), new FluidStack(FrogFluids.ammonia, 3), new FluidStack(FrogFluids.carbonOxide, 5), new FluidStack(FrogFluids.methane, 10), FluidRegistry.getFluidStack("ic2hydrogen", 5) }));
			FrogAPI.managerCT.add(new CondenseTowerRecipe(10, 75, new FluidStack(FluidRegistry.getFluid("ic2air"), 12), new FluidStack[] { new FluidStack(FrogFluids.argon, 1), new FluidStack(FrogFluids.nitrogen, 7), new FluidStack(FrogFluids.oxygen, 2), new FluidStack(FrogFluids.carbonDioxide, 2) }));

			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(IC2Items.getItem("dust", "coal"), new ItemStack(FrogRegistees.INGOT, 1, 4), new FluidStack(FrogFluids.coalTar, 50), 80, 48));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(FrogRegistees.DUST, 1, 2), new FluidStack(FrogFluids.carbonDioxide, 50), 100, 64));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.STONE), new ItemStack(FrogRegistees.DUST, 1, 2), new FluidStack(FrogFluids.carbonDioxide, 50), 100, 64));

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
		Recipes.advRecipes.addRecipe(new ItemStack(FrogRegistees.JINKELA), "KKK", "PPP", "NNN", 'K', "ingotPotassium", 'P', "ingotPhosphorus", 'N', new FluidStack(FrogFluids.nitrogen, 1000));

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

		OreDictionary.registerOre("ingotPotassium", new ItemStack(FrogRegistees.INGOT, 1, 0));
		OreDictionary.registerOre("ingotPhosphorus", new ItemStack(FrogRegistees.INGOT, 1, 1));

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
