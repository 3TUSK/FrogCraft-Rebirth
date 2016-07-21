package frogcraftrebirth.common.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import frogcraftrebirth.api.recipes.IRecipeManager;

public class CondenseTowerRecipeManager implements IRecipeManager<CondenseTowerRecipe> {
	
	public CondenseTowerRecipeManager() {
		recipes = new ArrayList<CondenseTowerRecipe>();
	}

	@Override
	public boolean equal(CondenseTowerRecipe recipe1, CondenseTowerRecipe recipe2) {
		return (recipe1.getInput().equals(recipe2.getInput()));
	}

	@Override
	public void add(CondenseTowerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(CondenseTowerRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<CondenseTowerRecipe> getRecipes() {
		return recipes;
	}
	
	//This method will only read the first parameter.
	@SuppressWarnings("unchecked")
	@Override
	public <FluidStack> CondenseTowerRecipe getRecipe(FluidStack... input) {
		if (input[0] == null) 
			return null;
		for (CondenseTowerRecipe recipe : recipes) {
			if (recipe.getInput().isFluidStackIdentical((net.minecraftforge.fluids.FluidStack) input[0]))
				return recipe;
		}
		return null;
	}
	
	private static List<CondenseTowerRecipe> recipes;

}
