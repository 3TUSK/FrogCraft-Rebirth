/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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
import frogcraftrebirth.api.FrogRegistees;
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
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Collections;

class FrogRecipes {
	
	public static void init() {
		if (FrogConfig.modpackOptions.enableRecipes) {
			FluidStackFactory fluidFactory = new FluidStackFactory();
			// --- Begin of old FrogCraft recipes, Part 1 ---
			// CaO + H2O -> Ca(OH)2
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.NON_METAL_DUST, 1,4)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(new ItemStack(FrogRegistees.NON_METAL_DUST, 1,6)), ItemStack.EMPTY, 20, 100, 0, 1));
			// Ca(OH)2 + CO2 -> CaCO3 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.NON_METAL_DUST, 1,6)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FrogFluids.carbonDioxide, 1000))), Arrays.asList(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 1), IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY, 20, 10, 0, 0));
			// N2 + 3H2 -> 2NH3
			ItemStack hydrogenCells = IC2Items.getItem("fluid_cell", "ic2hydrogen");
			hydrogenCells.setCount(3);
			ItemStack ammoniaCell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(ammoniaCell).fill(fluidFactory.create("ammonia", 1000), true);
			ammoniaCell.setCount(2);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("nitrogen", 1000)), new FrogRecipeInputItemStack(hydrogenCells)), Collections.singleton(ammoniaCell), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 2), 150, 128, 0, 2));
			// CO2 + 2NH3 -> CO(NH2)2 + H2O
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("carbon_dioxide", 1000)), new FrogRecipeInputItemStack(ammoniaCell.copy())), Arrays.asList(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 7), IC2Items.getItem("fluid_cell", "water")), ItemStack.EMPTY,50, 40, 0, 2));
			// HNO3 + NH3 -> NH4NO3
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("nitric_acid", 1000)), new FrogRecipeInputUniversalFluidCell(fluidFactory.create("ammonia", 1000))), Collections.singleton(new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 0)), ItemStack.EMPTY, 10, 10, 0, 2));
			//SO3+H2O->H2SO4
			ItemStack sulfuricCell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(sulfuricCell).fill(fluidFactory.create("sulfuric_acid", 1000), true);
			sulfuricCell.setCount(1);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfur_trioxide", 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(sulfuricCell), ItemStack.EMPTY, 1200, 128, 0, 1));
			//2SO2+O2->2SO3
			ItemStack so3Cell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(so3Cell).fill(fluidFactory.create("sulfur_trioxide", 1000), true);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfur_dioxide", 1000)), new FrogRecipeInputItemStack(IC2Items.getItem("fluid_cell", "ic2oxygen"))), Collections.singleton(so3Cell), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 3), 300, 100, 0, 1));

			FrogAPI.managerCT.add(new CondenseTowerRecipe(100, 75, fluidFactory.create("coal_tar", 25), new FluidStack[] { fluidFactory.create("benzene", 2), fluidFactory.create("ammonia", 3), fluidFactory.create("carbon_oxide", 5), fluidFactory.create("methane", 10), fluidFactory.create("ic2hydrogen", 5) }));
			FrogAPI.managerCT.add(new CondenseTowerRecipe(10, 75, fluidFactory.create("ic2air", 12), new FluidStack[] { fluidFactory.create("argon", 1), fluidFactory.create("nitrogen", 7), fluidFactory.create("ic2oxygen", 2), fluidFactory.create("carbon_dioxide", 2) }));

			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(IC2Items.getItem("dust", "coal"), new ItemStack(FrogRegistees.INFLAMMABLE, 1, 4), fluidFactory.create("coal_tar", 50), 80, 48));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 4), fluidFactory.create("carbon_dioxide", 50), 100, 64));
			FrogAPI.managerPyrolyzer.add(new PyrolyzerRecipe(new ItemStack(Blocks.STONE), new ItemStack(FrogRegistees.NON_METAL_DUST, 1, 4), fluidFactory.create("carbon_dioxide", 50), 100, 64));

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
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfuric_acid", 1000)), new FrogRecipeInputUniversalFluidCell(fluidFactory.create("sulfur_trioxide", 1000))), Collections.singleton(oleumCell), ItemStack.EMPTY, 200, 10, 0, 1));
			// H2S2O7 + H2O -> 2H2SO4, mimicking contact process https://en.wikipedia.org/wiki/Contact_process
			ItemStack sulfuricCell_2 = sulfuricCell.copy();
			sulfuricCell_2.setCount(2);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputUniversalFluidCell(fluidFactory.create("oleum", 1000)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 1000))), Collections.singleton(sulfuricCell_2), ItemStack.EMPTY, 100, 10, 0,0));
			// 2KCl + 2H2O -> 2KOH + H2(g) + Cl2(g), manufacturing potassium hydroxide
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.INTERMEDIATE, 2, 3)), new FrogRecipeInputUniversalFluidCell(new FluidStack(FluidRegistry.WATER, 2000))), Collections.emptyList(), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 1), 600, 512, 0, 0));
			// Saponification

			// 2Al2O3 + 3C -> 4Al+ 3CO2, electrolysis
			ItemStack co2Cell = FrogRecipeInputs.UNI_CELL.copy();
			FluidUtil.getFluidHandler(co2Cell).fill(fluidFactory.create("carbon_dioxide", 1000), true);
			co2Cell.setCount(3);
			FrogAPI.managerACR.add(new AdvChemRecRecipe(Arrays.asList(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.INTERMEDIATE, 2, 0)), new FrogRecipeInputOreDict("dustCoal", 3)), Arrays.asList(new ItemStack(FrogRegistees.METAL_DUST, 4, 0), co2Cell), new ItemStack(FrogRegistees.REACTION_MODULE, 1, 1), 1200, 512, 3, 0));

			// TiO2 + 2H2 -> Ti + 2H2O
			FrogAPI.managerABF.add(new AdvBlastFurnaceRecipe(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.INTERMEDIATE, 1, 5)), FrogRecipeInputs.EMPTY, FluidRegistry.getFluidStack("ic2hydrogen", 2000), new ItemStack(FrogRegistees.METAL_INGOT, 1, 2), ItemStack.EMPTY, FrogFluids.argon, 300, 0));

			// Temperary Aluminium smelting
			FrogAPI.managerABF.add(new AdvBlastFurnaceRecipe(new FrogRecipeInputItemStack(new ItemStack(FrogRegistees.METAL_DUST, 1, 0)), FrogRecipeInputs.EMPTY, null, new ItemStack(FrogRegistees.METAL_INGOT, 1, 0), ItemStack.EMPTY, FrogFluids.argon, 200, 0));

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

		Recipes.advRecipes.addRecipe(new ItemStack(FrogRegistees.AMMONIA_COOLANT_60K), " T ", "TCT", " T ", 'T', "plateTin", 'C', FluidRegistry.getFluidStack("ammonia", 1000));
		Recipes.advRecipes.addRecipe(new ItemStack(FrogRegistees.JINKELA), "KKK", "PPP", "NNN", 'K', "ingotPotassium", 'P', "ingotPhosphorus", 'N', FluidRegistry.getFluidStack("nitrogen", 1000));

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
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_DUST, 5, 0)), centrifugeMetadata, true, new ItemStack(FrogRegistees.INTERMEDIATE, 4, 3), new ItemStack(FrogRegistees.INTERMEDIATE, 1, 2));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_DUST, 27, 1)), centrifugeMetadata, true, new ItemStack(FrogRegistees.INTERMEDIATE, 25, 0), new ItemStack(FrogRegistees.INTERMEDIATE, 1, 5), new ItemStack(FrogRegistees.INTERMEDIATE, 1, 6));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 0)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 0), new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 0));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 1)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 1), new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 1));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_PURIFIED, 1, 2)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 2), new ItemStack(FrogRegistees.ORE_DUST_TINY, 1, 2));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 0)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 0), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 0), IC2Items.getItem("dust", "small_lithium"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 1)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 1), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 1), IC2Items.getItem("dust", "stone"));
		Recipes.centrifuge.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.ORE_CRUSHED, 1, 2)), centrifugeMetadata, true, new ItemStack(FrogRegistees.ORE_DUST, 1, 2), new ItemStack(FrogRegistees.ORE_DUST_TINY, 2, 2), IC2Items.getItem("dust", "stone"));
	
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.INFLAMMABLE, 8, 4)), null, true, new ItemStack(FrogRegistees.INFLAMMABLE, 1, 0));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_PLATE, 9, 0)), null, true, new ItemStack(FrogRegistees.METAL_PLATE_DENSE, 1, 0));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_PLATE, 9, 1)), null, true, new ItemStack(FrogRegistees.METAL_PLATE_DENSE, 1, 1));
		Recipes.compressor.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_PLATE, 9, 2)), null, true, new ItemStack(FrogRegistees.METAL_PLATE_DENSE, 1, 2));

		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_INGOT, 1, 0)), null, true, new ItemStack(FrogRegistees.METAL_PLATE, 1, 0));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_INGOT, 1, 1)), null, true, new ItemStack(FrogRegistees.METAL_PLATE, 1, 1));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_INGOT, 1, 2)), null, true, new ItemStack(FrogRegistees.METAL_PLATE, 1, 2));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_PLATE, 1, 0)), null, true, new ItemStack(FrogRegistees.METAL_CASING, 2, 0));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_PLATE, 1, 1)), null, true, new ItemStack(FrogRegistees.METAL_CASING, 2, 1));
		Recipes.metalformerRolling.addRecipe(Recipes.inputFactory.forStack(new ItemStack(FrogRegistees.METAL_PLATE, 1, 2)), null, true, new ItemStack(FrogRegistees.METAL_CASING, 2, 2));
	}
	
	static void initOreDict() {
		OreDictionary.registerOre("oreCarnallite", GameRegistry.makeItemStack("frogcraftrebirth:ore", 0, 1, null));
		OreDictionary.registerOre("oreDewalquite", GameRegistry.makeItemStack("frogcraftrebirth:ore", 1, 1, null));
		OreDictionary.registerOre("oreFluorapatite", GameRegistry.makeItemStack("frogcraftrebirth:ore", 2, 1, null));
		
		OreDictionary.registerOre("dustCarnallite", GameRegistry.makeItemStack("frogcraftrebirth:ore_dust", 0, 1, null));
		OreDictionary.registerOre("dustDewalquite", GameRegistry.makeItemStack("frogcraftrebirth:ore_dust", 1, 1, null));
		OreDictionary.registerOre("dustFluorapatite", GameRegistry.makeItemStack("frogcraftrebirth:ore_dust", 2, 1, null));
		
		OreDictionary.registerOre("dustTinyCarnallite", GameRegistry.makeItemStack("frogcraftrebirth:ore_dust_tiny", 0, 1, null));
		OreDictionary.registerOre("dustTinyDewalquite", GameRegistry.makeItemStack("frogcraftrebirth:ore_dust_tiny", 1, 1, null));
		OreDictionary.registerOre("dustTinyFluorapatite", GameRegistry.makeItemStack("frogcraftrebirth:ore_dust_tiny", 2, 1, null));
		
		OreDictionary.registerOre("dustVanadiumPentoxide", GameRegistry.makeItemStack("frogcraftrebirth:intermediate_product", 6, 1, null));

		OreDictionary.registerOre("dustAluminium", GameRegistry.makeItemStack("frogcraftrebirth:metal_dust", 0, 1, null));
		OreDictionary.registerOre("dustMagnalium", GameRegistry.makeItemStack("frogcraftrebirth:metal_dust", 1, 1, null));
		OreDictionary.registerOre("dustTitanium", GameRegistry.makeItemStack("frogcraftrebirth:metal_dust", 2, 1, null));

		OreDictionary.registerOre("ingotAluminium", GameRegistry.makeItemStack("frogcraftrebirth:metal_ingot", 0, 1, null));
		OreDictionary.registerOre("ingotMagnalium", GameRegistry.makeItemStack("frogcraftrebirth:metal_ingot", 1, 1, null));
		OreDictionary.registerOre("ingotTitanium", GameRegistry.makeItemStack("frogcraftrebirth:metal_ingot", 2, 1, null));

		OreDictionary.registerOre("plateAluminium", GameRegistry.makeItemStack("frogcraftrebirth:metal_plate", 0, 1, null));
		OreDictionary.registerOre("plateMagnalium", GameRegistry.makeItemStack("frogcraftrebirth:metal_plate", 1, 1, null));
		OreDictionary.registerOre("plateTitanium", GameRegistry.makeItemStack("frogcraftrebirth:metal_plate", 2, 1, null));

		if (FrogConfig.modpackOptions.altAluminiumOreDict) {
			if (FrogConfig.modpackOptions.altAluminiumDustOreDict) {
				OreDictionary.registerOre("dustAluminum", GameRegistry.makeItemStack("frogcraftrebirth:metal_dust", 0, 1, null));
			}
			if (FrogConfig.modpackOptions.altAluminiumIngotOreDict) {
				OreDictionary.registerOre("ingotAluminum", GameRegistry.makeItemStack("frogcraftrebirth:metal_ingot", 0, 1, null));
			}
			if (FrogConfig.modpackOptions.altAluminiumPlateOreDict) {
				OreDictionary.registerOre("plateAluminum", GameRegistry.makeItemStack("frogcraftrebirth:metal_plate", 0, 1, null));
			}
		}
	}
}
