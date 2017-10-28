/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 5:56:52 PM, Jul 23, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import java.util.Arrays;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.client.gui.*;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CompatJEI implements IModPlugin {

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
		registry.handleRecipes(IAdvChemRecRecipe.class, new RecipeWrapperFactoryChemReaction(), "frogcraftrebirth.chemreaction");
		registry.handleRecipes(ICondenseTowerRecipe.class, new RecipeWrapperFactoryCondensation(), "frogcraftrebirth.condensation");
		registry.handleRecipes(IPyrolyzerRecipe.class, new RecipeWrapperFactoryPyrolyzation(), "frogcraftrebirth.pyrolyzation");
		registry.handleRecipes(IAdvBlastFurnaceRecipe.class, new RecipeWrapperFactoryBlastFurnace(), "frogcraftrebirth.blastfurnace");

		registry.addRecipeCatalyst(new ItemStack(FrogRegistees.MACHINE, 1, 0), "frogcraftrebirth.chemreaction");
		registry.addRecipeCatalyst(new ItemStack(FrogRegistees.CONDENSE_TOWER, 1, 0), "frogcraftrebirth.condensation");
		registry.addRecipeCatalyst(new ItemStack(FrogRegistees.MACHINE, 1, 2), "frogcraftrebirth.pyrolyzation");
		registry.addRecipeCatalyst(new ItemStack(FrogRegistees.MACHINE2, 1, 0), "frogcraftrebirth.blastfurnace");

		registry.addRecipes(FrogAPI.managerACR.getRecipes(), "frogcraftrebirth.chemreaction");
		registry.addRecipes(FrogAPI.managerCT.getRecipes(), "frogcraftrebirth.condensation");
		registry.addRecipes(FrogAPI.managerPyrolyzer.getRecipes(), "frogcraftrebirth.pyrolyzation");
		registry.addRecipes(FrogAPI.managerABF.getRecipes(), "frogcraftrebirth.blastfurnace");

		registry.addRecipeClickArea(GuiMPS.class, 39, 47, 24, 16, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeClickArea(GuiAdvChemReactor.class, 73, 40, 30, 10, "frogcraftrebirth.chemreaction");
		registry.addRecipeClickArea(GuiCondenseTower.class, 115, 40, 12, 14, "frogcraftrebirth.condensation");
		registry.addRecipeClickArea(GuiPyrolyzer.class, 45, 29, 24, 17, "frogcraftrebirth.pyrolyzation");
		registry.addRecipeClickArea(GuiAdvBlastFurnace.class, 76, 27, 24, 17, "frogcraftrebirth.blastfurnace");

		registry.addIngredientInfo(new ItemStack(FrogRegistees.MACHINE, 1, 0), ItemStack.class, "jei.doc.advChemReactor");
		registry.addIngredientInfo(new ItemStack(FrogRegistees.MACHINE, 1, 1), ItemStack.class, "jei.doc.airPump");
		registry.addIngredientInfo(new ItemStack(FrogRegistees.MACHINE, 1, 2), ItemStack.class, "jei.doc.pyrolyzer");
		registry.addIngredientInfo(new ItemStack(FrogRegistees.MACHINE, 1, 3), ItemStack.class, "jei.doc.liquefier");
		
		registry.addIngredientInfo(Arrays.asList(new ItemStack(FrogRegistees.CONDENSE_TOWER, 1, 0), new ItemStack(FrogRegistees.CONDENSE_TOWER, 1, 1), new ItemStack(FrogRegistees.CONDENSE_TOWER, 1, 2)), ItemStack.class, "jei.doc.condenseTower");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {}

}
