package frogcraftrebirth.api.recipes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import net.minecraftforge.fluids.FluidStack;
/**
 * Created at 3:24 P.M. (UTC+8) June 23nd 2015<p>
 * Aim to improve recipe system of FrogCraft, preparing for MineTweaker support.
 * @author 3TUSK
 */
public class CondenseTowerRecipe{
	
	/**Constructor method. Theoretically speaking, the length of output parameter is NOT larger that 4.*/
	public CondenseTowerRecipe(int time, FluidStack input, FluidStack[] output){
		this(time, input, Arrays.asList(output));
	}
	
	public CondenseTowerRecipe(int time, FluidStack input, Collection<FluidStack> output){
		this.output = new java.util.HashSet<FluidStack>();
		this.input = input;
		this.output.addAll(output);
		this.time = time;
	}
	
	public FluidStack getInput() {
		return input;
	}
	
	public Set<FluidStack> getOutput() {
		return output;
	}
	
	public int getTime(){
		return time;
	}
	
	public CondenseTowerRecipe addOutput(FluidStack fluid) {
		output.add(fluid);
		return this;
	}
	
	public CondenseTowerRecipe addOutput(FluidStack... fluids) {
		output.addAll(Arrays.asList(fluids));
		return this;
	}
	
	/**The input.*/
	private FluidStack input;
	/**The output. Notice that output cannot contain two fluid stacks with same type of fluid.*/
	private Set<FluidStack> output;
	/**Time consumed by this recipe.*/
	private int time;

}
