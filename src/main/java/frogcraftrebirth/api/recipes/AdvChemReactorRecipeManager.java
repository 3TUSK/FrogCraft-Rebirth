package frogcraftrebirth.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class AdvChemReactorRecipeManager implements IRecipeManager<AdvChemRecRecipe> {

	@Override
	public boolean equal(AdvChemRecRecipe recipe1, AdvChemRecRecipe recipe2) {
		if (recipe1.getInputs().size() != recipe2.getInputs().size()) 
			return false;
		if (!recipe1.getInputs().containsAll(recipe2.getInputs()))
			return false;
		return true;
	}

	@Override
	public void add(AdvChemRecRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(AdvChemRecRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<AdvChemRecRecipe> getRecipes() {
		return recipes;
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <ItemStack> AdvChemRecRecipe getRecipe(@SuppressWarnings("unchecked")ItemStack... inputs) {
		AdvChemRecRecipe aRecipe = null;
		for (AdvChemRecRecipe recipe : recipes) {
			if (recipe.getInputs().containsAll(Arrays.asList(inputs))) {
				aRecipe = recipe;
				break;
			}
		}
		
		return aRecipe;
	}

	private static ArrayList<AdvChemRecRecipe> recipes = new ArrayList<AdvChemRecRecipe>();

}
