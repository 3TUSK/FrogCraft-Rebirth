/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
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
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.common.lib.AdvChemRecRecipe;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@ModOnly(FrogAPI.MODID)
@ZenClass("mods.frogcraftrebirth.AdvancedChemicalReactor")
@ZenRegister
public final class AdvChemReactor {

	public static void add(
			IIngredient[] input,
			IItemStack[] output,
			IItemStack catalyst,
			int cellIn,
			int cellOut,
			int time,
			int power
	) {
		CraftTweakerAPI.apply(new Addition(input, output, catalyst, cellIn, cellOut, time, power));
	}

	public static void remove(
			IIngredient[] input
	) {
		CraftTweakerAPI.apply(new Removal(input));
	}

	private static final class Addition implements IAction {

		private final IAdvChemRecRecipe recipe;

		Addition(IIngredient[] input, IItemStack[] output, IItemStack catalyst, int cellIn, int cellOut, int time, int power) {
			Collection<IFrogRecipeInput> actualInput = Arrays.stream(input).map(i -> CompatCraftTweaker.tryResolve(i, true)).collect(Collectors.toSet());
			Collection<ItemStack> actualOutput = Arrays.stream(output).map(i -> (ItemStack) i.getInternal()).collect(Collectors.toList());
			ItemStack actualCatalyst = catalyst != null ? (ItemStack) catalyst.getInternal() : ItemStack.EMPTY;
			this.recipe = new AdvChemRecRecipe(actualInput, actualOutput, actualCatalyst, time, power, cellIn, cellOut);
		}

		@Override
		public void apply() {
			FrogAPI.managerACR.add(recipe);
		}

		@Override
		public String describe() {
			return null;
		}
	}

	private static final class Removal implements IAction {

		private final IAdvChemRecRecipe recipe;

		Removal(IIngredient[] input) {
			recipe = FrogAPI.managerACR.getRecipe(Arrays.stream(input).map(i -> CompatCraftTweaker.tryResolve(i, true)).collect(Collectors.toList()));
		}

		@Override
		public void apply() {
			if (recipe != null) {
				FrogAPI.managerACR.remove(recipe);
			}
		}

		@Override
		public String describe() {
			return null;
		}
	}
}
