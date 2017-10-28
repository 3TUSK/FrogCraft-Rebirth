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

import java.util.Collection;

import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;

public class AdvChemRecRecipe implements IAdvChemRecRecipe {
	
	private final Collection<IFrogRecipeInput> inputs;
	private final Collection<ItemStack> outputs;
	private final ItemStack validCatalyst;
	private final int time, energyPerTick;
	private final int cellReq, cellProduce;
	
	public AdvChemRecRecipe(Collection<IFrogRecipeInput> inputs, Collection<ItemStack> outputs, ItemStack catalyst, int time, int energyPerTick, int cellReq, int cellProduce) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.validCatalyst = catalyst;
		this.time = time;
		this.energyPerTick = energyPerTick;
		this.cellReq = cellReq;
		this.cellProduce = cellProduce;
	}
	
	@Override
	public Collection<IFrogRecipeInput> getInputs() {
		return inputs;
	}
	
	@Override
	public Collection<ItemStack> getOutputs() {
		return outputs;
	}
	
	@Override
	public ItemStack getCatalyst() {
		return validCatalyst;
	}
	
	@Override
	public int getTime() {
		return time;
	}
	
	@Override
	public int getEnergyRate() {
		return energyPerTick;
	}
	
	@Override
	public int getRequiredCellAmount() {
		return this.cellReq;
	}
	
	@Override
	public int getProducedCellAmount() {
		return this.cellProduce;
	}

}
