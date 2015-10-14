package frogcraftrewrite.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

public class CondenseTowerRecipeManager implements IRecipeManager<CondenseTowerRecipe>{

	@Override
	public boolean equal(CondenseTowerRecipe recipe1, CondenseTowerRecipe recipe2) {
		return (recipe1.getInput() == recipe2.getInput());
	}

	@Override
	public boolean add(CondenseTowerRecipe recipe) {
		return recipes.add(recipe);
	}

	@Override
	public boolean remove(CondenseTowerRecipe recipe) {
		return recipes.remove(recipe);
	}
	
	@Override
	public Collection<CondenseTowerRecipe> getRecipes() {
		return recipes;
	}
	
	@Override
	public CondenseTowerRecipe getRecipe(Object... input) {
		return null;
	}
	
	private static ArrayList<CondenseTowerRecipe> recipes = new ArrayList<CondenseTowerRecipe>();

}
