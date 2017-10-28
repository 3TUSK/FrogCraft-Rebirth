package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.FrogConfig;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.lib.AdvBlastFurnaceRecipe;
import frogcraftrebirth.common.lib.AdvChemRecRecipe;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputItemStack;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputOreDict;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputUniversalFluidCell;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputs;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Collections;

public class FrogRecipes {
	
	public static void init() {
		initOreDict();
		if (!FrogConfig.modpackMode) {
			// --- Begin of old FrogCraft recipes, Part 1 ---
			// CaO + H2O -> Ca(OH)2
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.NON_METAL_DUST, 1,4)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(new ItemStack(FrogRegistees.NON_METAL_DUST, 1,6)), ItemStack.EMPTY, 20, 100, 0, 1));
			// Ca(OH)2 + CO2 -> CaCO3 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.NON_METAL_DUST, 1,6)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.carbonDioxide, 1000))), Arrays.asList(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 1), IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY, 20, 10, 0, 0));
			// N2 + 3H2 -> 2NH3
			ItemStack hydrogenCells = IC2Items.getItem("fluid_cell", "ic2hydrogen");
			hydrogenCells.setCount(3);
			ItemStack ammoniaCell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(ammoniaCell).fill(new FluidStack(FrogFluids.ammonia, 1000), true);
			ammoniaCell.setCount(2);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.nitrogen, 1000)), new FrogRecipeInputItemStack(hydrogenCells)), Collections.singleton(ammoniaCell), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 2), 150, 128, 0, 2));
			// CO2 + 2NH3 -> CO(NH2)2 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.carbonDioxide, 1000)), new FrogRecipeInputItemStack(ammoniaCell.copy())), Arrays.asList(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 7), IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY,50, 40, 0, 2));
			// HNO3 + NH3 -> NH4NO3
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.nitricAcid, 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.ammonia, 1000))), Collections.singleton(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 0)), ItemStack.EMPTY, 10, 10, 0, 2));
			//SO3+H2O->H2SO4
			ItemStack sulfuricCell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(sulfuricCell).fill(new FluidStack(FrogFluids.sulfuricAcid, 1000), true);
			sulfuricCell.setCount(1);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.sulfurTrioxide, 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(sulfuricCell), ItemStack.EMPTY, 1200, 128, 0, 1));
			//2SO2+O2->2SO3
			ItemStack so3Cell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(so3Cell).fill(new FluidStack(FrogFluids.sulfurTrioxide, 1000), true);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.sulfurDioxide, 1000)), new FrogRecipeInputItemStack(IC2Items.getItem("fluid_cell", "ic2oxygen"))), Collections.singleton(so3Cell), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 3), 300, 100, 0, 1));

			FrogAPI.managerCT.add(new CondenseTowerRecipe(100, 75, new FluidStack(FrogFluids.coalTar, 25), new FluidStack[] { new FluidStack(FrogFluids.benzene, 2), new FluidStack(FrogFluids.ammonia, 3), new FluidStack(FrogFluids.carbonOxide, 5), new FluidStack(FrogFluids.methane, 10), FluidRegistry.getFluidStack("ic2hydrogen", 5) }));
			FrogAPI.managerCT.add(new CondenseTowerRecipe(10, 75, new FluidStack(FluidRegistry.getFluid("ic2air"), 12), new FluidStack[] { new FluidStack(FrogFluids.argon, 1), new FluidStack(FrogFluids.nitrogen, 7), new FluidStack(FluidRegistry.getFluid("ic2oxygen"), 2), new FluidStack(FrogFluids.carbonDioxide, 2) }));

			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(IC2Items.getItem("dust", "coal"), new ItemStack(FrogRegistees.INFLAMMABLE, 1, 4), new FluidStack(FrogFluids.coalTar, 50), 80, 48));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 4), new FluidStack(FrogFluids.carbonDioxide, 50), 100, 64));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.STONE), new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 4), new FluidStack(FrogFluids.carbonDioxide, 50), 100, 64));

			//C+O2=CO2
			FrogAPI.FUEL_REG.regFuelByproduct(new ItemStack(Items.COAL, 1, 0), FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct(new ItemStack(Items.COAL, 1, 1), FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct("dustCoal", FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct("dustCharcoal", FrogFluids.carbonDioxide);
			FrogAPI.FUEL_REG.regFuelByproduct("dustCarbon", FrogFluids.carbonDioxide);
			//S+O2=SO2
			FrogAPI.FUEL_REG.regFuelByproduct("dustSulfur", FrogFluids.sulfurDioxide);

			// TODO implements 2CO+O2=2CO2 as a combustion furnace byproduct
			// --- End of old FrogCraft recipes, Part 1 ---

			// --- Begin of old FrogCraft recipes, Part 2 --
			// These recipes are used for matching GregTech style. By default, they should be disabled.
			// Not all of recipes from old FrogCraft are implemented, due to the design guideline.
			// TODO Master switch
			if (Loader.isModLoaded("techreborn") || Loader.isModLoaded("gregtech")) {
				//Ca3(PO4)2 + 3SiO2 + 5 C == 3 CaSiO3 + 5CO + 2P
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputOreDict("dustPhosphor", 1), new FrogRecipeInputOreDict("dustSilica", 3), new FrogRecipeInputOreDict("dustCarbon", 5)), Arrays.asList(new ItemStack(FrogRegistees.NON_METAL_DUST, 3, 2), ItemStack.EMPTY, new ItemStack(FrogRegistees.INFLAMMABLE, 2, 2)), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 0), 350, 128, 5, 0));
				//CaSiO3=CaO+SiO2
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Collections.singleton(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 2))), Arrays.asList(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 4), new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 5)), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 0), 400, 128, 0, 0));
				//C->raw carbon fiber
				//Mg+Br2=MgBr2
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputOreDict("dustMagnesium", 1), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.bromine, 1000))), Collections.singleton(new ItemStack(FrogRegistees.INTERMEDIATE, 1, 2)), ItemStack.EMPTY, 10, 30, 0, 1));
				//Cl2+2K=2KCl
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputOreDict("potassium", 2), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.chlorine, 1000))), Collections.singleton(new ItemStack(FrogRegistees.INTERMEDIATE, 2, 3)), ItemStack.EMPTY, 10, 30, 0, 1));
			}
			// --- End of old FrogCraft recipes, Part 2 ---

			// --- Begin of FrogCraft: Rebirth recipes --
			// These recipes are added by FrogCraft: Rebirth.
			// It is the existence of these recipes that makes FrogCraft: Rebirth still standing as one
			// of the only few chemical engineering mods known in the community.

			// H2SO4(l) + SO3(g) -> H2S2O7(l), i.e. oleum
			ItemStack oleumCell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(oleumCell).fill(new FluidStack(FrogFluids.oleum, 1000), true);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.sulfuricAcid, 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.sulfurTrioxide, 1000))), Collections.singleton(oleumCell), ItemStack.EMPTY, 200, 10, 0, 1));
			// H2S2O7 + H2O -> 2H2SO4, mimicking contact process https://en.wikipedia.org/wiki/Contact_process
			ItemStack sulfuricCell_2 = sulfuricCell.copy();
			sulfuricCell_2.setCount(2);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.oleum, 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(sulfuricCell_2), ItemStack.EMPTY, 100, 10, 0,0));
			// 2KCl + 2H2O -> 2KOH + H2(g) + Cl2(g), manufacturing potassium hydroxide
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.INTERMEDIATE, 2, 2)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 2000))), Collections.EMPTY_LIST, new ItemStack(FrogRegistees.REACTION_MODULE, 1, 1), 600, 512, 0, 0));
			// Saponification

			// 2Al2O3 + 3C -> 4Al+ 3CO2, electrolysis
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.INTERMEDIATE, 2, 0)), new FrogRecipeInputOreDict("dustCarbon", 3)), Arrays.asList(new ItemStack(FrogRegistees.METAL_DUST, 4, 0), ItemStack.EMPTY), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 1), 1200, 512, 3, 0));
			// TiO2 + 2H2 -> Ti + 2H2O, awaiting feature/adv-blast-furnace merged
			FrogAPI.managerABF.add(new AdvBlastFurnaceRecipe(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.INTERMEDIATE, 1, 5)), FrogRecipeInputs.EMPTY, FluidRegistry.getFluidStack("ic2hydrogen", 2000), new ItemStack(FrogRegistees.METAL_INGOT, 1, 2), ItemStack.EMPTY, FrogFluids.argon, 300, 0));

			/* TODO: Implement at least one of following:
			 * Ca5(PO4)3F + 5 H2SO4 + 10 H2O → 3 H3PO4 + 5 CaSO4·2 H2O + HF
			 * 4 Ca5(PO4)3F + 21 SiO2 + 30 C → 20 CaSiO3 + 30 CO + SiF4 + 6 P2
			 */
			// --- End of FrogCraft: Rebirth recipes --
		}
	}
	
	public static void postInit() {
		MPSUpgradeManager.INSTANCE.registerSolarUpgrade(IC2Items.getItem("te", "solar_generator"));
		MPSUpgradeManager.INSTANCE.registerStorageUpgrade(IC2Items.getItem("upgrade", "energy_storage"), 10000);
		MPSUpgradeManager.INSTANCE.registerVoltageUpgrades(IC2Items.getItem("upgrade", "transformer"), 1);

		Recipes.advRecipes.addRecipe(new ItemStack(FrogRegistees.AMMONIA_COOLANT_60K), " T ", "TCT", " T ", 'T', "plateTin", 'C', new FluidStack(FrogFluids.ammonia, 1000));
		Recipes.advRecipes.addRecipe(new ItemStack(FrogRegistees.JINKELA), "KKK", "PPP", "NNN", 'K', "ingotPotassium", 'P', "ingotPhosphorus", 'N', new FluidStack(FrogFluids.nitrogen, 1000));

		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE, 1, 0)), null, true, new ItemStack(FrogRegistees.ORE_CRUSHED, 3, 0));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE, 1, 1)), null, true, new ItemStack(FrogRegistees.ORE_CRUSHED, 3, 1));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE, 1, 2)), null, true, new ItemStack(FrogRegistees.ORE_CRUSHED, 3, 2));
		
		NBTTagCompound oreWashingMetadata = new NBTTagCompound();
		oreWashingMetadata.setInteger("amount", 500);
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 0)), oreWashingMetadata, true, new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 0), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 0), new ItemStack(Blocks.SAND));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 1)), oreWashingMetadata, true, new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 1), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 1), IC2Items.getItem("dust", "stone"));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 2)), oreWashingMetadata, true, new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 2), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 2), IC2Items.getItem("dust", "stone"));
	
		NBTTagCompound centrifugeMetadata = new NBTTagCompound();
		centrifugeMetadata.setInteger("minHeat", 500);
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 0)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 0), new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 0));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 1)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 1), new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 1));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 2)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 2), new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 2));
		centrifugeMetadata.setInteger("minHeat", 1000);
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 0)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 0), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 0), IC2Items.getItem("dust", "small_lithium"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 1)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 1), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 1), IC2Items.getItem("dust", "stone"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 2)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 2), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 2), IC2Items.getItem("dust", "stone"));
	
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.INFLAMMABLE, 8, 4)), null, true, new ItemStack(FrogRegistees.INFLAMMABLE, 1, 0));
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("jinkela", FrogRegistees.JINKELA);

		OreDictionary.registerOre("ingotPotassium", new ItemStack(FrogRegistees.INFLAMMABLE, 1, 3));
		OreDictionary.registerOre("ingotPhosphorus", new ItemStack(FrogRegistees.INFLAMMABLE, 1, 2));
		
		OreDictionary.registerOre("oreCarnallite", new ItemStack(FrogRegistees.ORE, 1, 0));
		OreDictionary.registerOre("oreDewalquite", new ItemStack(FrogRegistees.ORE, 1, 1));
		OreDictionary.registerOre("oreFluorapatite", new ItemStack(FrogRegistees.ORE, 1, 2));
		
		OreDictionary.registerOre("dustCarnallite", new ItemStack(FrogRegistees.ORE_DUST, 1, 0));
		OreDictionary.registerOre("dustDewalquite", new ItemStack(FrogRegistees.ORE_DUST, 1, 1));
		OreDictionary.registerOre("dustFluorapatite", new ItemStack(FrogRegistees.ORE_DUST, 1, 2));
		
		OreDictionary.registerOre("dustTinyCarnallite", new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 0));
		OreDictionary.registerOre("dustTinyDewalquite", new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 1));
		OreDictionary.registerOre("dustTinyFluorapatite", new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 2));
		
		OreDictionary.registerOre("dustVanadiumPentoxide", new ItemStack(FrogRegistees.INTERMEDIATE, 1, 6));
	}
}
