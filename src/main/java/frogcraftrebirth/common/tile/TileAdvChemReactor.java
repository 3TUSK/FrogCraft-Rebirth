package frogcraftrebirth.common.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class TileAdvChemReactor extends TileFrogMachine {
	public int process, processMax;
	boolean working, changed = false, inputsLocked = false, outputLocked = false;
	private IAdvChemRecRecipe recipe;
	public TileAdvChemReactor() {
		super(13, "TileEntityAdvancedChemicalReactor", 2, 100000);
		//0 for module, 1-5 for input, 6-10 for output, 11 for cell input and 12 for cell output
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.process = tag.getInteger("process");
		this.processMax = tag.getInteger("processMax");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("working", this.working);
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) 
			return;
		
		recipe = (IAdvChemRecRecipe)FrogAPI.managerACR.<ItemStack[]>getRecipe(Arrays.copyOfRange(inv, 1, 5));
		
		if (recipe == null)
			return;
		
		//Map<String, Integer> recipeInput = recipe.getInputs();
		
		working = canWork(recipe);
		
		if (working) {
			working = canWork(recipe);
		} else 
			return;
		
		if (working && charge >= recipe.getEnergyRate()) {
			this.charge -= recipe.getEnergyRate();
			++process;
		}
		
		if (process == processMax) {
			Map<String, Integer> recipeOutput = recipe.getOutputs();
			ArrayList<ItemStack> product = new ArrayList<ItemStack>();
			//Overhaul required
			for (Entry<String, Integer> ore : recipeOutput.entrySet()) {
				ItemStack stack = OreDictionary.getOres(ore.getKey()).get(0);
				stack.stackSize = ore.getValue();
				product.add(stack);
			}
			
			this.changed = true;
			this.working = false;
		}
		
		if (changed) {
			this.markDirty();
			this.sendTileUpdatePacket(this);
			changed = false;
		}
	}
	
	//TODO: COMPLETE THIS METHOD
	public boolean canWork(IAdvChemRecRecipe recipe) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
			case 0: //Bottom
				return new int[] {6,7,8,9,10,12};
			case 1: //Top
				return new int[] {1,2,3,4,5,11};
			default: //Disallow auto-insert module
				return null;
		}
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		if (side != 1)
			return false;
		return Arrays.asList(1,2,3,4,5,11).contains(slot);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		if (side != 0)
			return false;
		return Arrays.asList(6,7,8,9,10,12).contains(slot);
	}

}
