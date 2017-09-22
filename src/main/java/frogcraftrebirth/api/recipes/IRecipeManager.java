package frogcraftrebirth.api.recipes;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * A <code>RecipeManager</code> represents a manager
 * dedicated for a certain type of recipe (which is
 * represented by type parameter, R).
 * @since 1.0.0
 */
public interface IRecipeManager<R> {

	void add(R recipe);

	void remove(R recipe);

	Collection<R> getRecipes();

	/**
	 * @implSpec
	 * Default implementation always returns null, for binary-compatible sake.
	 *
	 * @param input Array of wrapped inputs
	 *
	 * @return The recipe if matched any; null if otherwise.
	 *
	 * @see #getRecipe(Iterable) 
	 */
	@Nullable
	default R getRecipe(IFrogRecipeInput... input) {
		return null;
	}

	/**
	 * {@link Iterable} variant of {@link #getRecipe(IFrogRecipeInput...)},
	 * useful when the inputs are in a {@link java.util.Collection}.
	 *
	 * @implSpec
	 * Default implementation always returns null, for binary-compatible sake.
	 *
	 * @param input Array of wrapped inputs
	 *
	 * @return The recipe if matched any; null if otherwise.
	 *
	 * @see #getRecipe(IFrogRecipeInput...)
	 */
	@Nullable
	default R getRecipe(Iterable<IFrogRecipeInput> input) {
		return null;
	}

}
