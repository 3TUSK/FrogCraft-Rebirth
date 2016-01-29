package frogcraftrebirth.common.tile;

import java.util.Arrays;
import java.util.List;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.recipes.AdvChemReactorRecipe;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

public class TileAdvChemReactor extends TileFrogMachine {
	public int process, processMax;
	boolean working, changed = false;
	
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
		if (worldObj.isRemote) return;
		AdvChemReactorRecipe recipe = (AdvChemReactorRecipe)FrogAPI.managerACR.<ItemStack[]>getRecipe(Arrays.copyOfRange(inv, 1, 5));
		List<ItemStack> recipeInput;
		recipeInput = recipe != null ? recipe.getInput() : null;
		if (!working && recipe != null && recipeInput != null) {
			for (ItemStack s : recipeInput) {
				for (int i=1;i<6;i++) {
					if (OreDictionary.itemMatches(s, inv[i], false) && inv[i].stackSize >= s.stackSize) {
						ItemStack temp = inv[i].copy();
						temp.stackSize -= s.stackSize;
						this.setInventorySlotContents(i, temp);
						break;
					}
				}
			}
			working = recipe == null ? false : true;
		} 
		else 
			return;
		List<ItemStack> recipeOutput = recipe.getOutput();
		int freeOutSlot = 0;
		for (int i=6;i<11;i++) {
			if (this.getStackInSlot(i) == null)
				++freeOutSlot;
		}
		if (working && process == 0 && freeOutSlot < recipeOutput.size()) {
			working = false; //Not enough output slots, stop working.
		} else {
			this.processMax = recipe.getTime();
		}
		
		if (working && charge >= recipe.getEnergyPerTick()) {
			this.charge -= recipe.getEnergyPerTick();
			++process;
		}
		
		if (process == processMax) {
			int outputLength = recipeOutput.size();
			boolean emptyOutput = true;
			for (int i=6;i<11;i++) {
				if (this.getStackInSlot(i) != null) {
					emptyOutput = false;
					break;
				}
			}
			if (emptyOutput) {
				for (int i=0;i<outputLength;i++) {
					this.setInventorySlotContents(i+5, recipeOutput.get(i));
				}
			} else {
				for (ItemStack s : recipeOutput) {
					for (int i=6;i<11;i++) {
						if (OreDictionary.itemMatches(this.getStackInSlot(i), s, false) && this.inv[i].stackSize >= s.stackSize) {
							ItemStack temp = this.getStackInSlot(i);
							temp.stackSize -= s.stackSize;
							this.setInventorySlotContents(i, temp);
							break;
						}
					}
				}
			}
			this.changed = true;
		}
		if (changed) {
			this.markDirty();
			changed = false;
		}
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
