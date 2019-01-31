/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogGameObjects;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.common.lib.AdvBlastFurnaceRecipe;
import frogcraftrebirth.common.lib.AdvChemRecRecipe;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputItemStack;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputOreDict;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputUniversalFluidCell;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputs;
import frogcraftrebirth.common.lib.util.FluidStackFactory;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Collections;

public class FrogRecipes {
	
	public static void init() {
		if (FrogConfig.modpackOptions.enableRecipes) {
			FluidStackFactory fluidFactory = new FluidStackFactory();
			// --- Begin of old FrogCraft recipes, Part 1 ---
			// CaO + H2O -> Ca(OH)2
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.QUICKLIME_DUST)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(new ItemStack(FrogGameObjects.SLAKED_LIME_DUST)), ItemStack.EMPTY, 20, 100, 0, 1));
			// Ca(OH)2 + CO2 -> CaCO3 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.SLAKED_LIME_DUST)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.carbonDioxide, 1000))), Arrays.asList(new ItemStack(FrogGameObjects.CALCITE_DUST), IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY, 20, 10, 0, 0));
			// N2 + 3H2 -> 2NH3
			ItemStack hydrogenCells = IC2Items.getItem("fluid_cell", "ic2hydrogen");
			hydrogenCells.setCount(3);
			ItemStack ammoniaCell = fluidFactory.createCell("ammonia", 2);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("nitrogen", 1000)), new FrogRecipeInputItemStack(hydrogenCells)), Collections.singleton(ammoniaCell), new ItemStack(FrogGameObjects.AMMONIA_CATALYST_MODULE), 150, 128, 0, 2));
			// CO2 + 2NH3 -> CO(NH2)2 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("carbon_dioxide", 1000)), new FrogRecipeInputItemStack(ammoniaCell.copy())), Arrays.asList(new ItemStack(FrogGameObjects.UREA_DUST), IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY,50, 40, 0, 2));
			// HNO3 + NH3 -> NH4NO3
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("nitric_acid", 1000)), new FrogRecipeInputUniversalFluidCell(fluidFactory.create("ammonia", 1000))), Collections.singleton(new ItemStack(FrogGameObjects.AMMONIUM_NITRATE_DUST)), ItemStack.EMPTY, 10, 10, 0, 2));
			//SO3+H2O->H2SO4
			ItemStack sulfuricCell = fluidFactory.createCell("sulfuric_acid", 1);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfur_trioxide", 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(sulfuricCell), ItemStack.EMPTY, 1200, 128, 0, 1));
			//2SO2+O2->2SO3
			ItemStack so3Cell = fluidFactory.createCell("sulfur_trioxide", 1);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfur_dioxide", 1000)), new FrogRecipeInputItemStack(IC2Items.getItem("fluid_cell", "ic2oxygen"))), Collections.singleton(so3Cell), new ItemStack(FrogGameObjects.SULFUR_TRIOXIDE_MODULE), 300, 100, 0, 1));

			FrogAPI.managerCT.add(new CondenseTowerRecipe(100, 75, fluidFactory.create("coal_tar", 25), new FluidStack[] { fluidFactory.create("benzene", 2), fluidFactory.create("ammonia", 3), fluidFactory.create("carbon_oxide", 5), fluidFactory.create("methane", 10), fluidFactory.create("ic2hydrogen", 5) }));
			FrogAPI.managerCT.add(new CondenseTowerRecipe(10, 75, fluidFactory.create("liquefied_air", 12), new FluidStack[] { fluidFactory.create("argon", 1), fluidFactory.create("nitrogen", 7), fluidFactory.create("ic2oxygen", 2), fluidFactory.create("carbon_dioxide", 2) }));

			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new FrogRecipeInputOreDict("dustCoal", 1), new ItemStack(FrogGameObjects.SHATTERED_COAL_COKE), fluidFactory.create("coal_tar", 50), 80, 48));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new FrogRecipeInputItemStack(new ItemStack(Blocks.COBBLESTONE)), new ItemStack(FrogGameObjects.QUICKLIME_DUST), fluidFactory.create("carbon_dioxide", 50), 100, 64));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new FrogRecipeInputItemStack(new ItemStack(Blocks.STONE)), new ItemStack(FrogGameObjects.QUICKLIME_DUST), fluidFactory.create("carbon_dioxide", 50), 100, 64));

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
			if (FrogConfig.compatibilityOptions.enableTechRebornCompatibility && Loader.isModLoaded("techreborn")) {
				//Ca3(PO4)2 + 3SiO2 + 5 C == 3 CaSiO3 + 5CO + 2P
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputOreDict("dustPhosphor", 1), new FrogRecipeInputOreDict("dustSilica", 3), new FrogRecipeInputOreDict("dustCarbon", 5)), Arrays.asList(new ItemStack(FrogGameObjects.CALCIUM_SILICATE_DUST), ItemStack.EMPTY, new ItemStack(FrogGameObjects.PHOSPHORUS)), new ItemStack(FrogGameObjects.HEATING_MODULE), 350, 128, 5, 0));
				//CaSiO3=CaO+SiO2
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Collections.singleton(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.CALCIUM_SILICATE_DUST))), Arrays.asList(new ItemStack(FrogGameObjects.QUICKLIME_DUST), new ItemStack(FrogGameObjects.SILICA_DUST)), new ItemStack(FrogGameObjects.HEATING_MODULE), 400, 128, 0, 0));
				//C->raw carbon fiber
				//Mg+Br2=MgBr2
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputOreDict("dustMagnesium", 1), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.bromine, 1000))), Collections.singleton(new ItemStack(FrogGameObjects.MAGNESIUM_BROMIDE_DUST)), ItemStack.EMPTY, 10, 30, 0, 1));
				//Cl2+2K=2KCl
				FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputOreDict("potassium", 2), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.chlorine, 1000))), Collections.singleton(new ItemStack(FrogGameObjects.POTASSIUM_CHLORIDE_DUST, 2)), ItemStack.EMPTY, 10, 30, 0, 1));
			}
			// --- End of old FrogCraft recipes, Part 2 ---

			// --- Begin of FrogCraft: Rebirth recipes --
			// These recipes are added by FrogCraft: Rebirth.
			// It is the existence of these recipes that makes FrogCraft: Rebirth still standing as one
			// of the only few chemical engineering mods known in the community.

			// H2SO4(l) + SO3(g) -> H2S2O7(l), i.e. oleum
			ItemStack oleumCell = fluidFactory.createCell("oleum", 1);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfuric_acid", 1000)), new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfur_trioxide", 1000))), Collections.singleton(oleumCell), ItemStack.EMPTY, 200, 10, 0, 1));
			// H2S2O7 + H2O -> 2H2SO4, mimicking contact process https://en.wikipedia.org/wiki/Contact_process
			ItemStack sulfuricCell_2 = sulfuricCell.copy();
			sulfuricCell_2.setCount(2);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("oleum", 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(sulfuricCell_2), ItemStack.EMPTY, 100, 10, 0,0));
			// 2KCl + 2H2O -> 2KOH + H2(g) + Cl2(g), manufacturing potassium hydroxide
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.POTASSIUM_CHLORIDE_DUST, 2)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 2000))), Collections.emptyList(), new ItemStack(FrogGameObjects.ELECTROLYSIS_MODULE), 600, 512, 0, 0));

			// 2Al2O3 + 3C -> 4Al+ 3CO2, electrolysis
			ItemStack co2Cell = fluidFactory.createCell("carbon_dioxide", 3);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.ALUMINIUM_OXIDE_DUST, 2)), new FrogRecipeInputOreDict("dustCoal", 3)), Arrays.asList(new ItemStack(FrogGameObjects.ALUMINIUM_DUST, 4), co2Cell), new ItemStack(FrogGameObjects.ELECTROLYSIS_MODULE), 1200, 512, 3, 0));

			// TiO2 + 2H2 -> Ti + 2H2O
			FrogAPI.managerABF.add(new AdvBlastFurnaceRecipe(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.TITANIUM_OXIDE_DUST)), FrogRecipeInputs.EMPTY, FluidRegistry.getFluidStack("ic2hydrogen", 2000), new ItemStack(FrogGameObjects.TITANIUM_INGOT), ItemStack.EMPTY, FrogFluids.argon, 300, 0));

			// Temperary Aluminium smelting
			FrogAPI.managerABF.add(new AdvBlastFurnaceRecipe(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.ALUMINIUM_DUST)), FrogRecipeInputs.EMPTY, null, new ItemStack(FrogGameObjects.ALUMINIUM_INGOT), ItemStack.EMPTY, null, 200, 0));

			/* TODO: Implement at least one of following:
			 * Ca5(PO4)3F + 5 H2SO4 + 10 H2O → 3 H3PO4 + 5 CaSO4·2 H2O + HF
			 * 4 Ca5(PO4)3F + 21 SiO2 + 30 C → 20 CaSiO3 + 30 CO + SiF4 + 6 P2
			 */

			// Saponification
			ItemStack glycerolCells = fluidFactory.createCell("glycerol", 3);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogGameObjects.LIPID)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.potassiumHydroxide, 3000))), Arrays.asList(new ItemStack(FrogGameObjects.SOAP), glycerolCells), new ItemStack(FrogGameObjects.HEATING_MODULE), 100, 20, 0, 0));
			// --- End of FrogCraft: Rebirth recipes --
		}
	}
	
	public static void postInit() {
		MPSUpgradeManager.INSTANCE.registerSolarUpgrade(IC2Items.getItem("te", "solar_generator"));
		MPSUpgradeManager.INSTANCE.registerStorageUpgrade(IC2Items.getItem("upgrade", "energy_storage"), 10000);
		MPSUpgradeManager.INSTANCE.registerVoltageUpgrades(IC2Items.getItem("upgrade", "transformer"), 1);

		Recipes.advRecipes.addRecipe(new ItemStack(FrogGameObjects.AMMONIA_COOLANT_60K), " T ", "TCT", " T ", 'T', "plateTin", 'C', FluidRegistry.getFluidStack("ammonia", 1000));
		Recipes.advRecipes.addRecipe(new ItemStack(FrogGameObjects.JINKELA), "KKK", "PPP", "NNN", 'K', new ItemStack(FrogGameObjects.POTASSIUM), 'P', new ItemStack(FrogGameObjects.PHOSPHORUS), 'N', FluidRegistry.getFluidStack("nitrogen", 1000));

		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CARNALLITE)), null, false, new ItemStack(FrogGameObjects.CRUSHED_CARNALLITE_ORE, 3, 0));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.DEWALQUITE)), null, false, new ItemStack(FrogGameObjects.CRUSHED_DEWALQUITE_ORE, 3, 1));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.FLUORAPATITE)), null, false, new ItemStack(FrogGameObjects.CRUSHED_FLUORAPATITE_ORE, 3, 2));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("ingotAluminium"), null, false, new ItemStack(FrogGameObjects.ALUMINIUM_DUST));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("ingotMagnalium"), null, false, new ItemStack(FrogGameObjects.MAGNALIUM_DUST));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("ingotTitanium"), null, false, new ItemStack(FrogGameObjects.TITANIUM_DUST));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("ingotMagnesium"), null, false, new ItemStack(FrogGameObjects.MAGNESIUM_DUST));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateDenseAluminium"), null, false, new ItemStack(FrogGameObjects.ALUMINIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateDenseMagnalium"), null, false, new ItemStack(FrogGameObjects.MAGNALIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateDenseTitanium"), null, false, new ItemStack(FrogGameObjects.TITANIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateDenseMagnesium"), null, false, new ItemStack(FrogGameObjects.MAGNESIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateAluminium"), null, false, new ItemStack(FrogGameObjects.TINY_ALUMINIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateMagnalium"), null, false, new ItemStack(FrogGameObjects.TINY_MAGNALIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateTitanium"), null, false, new ItemStack(FrogGameObjects.TINY_TITANIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forOreDict("plateMagnesium"), null, false, new ItemStack(FrogGameObjects.TINY_MAGNESIUM_DUST, 8));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.ALUMINIUM_CASING)), null, false, new ItemStack(FrogGameObjects.TINY_ALUMINIUM_DUST, 4));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNALIUM_CASING)), null, false, new ItemStack(FrogGameObjects.TINY_MAGNALIUM_DUST, 4));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TITANIUM_CASING)),null, false, new ItemStack(FrogGameObjects.TINY_TITANIUM_DUST, 4));
		Recipes.macerator.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNESIUM_CASING)), null, false, new ItemStack(FrogGameObjects.TINY_MAGNESIUM_DUST, 4));

		NBTTagCompound oreWashingMetadata = new NBTTagCompound();
		oreWashingMetadata.setInteger("amount", 500);
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CRUSHED_CARNALLITE_ORE)), oreWashingMetadata, false, new ItemStack(FrogGameObjects.PURIFIED_CARNALLITE_ORE), new ItemStack(FrogGameObjects.TINY_CARNALLITE_DUST, 2), new ItemStack(Blocks.SAND));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CRUSHED_DEWALQUITE_ORE)), oreWashingMetadata, false, new ItemStack(FrogGameObjects.PURIFIED_DEWALQUITE_ORE), new ItemStack(FrogGameObjects.TINY_DEWALQUITE_DUST, 2), IC2Items.getItem("dust", "stone"));
		Recipes.oreWashing.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CRUSHED_FLUORAPATITE_ORE)), oreWashingMetadata, false, new ItemStack(FrogGameObjects.PURIFIED_FLUORAPATITE_ORE), new ItemStack(FrogGameObjects.TINY_FLUORAPATITE_DUST, 2), IC2Items.getItem("dust", "stone"));
	
		NBTTagCompound centrifugeMetadata = new NBTTagCompound();
		centrifugeMetadata.setInteger("minHeat", 500);
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CARNALLITE_DUST, 5)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.POTASSIUM_CHLORIDE_DUST, 4), new ItemStack(FrogGameObjects.MAGNESIUM_BROMIDE_DUST, 1));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.DEWALQUITE_DUST, 27)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.ALUMINIUM_OXIDE_DUST, 25), new ItemStack(FrogGameObjects.TITANIUM_OXIDE_DUST, 1), new ItemStack(FrogGameObjects.VANADIUM_OXIDE_DUST, 1));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.PURIFIED_CARNALLITE_ORE)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.CARNALLITE_DUST), new ItemStack(FrogGameObjects.TINY_CARNALLITE_DUST));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.PURIFIED_DEWALQUITE_ORE)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.DEWALQUITE_DUST), new ItemStack(FrogGameObjects.TINY_DEWALQUITE_DUST));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.PURIFIED_FLUORAPATITE_ORE)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.FLUORAPATITE_DUST), new ItemStack(FrogGameObjects.TINY_FLUORAPATITE_DUST));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CRUSHED_CARNALLITE_ORE)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.CARNALLITE_DUST), new ItemStack(FrogGameObjects.TINY_CARNALLITE_DUST, 2), IC2Items.getItem("dust", "small_lithium"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CRUSHED_DEWALQUITE_ORE)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.DEWALQUITE_DUST), new ItemStack(FrogGameObjects.TINY_DEWALQUITE_DUST, 2), IC2Items.getItem("dust", "stone"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.CRUSHED_FLUORAPATITE_ORE)), centrifugeMetadata, false, new ItemStack(FrogGameObjects.FLUORAPATITE_DUST), new ItemStack(FrogGameObjects.TINY_FLUORAPATITE_DUST, 2), IC2Items.getItem("dust", "stone"));
	
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.SHATTERED_COAL_COKE, 8)), null, false, new ItemStack(FrogGameObjects.BRIQUETTE));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.ALUMINIUM_PLATE, 9)), null, false, new ItemStack(FrogGameObjects.DENSE_ALUMINIUM_PLATE));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNALIUM_PLATE, 9)), null, false, new ItemStack(FrogGameObjects.DENSE_MAGNALIUM_PLATE));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TITANIUM_PLATE, 9)), null, false, new ItemStack(FrogGameObjects.DENSE_TITANIUM_PLATE));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNESIUM_PLATE, 9)), null, false, new ItemStack(FrogGameObjects.DENSE_MAGNESIUM_PLATE));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TINY_ALUMINIUM_DUST, 9)), null, false, new ItemStack(FrogGameObjects.ALUMINIUM_DUST));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TINY_MAGNALIUM_DUST, 9)), null, false, new ItemStack(FrogGameObjects.MAGNALIUM_DUST));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TINY_TITANIUM_DUST, 9)), null, false, new ItemStack(FrogGameObjects.TITANIUM_DUST));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TINY_MAGNESIUM_DUST, 9)), null, false, new ItemStack(FrogGameObjects.MAGNESIUM_DUST));

		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.ALUMINIUM_INGOT)), null, false, new ItemStack(FrogGameObjects.ALUMINIUM_PLATE));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNALIUM_INGOT)), null, false, new ItemStack(FrogGameObjects.MAGNALIUM_PLATE));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TITANIUM_INGOT)), null, false, new ItemStack(FrogGameObjects.TITANIUM_PLATE));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNESIUM_INGOT)), null, false, new ItemStack(FrogGameObjects.MAGNESIUM_PLATE));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.ALUMINIUM_PLATE)), null, false, new ItemStack(FrogGameObjects.ALUMINIUM_CASING, 2));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNALIUM_PLATE)), null, false, new ItemStack(FrogGameObjects.MAGNALIUM_CASING, 2));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.TITANIUM_PLATE)), null, false, new ItemStack(FrogGameObjects.TITANIUM_CASING, 2));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogGameObjects.MAGNESIUM_PLATE)), null, false, new ItemStack(FrogGameObjects.MAGNESIUM_CASING, 2));
	}
	
	public static void initOreDict() {
		OreDictionary.registerOre("oreCarnallite", new ItemStack(FrogGameObjects.CARNALLITE));
		OreDictionary.registerOre("oreDewalquite", new ItemStack(FrogGameObjects.DEWALQUITE));
		OreDictionary.registerOre("oreFluorapatite", new ItemStack(FrogGameObjects.FLUORAPATITE));
		
		OreDictionary.registerOre("dustCarnallite", new ItemStack(FrogGameObjects.CARNALLITE_DUST));
		OreDictionary.registerOre("dustDewalquite", new ItemStack(FrogGameObjects.DEWALQUITE_DUST));
		OreDictionary.registerOre("dustFluorapatite", new ItemStack(FrogGameObjects.FLUORAPATITE_DUST));
		
		OreDictionary.registerOre("dustTinyCarnallite", new ItemStack(FrogGameObjects.TINY_CARNALLITE_DUST));
		OreDictionary.registerOre("dustTinyDewalquite", new ItemStack(FrogGameObjects.TINY_DEWALQUITE_DUST));
		OreDictionary.registerOre("dustTinyFluorapatite", new ItemStack(FrogGameObjects.TINY_FLUORAPATITE_DUST));

		OreDictionary.registerOre("dustAluminium", new ItemStack(FrogGameObjects.ALUMINIUM_DUST));
		OreDictionary.registerOre("dustMagnalium", new ItemStack(FrogGameObjects.MAGNALIUM_DUST));
		OreDictionary.registerOre("dustTitanium",  new ItemStack(FrogGameObjects.TITANIUM_DUST));
		OreDictionary.registerOre("dustMagnesium", new ItemStack(FrogGameObjects.MAGNESIUM_DUST));

		OreDictionary.registerOre("ingotAluminium", new ItemStack(FrogGameObjects.ALUMINIUM_INGOT));
		OreDictionary.registerOre("ingotMagnalium", new ItemStack(FrogGameObjects.MAGNALIUM_INGOT));
		OreDictionary.registerOre("ingotTitanium", new ItemStack(FrogGameObjects.TITANIUM_INGOT));
		OreDictionary.registerOre("ingotMagnesium", new ItemStack(FrogGameObjects.MAGNESIUM_INGOT));

		OreDictionary.registerOre("plateAluminium", new ItemStack(FrogGameObjects.ALUMINIUM_PLATE));
		OreDictionary.registerOre("plateMagnalium", new ItemStack(FrogGameObjects.MAGNALIUM_PLATE));
		OreDictionary.registerOre("plateTitanium", new ItemStack(FrogGameObjects.TITANIUM_PLATE));
		OreDictionary.registerOre("plateMagnesium", new ItemStack(FrogGameObjects.MAGNESIUM_PLATE));

		OreDictionary.registerOre("plateDenseAluminium", new ItemStack(FrogGameObjects.DENSE_ALUMINIUM_PLATE));
		OreDictionary.registerOre("plateDenseMagnalium", new ItemStack(FrogGameObjects.DENSE_MAGNALIUM_PLATE));
		OreDictionary.registerOre("plateDenseTitanium", new ItemStack(FrogGameObjects.DENSE_TITANIUM_PLATE));
		OreDictionary.registerOre("plateDenseMagnesium", new ItemStack(FrogGameObjects.DENSE_MAGNESIUM_PLATE));


		if (FrogConfig.modpackOptions.altAluminiumOreDict) {
			if (FrogConfig.modpackOptions.altAluminiumDustOreDict) {
				OreDictionary.registerOre("dustAluminum", new ItemStack(FrogGameObjects.ALUMINIUM_DUST));
			}
			if (FrogConfig.modpackOptions.altAluminiumIngotOreDict) {
				OreDictionary.registerOre("ingotAluminum", new ItemStack(FrogGameObjects.ALUMINIUM_INGOT));
			}
			if (FrogConfig.modpackOptions.altAluminiumPlateOreDict) {
				OreDictionary.registerOre("plateAluminum", new ItemStack(FrogGameObjects.ALUMINIUM_PLATE));
			}
			if (FrogConfig.modpackOptions.altAluminiumDensePlateOreDict) {
				OreDictionary.registerOre("plateAluminum", new ItemStack(FrogGameObjects.ALUMINIUM_PLATE));
			}
		}
	}
}
