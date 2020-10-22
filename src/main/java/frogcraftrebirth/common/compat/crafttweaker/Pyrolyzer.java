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
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;

@ModOnly(FrogAPI.MODID)
@ZenClass("mods.frogcraftrebirth.Pyrolyzer")
@ZenRegister
public final class Pyrolyzer {

	public static void add(
			IIngredient input,
			IItemStack output,
			ILiquidStack outputFluid,
			int time,
			int power
	) {
		CraftTweakerAPI.apply(new Addition(input, output, outputFluid, time, power));
	}

	public static void remove(
			IItemStack input
	) {
		CraftTweakerAPI.apply(new Removal(input));
	}

	private static final class Addition implements IAction {

		private final IPyrolyzerRecipe recipe;

		Addition(IIngredient input, IItemStack output, ILiquidStack outputFluid, int time, int power) {
			IFrogRecipeInput actualInput = CompatCraftTweaker.tryResolve(input);
			ItemStack actualOutput = output == null ? ItemStack.EMPTY : (ItemStack) output.getInternal();
			FluidStack actualOutputFluid = outputFluid != null ? (FluidStack) outputFluid.getInternal() : null;
			this.recipe = new PyrolyzerRecipe(actualInput, actualOutput, actualOutputFluid, time, power);
		}

		@Override
		public void apply() {
			FrogAPI.managerPyrolyzer.add(this.recipe);
		}

		@Override
		public String describe() {
			return null;
		}
	}

	private static final class Removal implements IAction {

		private final IPyrolyzerRecipe recipe;

		Removal(IItemStack input) {
			ItemStack actualInput = (ItemStack) input.getInternal();
			if (actualInput == null) {
				actualInput = ItemStack.EMPTY;
			}
			for (IPyrolyzerRecipe r : FrogAPI.managerPyrolyzer.getRecipes()) {
				if (r.getActualInput().matches(actualInput)) {
					this.recipe = r;
					return;
				}
			}
			this.recipe = null;
		}

		@Override
		public void apply() {
			FrogAPI.managerPyrolyzer.remove(this.recipe);
		}

		@Override
		public String describe() {
			return null;
		}
	}
}
