package frogcraftrebirth.common.lib;

import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class PyrolyzerRecipe implements IPyrolyzerRecipe {

	private final ItemStack input, output;
	private final FluidStack outputFluid;
	private final int time;
	
	public PyrolyzerRecipe(ItemStack input, ItemStack output, FluidStack outputFluid, int time){
		this.input = input;
		this.output = output;
		this.outputFluid = outputFluid;
		this.time = time;
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
	
	@Override
	public boolean equals(Object r) {
		if (!(r instanceof PyrolyzerRecipe)) 
			return false;
		
		return ((PyrolyzerRecipe)r).getInput().isItemEqual(this.input);
	}

}
