/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * A <code>IFrogRecipeInput</code> represents a wrapped input ingredient,
 * with its actual type not being exposed by default.
 */
public interface IFrogRecipeInput {

	/**
	 * Determine whether this input object is empty, which implies calling either
	 * {@link #matches(Object)} or {@link #matches(IFrogRecipeInput)} will return {@code false}.
	 *
	 * The definition of emptiness varies on implementations. For example, instance of ItemStack is
	 * considered empty when: 1. size < 1, or 2. getItem() == Items.AIR, or 3. that instance is
	 * the ItemStack.EMPTY instance.
	 *
	 * @return true if this ingredient is empty.
	 */
	default boolean isEmpty() {
		return false;
	}

	/**
	 * Determine whether the given object can
	 *
	 * @implSpec
	 * The following contracts must be followed:
	 * <ul>
	 *     <li>
	 *         Consistent. i.e. For IFrogRecipeInput x, y, multiple invocations of
	 *         {@code x.matches(y)} must not evaluate to different result, if and only
	 *         if none of them are modified among invocations.
	 *     </li>
	 *     <li>
	 *         If the given {@code input} object is instance of {@code IFrogRecipeInput},
	 *         then this method should delegate to {@link #matches(IFrogRecipeInput)}.
	 *         Otherwise, the rules stated below applies.
	 *     </li>
	 *     <li>
	 *         For any instance of this class x, {@code x.matches(null)} holds false.
	 *     </li>
	 *     <li>
	 *         For any instance of this class x, {@code x.isEmpty()} holds true implies
	 *         that this method will always return {@code false}.
	 *     </li>
	 * </ul>
	 * That said, this is not a equivalence relation, as {@code x.matches(y)} does not
	 * guarantee {@code y.matches(x)} (under most circumstances it's not true, example -
	 * input of "1 piles of sugar" may match "4 pile of sugar", but not vice versa.)
	 *
	 * @param input Wrapped <code>IFrogRecipeInput</code>
	 *
	 * @return true if the given actual input is matched; false for otherwise.
	 *
	 * @see Object#equals
	 */
	boolean matches(Object input);

	/**
	 * A specialized version of {@link #matches(Object)}.
	 *
	 * @implSpec
	 * The following contracts must be followed:
	 * <ul>
	 *     <li>
	 *         Reflexive. i.e. For IFrogRecipeInput x, {@code x.matches(x)} holds true.
	 *     </li>
	 *     <li>
	 *         Transitive. i.e. For IFrogRecipeInput x, y, z, both {@code x.matches(y)}
	 *         and {@code y.matches(z)} holding true imply that {@code x.matches(z)}
	 *         holds true.
	 *     </li>
	 *     <li>
	 *         Consistent. i.e. For IFrogRecipeInput x, y, multiple invocations of
	 *         {@code x.matches(y)} must not evaluate to different result, if and only
	 *         if none of them are modified among invocations.
	 *     </li>
	 *     <li>
	 *         For any instance of this class x, {@code x.matches(null)} holds false.
	 *     </li>
	 *     <li>
	 *         For any instance of this class x, {@code x.isEmpty()} holds true implies
	 *         that this method will always return {@code false}, unless the given {@code
	 *         input} is x itself, in which it will return {@code true} instead.
	 *     </li>
	 * </ul>
	 * That said, this is not a equivalence relation, as {@code x.matches(y)} does not
	 * always guarantee {@code y.matches(x)}. In another word, this method is antisymmetric.
	 * Example:
	 * <ul>
	 *     <li>
	 *         Input of "1 piles of sugar" may match "4 pile of sugar", but not vice versa.
	 *     </li>
	 *     <li>
	 *         Input of "1 iron ingot" can of course match "1 iron ingot", and vice versa.
	 *     </li>
	 * </ul>
	 *
	 * @param input The actual input object to be examined.
	 * @return true if the given actual input has same type with this ingredient, and size of actual
	 *         input is larger than size of this object; false for otherwise.
	 */
	default boolean matches(IFrogRecipeInput input) {
		return this.equals(input);
	}

	/**
	 * Retrieve a view-only list in which:
	 * <ul>
	 *     <li>for all element e in the list, {@code type.isInstance(e)} holds true</li>
	 *     <li>for all element e in the list, {@code this.matches(e)} holds true</li>
	 * </ul>
	 *
	 * @implSpec
	 * Returning {@code null} is prohibited. In case there is no instances of given type
	 * to put into the returning List, returning an empty list, preferably that of
	 * {@link java.util.Collections#emptyList}.
	 *
	 * @param type The type reference of actual inputs
	 * @param <T> The type of inputs (e.g ItemStack, FluidStack, etc.)
	 * @return A view-only list that contains given type of inputs, may be empty.
	 */
	@Nonnull
	<T> List<T> getActualInputs(Class<T> type);

	/**
	 * Retrieve the size of this input. The definition of size varies on the actual
	 * input type, i.e. return value of this method can only be used when type of the
	 * wrapped input is known.
	 *
	 * Example: if one implementation wraps an ItemStack, then getSize() can only be
	 * used on other ItemStack, not FluidStack or anything else.
	 *
	 * For consuming arbitrary ingredients, use {@link #accepts(Class, Object)}.
	 *
	 * @return Quantity of this input ingredient
	 */
	int getSize();

	/**
	 * Accepts arbitrary ingredient input and consumes it according to the type provided.
	 *
	 * @param type The type reference of actual input
	 * @param actualInput The actual input ingredient object
	 * @param <I> The type of actual input
	 *
	 * @return The remainder, maybe <code>null</code>.
	 */
	@Nullable
	<I> I accepts(Class<I> type, I actualInput);
}
