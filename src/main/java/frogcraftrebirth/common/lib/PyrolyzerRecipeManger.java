package frogcraftrebirth.common.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.api.recipes.IRecipeManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PyrolyzerRecipeManger implements IRecipeManager<IPyrolyzerRecipe>{

	@Override
	public void add(IPyrolyzerRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(IPyrolyzerRecipe recipe) {
		recipes.removeIf(recipe::equals);
	}
	
	@Override
	public Collection<IPyrolyzerRecipe> getRecipes() {
		return recipes;
	}

	@Override
	public IPyrolyzerRecipe getRecipe(IFrogRecipeInput... inputs) {
		if (inputs.length == 0)
			return null;
		IFrogRecipeInput input = inputs[0];
		if (input == null)
			return null;
		List<ItemStack> list = input.getActualInputs(ItemStack.class);
		if (list.size() > 0) {
			ItemStack first = list.get(0);
			if (first.isEmpty())
				return null;

			for (IPyrolyzerRecipe r : recipes) {
				if (r.getInput().isItemEqual(first) && first.getCount() > r.getInput().getCount()) {
					return r;
				}
			}
		}
		return null;
	}
	
	private static ArrayList<IPyrolyzerRecipe> recipes = new ArrayList<>();

}
