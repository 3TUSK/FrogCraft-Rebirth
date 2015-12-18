package frogcraftrewrite.api.recipes;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;

public class PyrolyzerRecipeManger implements IRecipeManager<PyrolyzerRecipe>{

	@Override
	public boolean equal(PyrolyzerRecipe recipe1, PyrolyzerRecipe recipe2) {
		return (recipe1.getInput() == recipe2.getInput());
	}

	@Override
	public void add(PyrolyzerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(PyrolyzerRecipe recipe) {
		java.util.Iterator<PyrolyzerRecipe> iter = recipes.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(recipe)) {
				iter.remove();
				return;
			}
		}
	}
	
	@Override
	public Collection<PyrolyzerRecipe> getRecipes() {
		return recipes;
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <ItemStack> PyrolyzerRecipe getRecipe(@SuppressWarnings("unchecked")ItemStack... inputs) {
		ItemStack input = (ItemStack)inputs[0];
		if (input == null)
			return null;
		for (PyrolyzerRecipe r : recipes) {
			if (r.getInput().isItemEqual((net.minecraft.item.ItemStack)input) 
					&& r.getInput().stackSize <= ((net.minecraft.item.ItemStack)input).stackSize)
				return r;
		}
		return null;
	}
	
	private static ArrayList<PyrolyzerRecipe> recipes = new ArrayList<PyrolyzerRecipe>();

}
