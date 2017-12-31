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

package frogcraftrebirth.common.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import net.minecraftforge.fluids.FluidStack;

public class CondenseTowerRecipe implements ICondenseTowerRecipe {
	
	private final FluidStack input;
	private final Set<FluidStack> output;
	private final int time, energyPerTick;

	public CondenseTowerRecipe(int time, int energyPerTick, FluidStack input, FluidStack[] output) {
		this(time, energyPerTick, input, Arrays.asList(output));
	}

	public CondenseTowerRecipe(int time, int energyPerTick, FluidStack input, Set<FluidStack> output) {
		this.output = output;
		this.input = input;
		this.time = time;
		this.energyPerTick = energyPerTick;
	}

	public CondenseTowerRecipe(int time, int energyPerTick, FluidStack input, Collection<FluidStack> output) {
		this.output = new HashSet<>(output);
		this.input = input;
		this.time = time;
		this.energyPerTick = energyPerTick;
	}

	@Override
	public FluidStack getInput() {
		return input;
	}

	@Override
	public Set<FluidStack> getOutput() {
		return output;
	}

	@Override
	public int getTime() {
		return time;
	}
	
	@Override
	public int getEnergyPerTick() {
		return energyPerTick;
	}

}
