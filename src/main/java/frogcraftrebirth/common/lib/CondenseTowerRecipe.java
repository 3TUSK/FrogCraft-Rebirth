package frogcraftrebirth.common.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import frogcraftrebirth.api.recipes.ICondenseTowerRecipe;
import net.minecraftforge.fluids.FluidStack;

public class CondenseTowerRecipe implements ICondenseTowerRecipe {
	
	private final FluidStack input;
	private final Set<FluidStack> output;
	private final int time, energyPerTick;

	public CondenseTowerRecipe(int time, int energyPerTick, FluidStack input, FluidStack[] output) {
		this(time, energyPerTick, input, Arrays.asList(output));
	}

	public CondenseTowerRecipe(int time, int energyPerTick, FluidStack input, Collection<FluidStack> output) {
		this.output = new HashSet<>(output);
		this.input = input;
		this.time = time;
		this.energyPerTick = energyPerTick;
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
	public int getEnergyPerTick() {
		return energyPerTick;
	}

}
