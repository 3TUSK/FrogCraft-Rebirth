package frogcraftrewrite.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class AdvChemReactorRecipeManager implements IRecipeManager<AdvChemReactorRecipe> {

	@Override
	public boolean equal(AdvChemReactorRecipe recipe1, AdvChemReactorRecipe recipe2) {
		if (recipe1.getInput().size() != recipe2.getInput().size()) 
			return false;
		if (!recipe1.getInput().containsAll(recipe2.getInput()))
			return false;
		return true;
	}

	@Override
	public boolean add(AdvChemReactorRecipe recipe) {
		return recipes.add(recipe);
	}

	@Override
	public boolean remove(AdvChemReactorRecipe recipe) {
		return recipes.remove(recipe);
	}
	
	@Override
	public Collection<AdvChemReactorRecipe> getRecipes() {
		return recipes;
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <ItemStack> AdvChemReactorRecipe getRecipe(@SuppressWarnings("unchecked")ItemStack... inputs) {
		AdvChemReactorRecipe aRecipe = null;
		for (AdvChemReactorRecipe recipe : recipes) {
			if (recipe.getInput().containsAll(Arrays.asList(inputs))) {
				aRecipe = recipe;
				break;
			}
		}
		
		return aRecipe;
	}

	private static ArrayList<AdvChemReactorRecipe> recipes = new ArrayList<AdvChemReactorRecipe>();

}
