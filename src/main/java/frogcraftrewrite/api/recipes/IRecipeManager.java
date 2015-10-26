package frogcraftrewrite.api.recipes;

import java.util.Collection;

/**Meaningless.*/
public interface IRecipeManager<R> {
	
	public boolean equal(R recipe1, R recipe2);
	
	public boolean add(R recipe);
	
	public boolean remove(R recipe);
	
	public Collection<R> getRecipes();
	
	public <T> R getRecipe(@SuppressWarnings("unchecked")T... input);
}
