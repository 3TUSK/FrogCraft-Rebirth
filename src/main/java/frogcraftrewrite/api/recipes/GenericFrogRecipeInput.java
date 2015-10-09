package frogcraftrewrite.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class GenericFrogRecipeInput {
	
	public static GenericFrogRecipeInput convert(Object obj) {
		if (obj instanceof ItemStack)
			return new GenericFrogRecipeInput((ItemStack)obj);
		if (obj instanceof FluidStack)
			return new GenericFrogRecipeInput((FluidStack)obj);
		if (obj instanceof String)
			return new GenericFrogRecipeInput((String)obj);
		
		return null;
	}

	//Warning: unclear data type
	private Object inputObj;
	
	public GenericFrogRecipeInput(ItemStack obj) {
		this.inputObj = obj;
	}
	
	public GenericFrogRecipeInput(FluidStack obj) {
		this.inputObj = obj;
	}
	
	public GenericFrogRecipeInput(String obj) {
		this.inputObj = obj;
	}
	
	public ItemStack toStack() {
		if (inputObj instanceof ItemStack)
			return (ItemStack)inputObj;
		if (inputObj instanceof String)
			return OreDictionary.getOres((String)inputObj).get(0);
		
		return null;
	}
	
	public FluidStack toFluid() {
		if (inputObj instanceof FluidStack)
			return (FluidStack)inputObj;
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public String toDict() {
		if (inputObj instanceof ItemStack)
			return OreDictionary.getOreName(OreDictionary.getOreID(((ItemStack)inputObj)));
		if (inputObj instanceof FluidStack)
			return FluidRegistry.getFluidName((FluidStack)inputObj);
		if (inputObj instanceof String)
			return (String)inputObj;
		
		return null;
	}

}
