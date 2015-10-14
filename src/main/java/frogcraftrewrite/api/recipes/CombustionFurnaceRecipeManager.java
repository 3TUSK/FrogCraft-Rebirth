package frogcraftrewrite.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

public class CombustionFurnaceRecipeManager implements IRecipeManager<CombustionFurnaceRecipe>{

	@Override
	public boolean equal(CombustionFurnaceRecipe recipe1, CombustionFurnaceRecipe recipe2) {
		return (recipe1.getInput() == recipe2.getInput());
	}

	@Override
	public boolean add(CombustionFurnaceRecipe recipe) {
		return recipes.add(recipe);
	}

	@Override
	public boolean remove(CombustionFurnaceRecipe recipe) {
		return recipes.remove(recipe);
	}
	
	@Override
	public Collection<CombustionFurnaceRecipe> getRecipes() {
		return recipes;
	}
	
	@Override
	public CombustionFurnaceRecipe getRecipe(Object... input) {
		return null;
	}
	
	private static ArrayList<CombustionFurnaceRecipe> recipes = new ArrayList<CombustionFurnaceRecipe>();

}
