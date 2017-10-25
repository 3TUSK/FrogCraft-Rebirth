package frogcraftrebirth.api.recipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * A <code>IFrogRecipeInput</code> represents a wrapped input ingredient,
 * with its actual type not being exposed by default.
 */
public interface IFrogRecipeInput {

	/**
	 * A specialized version of {@link #matches(Object)}.
	 * @param input Wrapped <code>IFrogRecipeInput</code>
	 *
	 * @implSpec
	 * Contracts of {@link Object#equals} also applies here, with only difference
	 * that a.matches(b) == true does not guarantee a.equals(b) == true. That said,
	 * this method only checks equivalency based on exchangeability, e.g.
	 * an instance of FluidStack may matches an instance of ItemStack that happens
	 * to be a fluid container with the exact same fluid stack in it.
	 *
	 * @return true if the given actual input is matched; false for otherwise.
	 *
	 * @see Object#equals
	 */
	default boolean matches(IFrogRecipeInput input) {
		return this.equals(input);
	}

	/**
	 * @param actualInput The actual input ingredient.
	 * @return true if the given actual input is matched; false for otherwise.
	 */
	boolean matches(Object actualInput);

	/**
	 * Retrieve a list of possible inputs.
	 * <p>
	 *     Note that, elements in returned list must satisfy that match(input) == true.
	 * </p>
	 * @param type The type reference of actual inputs
	 * @param <T> The type of inputs (e.g ItemStack, FluidStack, etc.)
	 * @return A list contains given type of inputs.
	 */
	@Nonnull
	<T> List<T> getActualInputs(Class<T> type);

	/**
	 * Retrieve the size of this input. The definition of size varies on the actual
	 * input type, i.e. return value of this method can only be used when type of the
	 * wrapped input is known. Example: if one implementation wraps an ItemStack,
	 * then getSize() can only be used on other ItemStack, not FluidStack or anything else.
	 *
	 * If consuming arbitrary ingredients is need, use {@link #accepts(Class, Object)}.
	 * @return Quantity of this input ingredient
	 */
	int getSize();

	/**
	 * Accepts arbitrary ingredient input and consumes it according to the type provided.
	 * @param type The type reference of actual input
	 * @param actualInput The actual input ingredient object
	 * @param <I> The type of actual input
	 * @return The remainder, maybe <code>null</code>.
	 */
	@Nullable
	<I> I accepts(Class<I> type, I actualInput);
}
