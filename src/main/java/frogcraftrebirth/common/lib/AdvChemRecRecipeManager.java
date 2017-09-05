package frogcraftrebirth.common.lib;

import java.util.ArrayList;
import java.util.Collection;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IRecipeManager;
import net.minecraft.item.ItemStack;

public class AdvChemRecRecipeManager implements IRecipeManager<IAdvChemRecRecipe> {

	@Override
	public void add(IAdvChemRecRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(IAdvChemRecRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<IAdvChemRecRecipe> getRecipes() {
		return recipes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IAdvChemRecRecipe getRecipe(IFrogRecipeInput... inputs) {
		for (IAdvChemRecRecipe recipe : recipes) {
			if (recipe.matchInputs()) {
				return recipe;
			}
		}
		
		return null;
	}

	private static ArrayList<IAdvChemRecRecipe> recipes = new ArrayList<>();

}
