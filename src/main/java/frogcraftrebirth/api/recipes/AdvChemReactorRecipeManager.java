package frogcraftrebirth.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

@Deprecated
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
	public void add(AdvChemReactorRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(AdvChemReactorRecipe recipe) {
		recipes.remove(recipe);
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
