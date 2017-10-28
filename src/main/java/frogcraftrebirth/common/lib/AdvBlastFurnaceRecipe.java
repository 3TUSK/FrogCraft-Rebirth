package frogcraftrebirth.common.lib;

import frogcraftrebirth.api.recipes.IAdvBlastFurnaceRecipe;
import frogcraftrebirth.api.recipes.IFrogRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class AdvBlastFurnaceRecipe implements IAdvBlastFurnaceRecipe {

	private final IFrogRecipeInput primaryInput;
	private final IFrogRecipeInput secondaryInput;
	private final FluidStack fluidInput;
	private final ItemStack output;
	private final ItemStack byproduct;
	private final Fluid shieldGas;
	private final int time;
	private final int heatConsumption;

	public AdvBlastFurnaceRecipe(IFrogRecipeInput inputPrimary, IFrogRecipeInput inputSeconary, FluidStack inputFluid, ItemStack outputPrimary, ItemStack outputSecondary, Fluid shield, int time, int heatConsumption) {
		this.primaryInput = inputPrimary;
		this.secondaryInput = inputSeconary;
		this.fluidInput = inputFluid;
		this.output = outputPrimary;
		this.byproduct = outputSecondary;
		this.shieldGas = shield;
		this.time = time;
		this.heatConsumption = heatConsumption;
	}

	@Override
	public IFrogRecipeInput getInput() {
		return primaryInput;
	}

	@Override
	public IFrogRecipeInput getInputSecondary() {
		return secondaryInput;
	}

	@Override
	public FluidStack getInputFluid() {
		return fluidInput;
	}

	@Override
	public ItemStack getOutput() {
		return output;
	}

	@Override
	public ItemStack getByproduct() {
		return byproduct;
	}

	@Override
	public Fluid getShieldGas() {
		return shieldGas;
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public int getHeatConsumption() {
		return heatConsumption;
	}

}
