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

public class FrogRecipeInputFluidStack implements IFrogRecipeInput {

	private final FluidStack stack;

	public FrogRecipeInputFluidStack(FluidStack stack) {
		this.stack = stack;
	}

	@Override
	public boolean matches(IFrogRecipeInput input) {
		return this.equals(input);
	}

	@Override
	public boolean matches(Object actualInput) {
		return actualInput instanceof FluidStack ? stack.isFluidEqual((FluidStack)actualInput) : actualInput instanceof ItemStack && stack.isFluidEqual((ItemStack)actualInput);
	}

	@Nonnull
	@Override
	public <T> List<T> getActualInputs(Class<T> type) {
		return type == FluidStack.class ? Collections.singletonList(type.cast(stack)) : Collections.emptyList();
	}

	@Override
	public int getSize() {
		return stack.amount;
	}

	@Nullable
	@Override
	public <I> I accepts(Class<I> type, I actualInput) {
		if (type == FluidStack.class) {
			FluidStack casted = (FluidStack)actualInput;
			casted.amount -= stack.amount;
			return casted.amount > 0 ? actualInput : null;
		} else if (type == ItemStack.class) {
			ItemStack casted = (ItemStack)actualInput;
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(casted);
			if (handler != null) {
				handler.drain(stack.amount, true);
			}
			return actualInput;
		} else {
			return actualInput; //No-op when type mismatch
		}
	}

	@Override
	public boolean equals(Object o) {
		return o == this || o instanceof FrogRecipeInputFluidStack && ((FrogRecipeInputFluidStack)o).stack.isFluidEqual(this.stack);
	}
}
