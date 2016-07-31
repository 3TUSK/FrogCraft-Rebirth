package frogcraftrebirth.common.lib;

import java.util.ArrayList;
import java.util.Collection;

import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;

public class PyrolyzerRecipeManger implements IRecipeManager<IPyrolyzerRecipe>{

	@Override
	public boolean equal(IPyrolyzerRecipe recipe1, IPyrolyzerRecipe recipe2) {
		return (recipe1.getInput() == recipe2.getInput());
	}

	@Override
	public void add(IPyrolyzerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(IPyrolyzerRecipe recipe) {
		java.util.Iterator<IPyrolyzerRecipe> iter = recipes.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(recipe)) {
				iter.remove();
				return;
			}
		}
	}
	
	@Override
	public Collection<IPyrolyzerRecipe> getRecipes() {
		return recipes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <ItemStack> IPyrolyzerRecipe getRecipe(ItemStack... inputs) {
		ItemStack input = (ItemStack)inputs[0];
		if (input == null)
			return null;
		for (IPyrolyzerRecipe r : recipes) {
			if (r.getInput().isItemEqual((net.minecraft.item.ItemStack)input) 
					&& r.getInput().stackSize <= ((net.minecraft.item.ItemStack)input).stackSize)
				return r;
		}
		return null;
	}
	
	private static ArrayList<IPyrolyzerRecipe> recipes = new ArrayList<IPyrolyzerRecipe>();

}
