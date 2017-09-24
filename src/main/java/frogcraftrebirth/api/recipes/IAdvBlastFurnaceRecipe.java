package frogcraftrebirth.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IAdvBlastFurnaceRecipe {

	ItemStack getInput();

	FluidStack getInputFluid();

	ItemStack getOutput();

	/**
	 *
	 * @implSpec
	 * The return value must satisfy that Fluid.isGaseous() == true.
	 *
	 * @return The type of shield gas, in {@link Fluid} form.
	 *
	 * @see Fluid#isGaseous()
	 */
	Fluid getShieldGas();

	int getTime();
}
