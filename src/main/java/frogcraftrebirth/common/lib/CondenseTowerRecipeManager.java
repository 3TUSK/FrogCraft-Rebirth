package frogcraftrebirth.common.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;

public class CondenseTowerRecipeManager implements IRecipeManager<ICondenseTowerRecipe> {

	@Override
	public boolean equal(ICondenseTowerRecipe recipe1, ICondenseTowerRecipe recipe2) {
		return (recipe1.getInput().equals(recipe2.getInput()));
	}

	@Override
	public void add(ICondenseTowerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(ICondenseTowerRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<ICondenseTowerRecipe> getRecipes() {
		return recipes;
	}
	
	//This method will only read the first parameter.
	@SuppressWarnings("unchecked")
	@Override
	public <FluidStack> ICondenseTowerRecipe getRecipe(FluidStack... input) {
		if (input[0] == null) 
			return null;
		for (ICondenseTowerRecipe recipe : recipes) {
			if (recipe.getInput().isFluidStackIdentical((net.minecraftforge.fluids.FluidStack) input[0]))
				return recipe;
		}
		return null;
	}
	
	private final List<ICondenseTowerRecipe> recipes = new ArrayList<ICondenseTowerRecipe>();;

}
