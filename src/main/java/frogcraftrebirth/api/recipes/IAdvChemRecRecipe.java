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

import java.util.Arrays;
import java.util.Collection;

import net.minecraft.item.ItemStack;

public interface IAdvChemRecRecipe {

	Collection<IFrogRecipeInput> getInputs();

	Collection<ItemStack> getOutputs();

	ItemStack getCatalyst();

	int getTime();

	int getEnergyRate();

	default int getRequiredCellAmount() {
		return 0;
	}

	default int getProducedCellAmount() {
		return 0;
	}

	default boolean equals(IAdvChemRecRecipe rec) {
		return rec.getInputs().equals(getInputs());
	}

	default boolean matchInputs(IFrogRecipeInput... inputs) {
		return matchInputs(Arrays.asList(inputs));
	}

	default boolean matchInputs(Iterable<IFrogRecipeInput> inputs) {
		for (IFrogRecipeInput input : getInputs()) {
			boolean found = false;
			for (IFrogRecipeInput check : inputs) {
				if (input.matches(check)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

}
