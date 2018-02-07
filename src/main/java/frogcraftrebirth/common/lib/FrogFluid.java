/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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

import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FrogFluid extends Fluid {
	
	public FrogFluid(String name, int density, int temperature, boolean gaseous, EnumRarity rarity) {
		this(name, name, name, density, temperature, gaseous, rarity);
	}
	
	public FrogFluid(String name, String flow, String still, int density, int temperature, boolean gaseous, EnumRarity rarity) {
		super(name, new ResourceLocation("frogcraftrebirth", "fluids/" + flow), new ResourceLocation("frogcraftrebirth", "fluids/" + still));
		this.setDensity(density);
		this.setTemperature(temperature);
		this.setGaseous(gaseous);
		this.setRarity(rarity);
	}

	public final String toString() {
		return "FrogFluid{" + this.fluidName + "}";
	}

}
