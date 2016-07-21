package frogcraftrebirth.common.tile;

import java.util.Arrays;
import java.util.Collection;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.tile.IAdvChemReactor;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileAdvChemReactor extends TileFrogMachine implements IAdvChemReactor {
	public int process, processMax;
	private boolean working;
	private boolean changed = false;
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
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("working", this.working);
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote) 
			return;
		super.update();
		
		recipe = (IAdvChemRecRecipe)FrogAPI.managerACR.<ItemStack>getRecipe(Arrays.copyOfRange(inv, 1, 5));
		
		if (checkIngredient(recipe)) {
			this.consumeIngredient(recipe.getOutputs());
			this.working = true;
			this.changed = true;
		}
		
		if (working && charge >= recipe.getEnergyRate()) {
			this.charge -= recipe.getEnergyRate();
			++process;
		} else
			return;
		
		if (process == processMax) {
			this.produce();
			this.changed = true;
			this.working = false;
		}
		
		if (changed) {
			this.markDirty();
			this.sendTileUpdatePacket(this);
			changed = false;
		}
	}
	
	@Override
	public double modifyReactionRate(ItemStack... catalyst) {
		return 1D;
	}
	
	@Override
	public boolean checkIngredient(IAdvChemRecRecipe recipe) {
		if (recipe == null)
			return false;
		
		if (inv[1] == null && inv[2] == null && inv[3] == null && inv[4] == null && inv[5] == null) {
			return false;
		}
		
		for (OreStack ore : recipe.getInputs()) {
			boolean checkPass = false;
			for (int i = 1; i <= 5 ;i++) {
				if (ore.consumable(inv[i])) {
					checkPass = true;
					break;
				}
			}
			if (checkPass)
				continue;
			else return false; //means failed on checking
		}
		
		return false;
	}
	
	private void consumeIngredient(Collection<OreStack> toBeConsumed) {
		for (OreStack ore : toBeConsumed) {
			for (int i = 1; i <= 5 ;i++) {
				if (ore.consumable(inv[i])) {
					ore.consume(inv[i]);
					break;
				}
			}
		}
	}
	
	public void produce() {
		for (OreStack ore : recipe.getOutputs()) {
			boolean match = false;
			for (int i = 6; i <= 10; i++) {
				if (ore.stackable(inv[i])) {
					ore.stack(inv[i]);
					match = true;
					break;
				}
			}
			
			if (!match) {
				for (int i = 6; i <= 10; i++) {
					if (inv[i] == null) {
						ore.stack(inv[i]);
						break;
					}
				}
			}
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		switch (side) {
		case DOWN: //Bottom
			return new int[] {6,7,8,9,10,12};
		case UP: //Top
			return new int[] {1,2,3,4,5,11};
		default: //Disallow auto-insert module
			return null;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack item, EnumFacing direction) {
		if (direction != EnumFacing.UP)
			return false;
		if (index == 11 || (index <=5 && index >=1)) 
			if (inv[index].isItemEqual(item) && inv[index].stackSize + item.stackSize <= inv[index].getMaxStackSize()) 
				return true;
		
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack item, EnumFacing direction) {
		if (direction != EnumFacing.DOWN)
			return false;
		if (index == 12 || (index <=10 && index >=6))
			if (inv[index].isItemEqual(item) && inv[index].stackSize >= item.stackSize) 
				return true;
		
		return false;
	}

}
