package frogcraftrebirth.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IAdvBlastFurnaceRecipe {

	IFrogRecipeInput getInput();

	IFrogRecipeInput getInputSecondary();

	FluidStack getInputFluid();

	ItemStack getOutput();

	ItemStack getByproduct();

	/**
	 *
	 * @implSpec
	 * The return value must satisfy that Fluid.isGaseous() == true.
	 *
	 * @return The type of shield gas, in {@link Fluid} form.
	 *
	 * @throws IllegalStateException only if Fluid::isGaseous == false. Optional.
	 *
	 * @see Fluid#isGaseous()
	 */
	Fluid getShieldGas();

	int getTime();

	/**
	 * Returns the heat energy consumption per game tick.
	 * @return HU consumption, in HU/tick.
	 */
	int getHeatConsumption();
}
