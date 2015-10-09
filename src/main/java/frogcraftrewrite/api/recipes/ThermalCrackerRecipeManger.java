package frogcraftrewrite.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

public class ThermalCrackerRecipeManger implements IRecipeManager<ThermalCrackerRecipe>{

	@Override
	public boolean equal(ThermalCrackerRecipe recipe1, ThermalCrackerRecipe recipe2) {
		return (recipe1.getInput() == recipe2.getInput());
	}

	@Override
	public boolean add(ThermalCrackerRecipe recipe) {
		return recipes.add(recipe);
	}

	@Override
	public boolean remove(ThermalCrackerRecipe recipe) {
		return recipes.remove(recipe);
	}
	
	@Override
	public Collection<ThermalCrackerRecipe> getRecipes() {
		return recipes;
	}
	
	private static ArrayList<ThermalCrackerRecipe> recipes = new ArrayList<ThermalCrackerRecipe>();

}
