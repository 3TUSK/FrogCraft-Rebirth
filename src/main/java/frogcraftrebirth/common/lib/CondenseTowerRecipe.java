package frogcraftrebirth.common.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import net.minecraftforge.fluids.FluidStack;

public class CondenseTowerRecipe implements ICondenseTowerRecipe {
	
	private final FluidStack input;
	private Set<FluidStack> output;
	private final int time;

	public CondenseTowerRecipe(int time, FluidStack input, FluidStack[] output) {
		this(time, input, Arrays.asList(output));
	}

	public CondenseTowerRecipe(int time, FluidStack input, Collection<FluidStack> output) {
		this.output = new HashSet<FluidStack>(output);
		this.input = input;
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

}
