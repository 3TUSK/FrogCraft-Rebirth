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

package frogcraftrebirth.api;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * This FuelHandler implementation will handle vanilla fuel registration
 * in a more generic way; it will also handle Combustion Furnace fuel and 
 * byproducts registration.
 * 
 * @see frogcraftrebirth.common.tile.TileCombustionFurnace
 * @see net.minecraft.tileentity.TileEntityFurnace#getItemBurnTime
 * @author 3TUSK
 */
public final class FrogFuelHandler {

	FrogFuelHandler() {}

	@Nullable
	public FluidStack getFluidByproduct(@Nonnull ItemStack aStack) {
		if (aStack.isEmpty())
			return null;
		for (Entry<ItemStack, FluidStack> entry : fuel2FluidMap.entrySet()) {
			if (aStack.isItemEqual(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}
	
	@Nullable
	public FluidStack getFluidByproduct(@Nonnull String ore) {
		return ore2FluidMap.get(ore);
	}

	@Nonnull
	public ItemStack getItemByproduct(@Nonnull ItemStack aStack) {
		if (aStack.isEmpty())
			return ItemStack.EMPTY;
		for (Entry<ItemStack, ItemStack> entry : fuel2ByproductMap.entrySet()) {
			if (aStack.isItemEqual(entry.getKey()))
				return entry.getValue();
		}
		return ItemStack.EMPTY;
	}
	
	@Nonnull
	public ItemStack getItemByproduct(@Nonnull String ore) {
		return ore2ByproductMap.getOrDefault(ore, ItemStack.EMPTY);
	}

	public void regFuelByproduct(@Nonnull ItemStack fuel, @Nonnull Fluid byproduct) {
		regFuelByproduct(fuel, new FluidStack(byproduct, Fluid.BUCKET_VOLUME));
	}

	public void regFuelByproduct(@Nonnull ItemStack fuel, @Nonnull FluidStack byproduct) {
		fuel2FluidMap.put(fuel, byproduct);
	}

	public void regFuelByproduct(@Nonnull ItemStack fuel, @Nonnull ItemStack byproduct) {
		fuel2ByproductMap.put(fuel, byproduct);
	}
	
	public void regFuelByproduct(@Nonnull String ore, @Nonnull Fluid byproduct) {
		regFuelByproduct(ore, new FluidStack(byproduct, Fluid.BUCKET_VOLUME));
	}
	
	public void regFuelByproduct(@Nonnull String ore, @Nonnull FluidStack byproduct) {
		ore2FluidMap.put(ore, byproduct);
	}
	
	public void regFuelByproduct(@Nonnull String ore, @Nonnull ItemStack byproduct) {
		ore2ByproductMap.put(ore, byproduct);
	}

	private final Map<ItemStack, FluidStack> fuel2FluidMap = new LinkedHashMap<>();
	private final Map<ItemStack, ItemStack> fuel2ByproductMap = new LinkedHashMap<>();
	private final Map<String, FluidStack> ore2FluidMap = new HashMap<>();
	private final Map<String, ItemStack> ore2ByproductMap = new HashMap<>();
}
