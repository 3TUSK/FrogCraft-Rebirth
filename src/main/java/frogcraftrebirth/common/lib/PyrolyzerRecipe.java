package frogcraftrebirth.common.lib;

import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class PyrolyzerRecipe implements IPyrolyzerRecipe {

	private final ItemStack input, output;
	private final FluidStack outputFluid;
	private final int time, energyPerTick;
	
	public PyrolyzerRecipe(ItemStack input, ItemStack output, FluidStack outputFluid, int time, int energyPerTick){
		this.input = input;
		this.output = output;
		this.outputFluid = outputFluid;
		this.time = time;
		this.energyPerTick = energyPerTick;
	}
	
	public ItemStack getInput() {
		return input.copy();
	}
	
	public ItemStack getOutput() {
		return output.copy();
	}
	
	public FluidStack getOutputFluid() {
		return outputFluid != null ? outputFluid.copy() : null;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getEnergyPerTick() {
		return energyPerTick;
	}
	
	@Override
	public boolean equals(Object r) {
		return r instanceof PyrolyzerRecipe && ((PyrolyzerRecipe)r).getInput().isItemEqual(this.input);
	}

}
