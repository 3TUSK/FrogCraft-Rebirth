package frogcraftrebirth.common.lib;

import java.util.ArrayList;
import java.util.Collection;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;

public class AdvChemRecRecipeManager implements IRecipeManager<IAdvChemRecRecipe> {

	@Override
	public boolean equal(IAdvChemRecRecipe recipe1, IAdvChemRecRecipe recipe2) {
		if (recipe1.getInputs().size() != recipe2.getInputs().size()) 
			return false;
		if (recipe1.getInputs().containsAll(recipe2.getInputs()))
			return true;
			else return false;
	}

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
	public <ItemStack> IAdvChemRecRecipe getRecipe(ItemStack... inputs) {
		for (IAdvChemRecRecipe recipe : recipes) {
			if (recipe.matchInputs((net.minecraft.item.ItemStack[])inputs)) {
				return recipe;
			}
		}
		
		return null;
	}

	private static ArrayList<IAdvChemRecRecipe> recipes = new ArrayList<IAdvChemRecRecipe>();

}
