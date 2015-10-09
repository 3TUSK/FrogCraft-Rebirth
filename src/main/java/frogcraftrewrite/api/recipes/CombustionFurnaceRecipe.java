package frogcraftrewrite.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CombustionFurnaceRecipe{
	
	private ItemStack input, output;
	private FluidStack outputFluid;
	
	public CombustionFurnaceRecipe(ItemStack input, ItemStack output, FluidStack outputFluid)
	{
		this.input = input;
		this.output = output;
		this.outputFluid = outputFluid;
	}
	
	public ItemStack getInput() 
	{
		return input;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
	
	public FluidStack getOutputFluid()
	{
		return outputFluid;
	}
	
	

}
