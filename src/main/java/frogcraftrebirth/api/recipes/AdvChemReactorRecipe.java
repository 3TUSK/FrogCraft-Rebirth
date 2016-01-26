package frogcraftrebirth.api.recipes;

import java.util.List;

import frogcraftrebirth.api.item.ICatalystModuleItem;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created at 3:41 P.M. (UTC+8) June 22nd 2015<p>
 * Aim to improve recipe system of FrogCraft, preparing for MineTweaker support.
 * @author 3TUSK
 */
public class AdvChemReactorRecipe{
	
	/**The input and output.*/
	private List<ItemStack> input, output;
	/**The catalyst. Can be null.*/
	private ICatalystModuleItem catalyst;
	/**Time which will spend on processing*/
	private int time, energyRate;
	
	/**Constructor method without catalyst. By default the <code>accelerate</code> parameter will be 1 if no catalyst.*/
	public AdvChemReactorRecipe(ItemStack[] input, ItemStack[] output, int time, int energyRate) {
		this(new ArrayList<ItemStack>(Arrays.asList(input)), new ArrayList<ItemStack>(Arrays.asList(output)), null, time, energyRate);
	}
	
	/**Constructor method.*/
	public AdvChemReactorRecipe(List<ItemStack> input, List<ItemStack> output, ICatalystModuleItem catalyst, int time, int energyRate) {
		this.input = input;
		this.output = output;
		this.catalyst = catalyst;
		this.time = time;
	}
	
	/**Internal usage only, for checking whether a input combination matches a recipe.*/
	public AdvChemReactorRecipe(List<ItemStack> input, ICatalystModuleItem catalyst) {
		this.input = input;
		this.catalyst = catalyst;
	}
	
	public List<ItemStack> getInput() {
		return input;
	}
	
	public List<ItemStack> getOutput() {
		return output;
	}
	
	public ICatalystModuleItem getCatalyst() {
		return catalyst;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getEnergyPerTick() {
		return energyRate;
	}

	
	public boolean hasCatalyst() {
		return catalyst != null;
	}
	
	public AdvChemReactorRecipe setCatalyst(ICatalystModuleItem catalyst, float accelerate) {
		this.catalyst = catalyst;
		return this;
	}
	
	public AdvChemReactorRecipe cancelCatalyst(){
		this.catalyst = null;
		return this;
	}

}
