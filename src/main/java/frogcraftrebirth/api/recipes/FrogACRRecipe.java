/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 6:52:19 PM, Jan 28, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.recipes;

import java.util.Map;

public class FrogACRRecipe implements IAdvChemRecRecipe {
	
	//Yes this one will *only* support OreDictionary!
	
	private Map<String, Integer> inputs, outputs;
	private Map<String, Double> validCatalyst;
	private int time, energyPerTick;
	
	public FrogACRRecipe(Map<String, Integer> inputs, Map<String, Integer> outputs, Map<String, Double> catalyst, int time, int energy) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.validCatalyst = catalyst;
		this.time = time;
		this.energyPerTick = energy;
	}
	
	@Override
	public Map<String, Integer> getInputs() {
		return inputs;
	}
	
	@Override
	public Map<String, Integer> getOutputs() {
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
