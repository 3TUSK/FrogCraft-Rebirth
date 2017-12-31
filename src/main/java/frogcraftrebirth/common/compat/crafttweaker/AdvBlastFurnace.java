/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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
import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import frogcraftrebirth.common.lib.AdvBlastFurnaceRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ModOnly(FrogAPI.MODID)
@ZenClass("mods.frogcraftrebirth.AdvancedBlastFurnace")
@ZenRegister
public final class AdvBlastFurnace {

	@ZenMethod
	public static void add(
			IIngredient inputPrimary,
			IIngredient inputSecondary,
			ILiquidStack inputFluid,
			IItemStack outputPrimary,
			IItemStack outputSecondary,
			ILiquidStack shield,
			int time,
			int heatConsumption
	) {
		CraftTweakerAPI.apply(new Addition(inputPrimary, inputSecondary, inputFluid, outputPrimary, outputSecondary, shield, time, heatConsumption));
	}

	@ZenMethod
	public static void remove(
			IIngredient inputPrimary,
			IIngredient inputSecondary
	) {
		CraftTweakerAPI.apply(new Removal(inputPrimary, inputSecondary));
	}

	private static final class Addition implements IAction {

		private final IAdvBlastFurnaceRecipe recipe;

		Addition(IIngredient inputPrimary, IIngredient inputSecondary, ILiquidStack inputFluid, IItemStack outputPrimary, IItemStack outputSecondary, ILiquidStack shield, int time, int heatConsumption) {
			IFrogRecipeInput actualInputPrimary = CompatCraftTweaker.tryResolve(inputPrimary);
			IFrogRecipeInput actualInputSecondary = CompatCraftTweaker.tryResolve(inputSecondary);
			FluidStack actualInputFluid = (FluidStack) inputFluid.getInternal();
			ItemStack actualOutputPrimary = outputPrimary == null ? ItemStack.EMPTY : (ItemStack) outputPrimary.getInternal();
			ItemStack actualOutputSecondary = outputSecondary == null ? ItemStack.EMPTY : (ItemStack) outputSecondary.getInternal();
			Fluid actualShield = shield.getInternal() == null ? null : ((FluidStack)shield.getInternal()).getFluid();
			recipe = new AdvBlastFurnaceRecipe(actualInputPrimary, actualInputSecondary, actualInputFluid, actualOutputPrimary, actualOutputSecondary, actualShield, time, heatConsumption);
		}

		@Override
		public void apply() {
			FrogAPI.managerABF.add(recipe);
		}

		@Override
		public String describe() {
			return null;
		}
	}

	private static final class Removal implements IAction {

		private final IAdvBlastFurnaceRecipe recipe;

		Removal(IIngredient inputPrimary, IIngredient inputSecondary) {
			IFrogRecipeInput actualInputPrimary = CompatCraftTweaker.tryResolve(inputPrimary);
			IFrogRecipeInput actualInputSecondary = CompatCraftTweaker.tryResolve(inputSecondary);
			for (IAdvBlastFurnaceRecipe r : FrogAPI.managerABF.getRecipes()) {
				if (r.getInput().matches(actualInputPrimary) && r.getInputSecondary().matches(actualInputSecondary)) {
					this.recipe = r;
					return;
				}
			}
			this.recipe = null;
		}

		@Override
		public void apply() {
			FrogAPI.managerABF.remove(this.recipe);
		}

		@Override
		public String describe() {
			return null;
		}
	}
}
