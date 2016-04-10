package frogcraftrebirth.common.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created at 3:24 P.M. (UTC+8) June 23nd 2015
 * <p>
 * Aim to improve recipe system of FrogCraft, preparing for MineTweaker support.
 * 
 * @author 3TUSK
 */
public class CondenseTowerRecipe implements ICondenseTowerRecipe {
	
	private final FluidStack input;
	private Set<FluidStack> output;
	private final int time;

	public CondenseTowerRecipe(int time, FluidStack input, FluidStack[] output) {
		this(time, input, Arrays.asList(output));
	}

	public CondenseTowerRecipe(int time, FluidStack input, Collection<FluidStack> output) {
		this.output = new java.util.HashSet<FluidStack>();
		this.input = input;
		this.output.addAll(output);
		this.time = time;
	}

	@Override
	public FluidStack getInput() {
		return input;
	}

	@Override
	public Set<FluidStack> getOutput() {
		return output;
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public ICondenseTowerRecipe appendOutput(FluidStack... fluids) {
		output.addAll(Arrays.asList(fluids));
		return this;
	}

}
