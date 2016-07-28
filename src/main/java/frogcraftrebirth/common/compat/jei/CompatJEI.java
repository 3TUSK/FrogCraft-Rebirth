/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 5:56:52 PM, Jul 23, 2016, 
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.compat.jei;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.ICompatModuleFrog;
import frogcraftrebirth.client.gui.GuiCondenseTower;
import frogcraftrebirth.client.gui.GuiMPS;
import frogcraftrebirth.common.FrogBlocks;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CompatJEI implements IModPlugin, ICompatModuleFrog {

	@Override
	public void init() {
		FrogAPI.FROG_LOG.info("Detected JustEnoughItems, compability will be enabled automatically.");
	}

	@Override
	public void register(IModRegistry registry) {
		//registry.addRecipeHandlers(new HandlerAdvChemReactor());
		registry.addRecipeHandlers(new HandlerCondensation());
		//registry.addRecipeHandlers(new HandlerPyrolyzer());
		
		//registry.addRecipeCategories(new CategoryChemReaction());
		registry.addRecipeCategories(new CategoryCondensation(registry.getJeiHelpers().getGuiHelper()));
		//registry.addRecipeCategories(new CategoryPyrolyzation());
		
		registry.addRecipeCategoryCraftingItem(new ItemStack(FrogBlocks.condenseTowerPart, 1, 0), "frogcraftrebirth.condensation");
		
		registry.addRecipes(RecipeCondensation.getWrappedRecipeList());
		
		registry.addRecipeClickArea(GuiMPS.class, 39, 47, 24, 16, VanillaRecipeCategoryUid.SMELTING, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeClickArea(GuiCondenseTower.class, 109, 30, 13, 12, "frogcraftrebirth.condensation");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		
	}

}
