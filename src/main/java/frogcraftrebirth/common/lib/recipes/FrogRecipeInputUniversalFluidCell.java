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

package frogcraftrebirth.common.lib.recipes;

import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A specialized {@link IFrogRecipeInput} that only matches Universal Fluid Cell
 * from IndustrialCraft2. Used in Adv. Chemical Reactor, serving as a solution
 * to match the behavior from FrogCraft where it accepts IC2 cell (removed around
 * 1.10.2).
 *
 * @implSpec
 * The <code>getSize</code> method implemented here always return the ItemStack size.
 * If FluidStack.amount is needed, it is safe to assume that it is 1000 * getSize().
 *
 * @implNote
 * By default, it makes assumption that the given FluidStack instanced has amount
 * of multiple of 1000. This assumption is based on the legacy IC2 Cell behavior
 * where it always has volume of 1000 mB. As an direct result, this input cannot
 * support fluid input that has volume of non-1000-multiple.
 *
 * @see frogcraftrebirth.common.tile.TileAdvChemReactor
 */
public class FrogRecipeInputUniversalFluidCell implements IFrogRecipeInput {

	private final FluidStack stack;
	private final int size;

	public FrogRecipeInputUniversalFluidCell(FluidStack stack) {
		this.stack = stack;
		this.size = stack.amount / 1000;
	}

	public FrogRecipeInputUniversalFluidCell(ItemStack stack) {
		if (FrogRecipeInputs.UNI_CELL.isItemEqual(stack)) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(stack);
			Objects.requireNonNull(handler, "An IC2 Universal Fluid Cell is missing its fluid handler, possibly due to corrupted data");
			FluidStack f = handler.getTankProperties()[0].getContents();
			Objects.requireNonNull(f, "Cannot pass empty IC2 Universal Fluid Cell here!");
			this.stack = f;
			this.size = f.amount / 1000;
		} else {
			throw new IllegalArgumentException("Only IC2 Universal Fluid Cell can pass into this constructor");
		}
	}

	@Override
	public boolean matches(IFrogRecipeInput input) {
		List<FluidStack> listFluid = input.getActualInputs(FluidStack.class);
		if (listFluid.size() > 0) {
			return listFluid.stream().anyMatch(this.stack::equals);
		} else {
			ItemStack stack = input.getActualInputs(ItemStack.class).get(0);
			return ItemStack.areItemsEqual(stack, FrogRecipeInputs.UNI_CELL) && this.stack.isFluidEqual(stack);
		}
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput instanceof ItemStack && FrogRecipeInputs.UNI_CELL.isItemEqual((ItemStack)actualInput) && stack.isFluidEqual((ItemStack)actualInput);
	}

	@Nonnull
	@Override
	public <T> List<T> getActualInputs(Class<T> type) {
		if (type == ItemStack.class) {
			ItemStack result = FrogRecipeInputs.UNI_CELL.copy();
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(result);
			if (handler != null) { // For safety sake
				handler.fill(stack.copy(), true);
				return Collections.singletonList(type.cast(result));
			}
		}

		return Collections.emptyList();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		if (type == ItemStack.class) {
			ItemStack itemStack = (ItemStack)actualInput;
			if (FrogRecipeInputs.UNI_CELL.isItemEqual(itemStack)) {
				itemStack.shrink(this.size);
				return actualInput;
			}
		}

		return actualInput;
	}

	@Override
	public boolean equals(Object o) {
		return this == o || o instanceof FrogRecipeInputUniversalFluidCell && ((FrogRecipeInputUniversalFluidCell)o).stack.isFluidEqual(this.stack);
	}
}
