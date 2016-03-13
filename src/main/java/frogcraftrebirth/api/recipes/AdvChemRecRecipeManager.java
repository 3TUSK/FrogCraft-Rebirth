package frogcraftrebirth.api.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;

public class AdvChemRecRecipeManager implements IRecipeManager<IAdvChemRecRecipe> {

	@Override
	public boolean equal(IAdvChemRecRecipe recipe1, IAdvChemRecRecipe recipe2) {
		if (recipe1.getInputs().size() != recipe2.getInputs().size()) 
			return false;
		if (recipe1.getInputs().keySet().containsAll(recipe2.getInputs().keySet()))
			return true;
			else return false;
	}

	@Override
	public void add(IAdvChemRecRecipe recipe) {
		recipes.add(recipe);
	}

	@Override
	public void remove(IAdvChemRecRecipe recipe) {
		recipes.remove(recipe);
	}
	
	@Override
	public Collection<IAdvChemRecRecipe> getRecipes() {
		return recipes;
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <ItemStack> IAdvChemRecRecipe getRecipe(@SuppressWarnings("unchecked")ItemStack... inputs) {
		IAdvChemRecRecipe aRecipe = null;
		
		Collection<String> inputsList = Arrays.asList(ItemUtil.asOreDictInputsArray((net.minecraft.item.ItemStack[])inputs));
		
		for (IAdvChemRecRecipe recipe : recipes) {
			if (recipes.containsAll(inputsList)) {
				aRecipe = recipe;
				break;
			}
		}
		
		return aRecipe;
	}

	private static ArrayList<IAdvChemRecRecipe> recipes = new ArrayList<IAdvChemRecRecipe>();

}
