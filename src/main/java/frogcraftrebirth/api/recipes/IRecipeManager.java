package frogcraftrebirth.api.recipes;

import java.util.Collection;

/** Meaningless. */
public interface IRecipeManager<R> {

	boolean equal(R recipe1, R recipe2);

	void add(R recipe);

	void remove(R recipe);

	Collection<R> getRecipes();

	@SuppressWarnings("unchecked")
	<T> R getRecipe(T... input);
}
