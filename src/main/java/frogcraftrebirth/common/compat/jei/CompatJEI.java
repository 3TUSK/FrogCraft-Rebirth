/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
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

package frogcraftrebirth.common.compat.jei;

import java.util.Arrays;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogGameObjects;
import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.client.gui.GuiAdvBlastFurnace;
import frogcraftrebirth.client.gui.GuiAdvChemReactor;
import frogcraftrebirth.client.gui.GuiCondenseTower;
import frogcraftrebirth.client.gui.GuiMPS;
import frogcraftrebirth.client.gui.GuiPyrolyzer;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public final class CompatJEI implements IModPlugin {

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {}
	
	@Override
	public void registerIngredients(IModIngredientRegistration registry) {}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(
				new CategoryChemReaction(registry.getJeiHelpers().getGuiHelper()),
				new CategoryCondensation(registry.getJeiHelpers().getGuiHelper()),
				new CategoryPyrolyzation(registry.getJeiHelpers().getGuiHelper()),
				new CategoryBlastFurnace(registry.getJeiHelpers().getGuiHelper())
		);
	}

	@Override
	public void register(IModRegistry registry) {
		registry.handleRecipes(IAdvChemRecRecipe.class, RecipeChemReaction::new, "frogcraftrebirth.chemreaction");
		registry.handleRecipes(ICondenseTowerRecipe.class, RecipeCondensation::new, "frogcraftrebirth.condensation");
		registry.handleRecipes(IPyrolyzerRecipe.class, RecipePyrolyzation::new, "frogcraftrebirth.pyrolyzation");
		registry.handleRecipes(IAdvBlastFurnaceRecipe.class, RecipeBlastFurnace::new, "frogcraftrebirth.blastfurnace");

		registry.addRecipeCatalyst(new ItemStack(FrogGameObjects.ADV_CHEM_REACTOR), "frogcraftrebirth.chemreaction");
		registry.addRecipeCatalyst(new ItemStack(FrogGameObjects.CONDENSE_TOWER_CORE), "frogcraftrebirth.condensation");
		registry.addRecipeCatalyst(new ItemStack(FrogGameObjects.PYROLYZER), "frogcraftrebirth.pyrolyzation");
		registry.addRecipeCatalyst(new ItemStack(FrogGameObjects.ADV_BLAST_FURNACE), "frogcraftrebirth.blastfurnace");

		registry.addRecipes(FrogAPI.managerACR.getRecipes(), "frogcraftrebirth.chemreaction");
		registry.addRecipes(FrogAPI.managerCT.getRecipes(), "frogcraftrebirth.condensation");
		registry.addRecipes(FrogAPI.managerPyrolyzer.getRecipes(), "frogcraftrebirth.pyrolyzation");
		registry.addRecipes(FrogAPI.managerABF.getRecipes(), "frogcraftrebirth.blastfurnace");

		registry.addRecipeClickArea(GuiMPS.class, 39, 47, 24, 16, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeClickArea(GuiAdvChemReactor.class, 73, 40, 30, 10, "frogcraftrebirth.chemreaction");
		registry.addRecipeClickArea(GuiCondenseTower.class, 115, 40, 12, 14, "frogcraftrebirth.condensation");
		registry.addRecipeClickArea(GuiPyrolyzer.class, 45, 29, 24, 17, "frogcraftrebirth.pyrolyzation");
		registry.addRecipeClickArea(GuiAdvBlastFurnace.class, 76, 27, 24, 17, "frogcraftrebirth.blastfurnace");

		registry.addIngredientInfo(new ItemStack(FrogGameObjects.ADV_CHEM_REACTOR), VanillaTypes.ITEM, "jei.doc.advChemReactor");
		registry.addIngredientInfo(new ItemStack(FrogGameObjects.AIR_PUMP), VanillaTypes.ITEM, "jei.doc.airPump");
		registry.addIngredientInfo(new ItemStack(FrogGameObjects.PYROLYZER), VanillaTypes.ITEM, "jei.doc.pyrolyzer");
		registry.addIngredientInfo(new ItemStack(FrogGameObjects.LIQUEFIER), VanillaTypes.ITEM, "jei.doc.liquefier");
		
		registry.addIngredientInfo(Arrays.asList(new ItemStack(FrogGameObjects.CONDENSE_TOWER_CORE), new ItemStack(FrogGameObjects.CONDENSE_TOWER_CYLINDER), new ItemStack(FrogGameObjects.CONDENSE_TOWER_OUTLET)), VanillaTypes.ITEM, "jei.doc.condenseTower");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {}

}
