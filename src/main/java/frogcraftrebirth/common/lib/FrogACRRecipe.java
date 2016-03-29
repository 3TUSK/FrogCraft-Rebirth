/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 6:52:19 PM, Jan 28, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.lib;

import java.util.Collection;
import java.util.Map;

import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;

public class FrogACRRecipe implements IAdvChemRecRecipe {
	
	//Yes this one will *only* support OreDictionary!
	
	private Collection<OreStack> inputs, outputs;
	private Map<String, Double> validCatalyst;
	private int time, energyPerTick;
	
	public FrogACRRecipe(Collection<OreStack> inputs, Collection<OreStack> outputs, Map<String, Double> catalyst, int time, int energy) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.validCatalyst = catalyst;
		this.time = time;
		this.energyPerTick = energy;
	}
	
	@Override
	public Collection<OreStack> getInputs() {
		return inputs;
	}
	
	@Override
	public Collection<OreStack> getOutputs() {
		return outputs;
	}
	
	@Override
	public double getRateModifier(String catalyst) {
		try {
			return validCatalyst.get(catalyst).doubleValue();
		} catch (Exception e) {
			return 0D;
		}
	}
	
	@Override
	public int getTime() {
		return time;
	}
	
	@Override
	public int getEnergyRate() {
		return energyPerTick;
	}

}
