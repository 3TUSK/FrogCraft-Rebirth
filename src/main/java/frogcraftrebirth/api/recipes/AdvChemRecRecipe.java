/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 6:52:19 PM, Jan 28, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.api.recipes;

import java.util.Collection;

public class AdvChemRecRecipe {
	
	//Yes this one will *only* support OreDictionary!
	
	private Collection<String> inputs, outputs;
	private String validCatalyst;
	private int time, energyPerTick;
	
	public AdvChemRecRecipe(Collection<String> inputs, Collection<String> outputs, String catalyst, int time, int energy) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.validCatalyst = catalyst;
		this.time = time;
		this.energyPerTick = energy;
	}
	
	public Collection<String> getInputs() {
		return inputs;
	}
	
	public Collection<String> getOutputs() {
		return outputs;
	}
	
	public String getCatalyst() {
		return validCatalyst;
	}
	
	public int getReactionTime() {
		return time;
	}
	
	public int getEnergyRate() {
		return energyPerTick;
	}

}
