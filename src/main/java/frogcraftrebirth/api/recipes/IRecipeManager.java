/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.api.recipes;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * A <code>RecipeManager</code> represents a manager
 * dedicated for a certain type of recipe (which is
 * represented by type parameter, R).
 *
 * @param <R> The type of recipe objects that this object holds
 *
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
