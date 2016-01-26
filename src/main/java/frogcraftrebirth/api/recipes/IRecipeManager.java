package frogcraftrebirth.api.recipes;

import java.util.Collection;

/**Meaningless.*/
public interface IRecipeManager<R> {
	
	public boolean equal(R recipe1, R recipe2);
	
	public void add(R recipe);
	
	public void remove(R recipe);
	
	public Collection<R> getRecipes();
	
	public <T> R getRecipe(@SuppressWarnings("unchecked")T... input);
}
