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

package frogcraftrebirth.common.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.oredict.IOreDictEntry;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.common.lib.recipes.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public final class CompatCraftTweaker {

	static IFrogRecipeInput tryResolve(IIngredient ingredient) {
		return tryResolve(ingredient, false);
	}

	static IFrogRecipeInput tryResolve(IIngredient ingredient, boolean itemFirst) {
		if (ingredient == null) {
			return FrogRecipeInputs.EMPTY;
		}
		if (ingredient instanceof IOreDictEntry) {
			return new FrogRecipeInputOreDict(((IOreDictEntry)ingredient).getName(), ingredient.getAmount());
		}
		if (ingredient instanceof IItemStack) {
			return new FrogRecipeInputItemStack((ItemStack)ingredient.getInternal());
		}
		if (ingredient instanceof ILiquidStack) {
			return itemFirst ? new FrogRecipeInputUniversalFluidCell((FluidStack) ingredient.getInternal()) : new FrogRecipeInputFluidStack((FluidStack) ingredient.getInternal());
		}

		CraftTweakerAPI.getLogger().logWarning(String.format("[FrogCraft: Rebirth] Unable resolve IIngredient '%s', resolving as EMPTY", ingredient.toString()));
		return FrogRecipeInputs.EMPTY;
	}

}
