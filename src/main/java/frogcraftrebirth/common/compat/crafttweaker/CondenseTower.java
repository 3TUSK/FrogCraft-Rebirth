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
import crafttweaker.api.liquid.ILiquidStack;
import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import frogcraftrebirth.common.lib.CondenseTowerRecipe;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.Validate;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ModOnly(FrogAPI.MODID)
@ZenClass("mods.frogcraftrebirth.CondenseTower")
@ZenRegister
public final class CondenseTower {

	@ZenMethod
	public static void add(
			final ILiquidStack input,
			final ILiquidStack[] outputs,
			final int time,
			final int power
	) {
		CraftTweakerAPI.apply(new Addition(input, outputs, time, power));
	}

	@ZenMethod
	public static void remove(
			final ILiquidStack input
	) {
		CraftTweakerAPI.apply(new Removal(input));
	}

	private static final class Addition implements IAction {

		private final ICondenseTowerRecipe recipe;

		Addition(ILiquidStack input, ILiquidStack[] outputs, int time, int power) {
			Object actualInput = input.getInternal();
			Validate.isInstanceOf(FluidStack.class, actualInput);

			Set<FluidStack> actualOutput = new HashSet<>(outputs.length);
			for (ILiquidStack o : outputs) {
				Object actual = o.getInternal();
				Validate.isInstanceOf(FluidStack.class, actual);
				actualOutput.add((FluidStack)actual);
			}

			Validate.validState(time > 0);
			Validate.validState(power > 0);

			this.recipe = new CondenseTowerRecipe(time, power, (FluidStack)actualInput, Collections.unmodifiableSet(actualOutput));
		}

		@Override
		public void apply() {
			FrogAPI.managerCT.add(this.recipe);
		}

		@Override
		public String describe() {
			return "Adding Condense Tower recipe " + this.recipe;
		}
	}

	private static final class Removal implements IAction {

		private final ICondenseTowerRecipe recipe;

		Removal(ILiquidStack input) {
			for (ICondenseTowerRecipe r : FrogAPI.managerCT.getRecipes()) {
				if (r.getInput().equals(input.getInternal())) {
					this.recipe = r;
					return;
				}
			}
			recipe = null;
		}

		@Override
		public void apply() {
			if (recipe != null) {
				FrogAPI.managerCT.remove(recipe);
			}
		}

		@Override
		public String describe() {
			return "Removing Condense Tower recipe" + this.recipe;
		}
	}
}
