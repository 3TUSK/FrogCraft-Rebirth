package frogcraftrebirth.api.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;

public class AdvChemRecRecipeManager implements IRecipeManager<AdvChemRecRecipe> {

	@Override
	public boolean equal(AdvChemRecRecipe recipe1, AdvChemRecRecipe recipe2) {
		if (recipe1.getInputs().size() != recipe2.getInputs().size()) 
			return false;
		if (recipe1.getInputs().keySet().containsAll(recipe2.getInputs().keySet()))
			return true;
			else return false;
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
		
		Collection<String> inputsList = Arrays.asList(ItemUtil.asOreDictInputsArray((net.minecraft.item.ItemStack[])inputs));
		
		for (AdvChemRecRecipe recipe : recipes) {
			if (recipes.containsAll(inputsList)) {
				aRecipe = recipe;
				break;
			}
		}
		
		return aRecipe;
	}

	private static ArrayList<AdvChemRecRecipe> recipes = new ArrayList<AdvChemRecRecipe>();

}
