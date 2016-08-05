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

import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import net.minecraft.item.ItemStack;

public class AdvChemRecRecipe implements IAdvChemRecRecipe {
	
	private final Collection<OreStack> inputs;
	private final Collection<ItemStack> outputs;
	private final ItemStack validCatalyst;
	private final int time, energyPerTick;
	private final int cellReq, cellProduce;
	
	public AdvChemRecRecipe(Collection<OreStack> inputs, Collection<ItemStack> outputs, ItemStack catalyst, int time, int energyPerTick, int cellReq, int cellProduce) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.validCatalyst = catalyst;
		this.time = time;
		this.energyPerTick = energyPerTick;
		this.cellReq = cellReq;
		this.cellProduce = cellProduce;
	}
	
	@Override
	public Collection<OreStack> getInputs() {
		return inputs;
	}
	
	@Override
	public Collection<ItemStack> getOutputs() {
		return outputs;
	}
	
	@Override
	public ItemStack getCatalyst() {
		return validCatalyst;
	}
	
	@Override
	public int getTime() {
		return time;
	}
	
	@Override
	public int getEnergyRate() {
		return energyPerTick;
	}
	
	@Override
	public int getRequiredCellAmount() {
		return this.cellReq;
	}
	
	@Override
	public int getProducedCellAmount() {
		return this.cellProduce;
	}

}
