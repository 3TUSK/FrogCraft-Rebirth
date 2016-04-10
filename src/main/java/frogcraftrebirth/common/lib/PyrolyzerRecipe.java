package frogcraftrebirth.common.lib;

import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class PyrolyzerRecipe implements IPyrolyzerRecipe {

	private ItemStack input, output;
	private FluidStack outputFluid;
	private int time;
	
	public PyrolyzerRecipe(ItemStack input, ItemStack output, FluidStack outputFluid, int time){
		this.input = input;
		this.output = output;
		this.outputFluid = outputFluid;
		this.time = time;
	}
	
	public ItemStack getInput() {
		return input;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public FluidStack getOutputFluid() {
		return outputFluid != null ? outputFluid : null;
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
