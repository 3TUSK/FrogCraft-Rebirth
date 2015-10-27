package frogcraftrewrite.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ThermalCrackerRecipe{

	private ItemStack input, output;
	private FluidStack[] outputFluid;
	private int time;
	
	public ThermalCrackerRecipe(ItemStack input, ItemStack output, FluidStack[] outputFluid, int time){
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
	
	public FluidStack[] getOutputFluid() {
		return outputFluid;
	}
	
	public int getTime() {
		return time;
	}
	
	@Override
	public boolean equals(Object r) {
		if (r instanceof ThermalCrackerRecipe) return false;
		
		if (((ThermalCrackerRecipe)r).getInput().isItemEqual(this.input)) 
			return true;
		else
			return false;
	}

}
