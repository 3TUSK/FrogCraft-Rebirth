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
import frogcraftrebirth.api.ICompatModuleFrog;
import frogcraftrebirth.client.gui.GuiAdvChemReactor;
import frogcraftrebirth.client.gui.GuiCondenseTower;
import frogcraftrebirth.client.gui.GuiMPS;
import frogcraftrebirth.client.gui.GuiPyrolyzer;
import frogcraftrebirth.common.registry.RegFrogItemsBlocks;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CompatJEI implements IModPlugin, ICompatModuleFrog {

	@Override
	public void init() {
		FrogAPI.FROG_LOG.info("Detected JustEnoughItems, compability will be enabled automatically.");
	}
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
		
	}
	
	@Override
	public void registerIngredients(IModIngredientRegistration registry) {
		
	}

	@Override
	public void register(IModRegistry registry) {
		registry.addRecipeHandlers(new HandlerChemReaction());
		registry.addRecipeHandlers(new HandlerCondensation());
		registry.addRecipeHandlers(new HandlerPyrolyzation());
		
		registry.addRecipeCategories(new CategoryChemReaction(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new CategoryCondensation(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new CategoryPyrolyzation(registry.getJeiHelpers().getGuiHelper()));
		
		registry.addRecipeCategoryCraftingItem(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 0), "frogcraftrebirth.chemreaction");
		registry.addRecipeCategoryCraftingItem(new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1, 0), "frogcraftrebirth.condensation");
		registry.addRecipeCategoryCraftingItem(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 2), "frogcraftrebirth.pyrolyzation");
		
		registry.addRecipes(RecipeChemReaction.getWrappedRecipeList());
		registry.addRecipes(RecipeCondensation.getWrappedRecipeList());
		registry.addRecipes(RecipePyrolyzation.getWrappedRecipeList());
		
		registry.addRecipeClickArea(GuiMPS.class, 39, 47, 24, 16, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeClickArea(GuiAdvChemReactor.class, 73, 40, 30, 10, "frogcraftrebirth.chemreaction");
		registry.addRecipeClickArea(GuiCondenseTower.class, 115, 40, 12, 14, "frogcraftrebirth.condensation");
		registry.addRecipeClickArea(GuiPyrolyzer.class, 45, 29, 24, 17, "frogcraftrebirth.pyrolyzation");
		
		registry.addDescription(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 0), "jei.doc.advChemReactor");
		registry.addDescription(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 1), "jei.doc.airPump");
		registry.addDescription(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 2), "jei.doc.pyrolyzer");
		registry.addDescription(new ItemStack(RegFrogItemsBlocks.MACHINE, 1, 3), "jei.doc.liquefier");
		
		registry.addDescription(Arrays.asList(new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1, 0), new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1, 1), new ItemStack(RegFrogItemsBlocks.CONDENSE_TOWER, 1, 2)), "jei.doc.condenseTower");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		
	}

}
