package frogcraftrebirth.common.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.OreStack;
import frogcraftrebirth.api.item.ICatalystModuleItem;
import frogcraftrebirth.api.recipes.IAdvChemRecRecipe;
import frogcraftrebirth.api.tile.IAdvChemReactor;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

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
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("working", this.working);
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
	}
	
	@Override
	public void updateEntity() {
		if (worldObj.isRemote) 
			return;
		super.updateEntity();
		
		recipe = (IAdvChemRecRecipe)FrogAPI.managerACR.<ItemStack[]>getRecipe(Arrays.copyOfRange(inv, 1, 5));
		
		//Map<String, Integer> recipeInput = recipe.getInputs();
		
		boolean hasValidRecipe = checkIngredient(recipe);
		if (hasValidRecipe) {
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
			Collection<OreStack> recipeOutput = recipe.getOutputs();
			ArrayList<ItemStack> product = new ArrayList<ItemStack>();
			//Overhaul required
			for (OreStack ore : recipeOutput) {
				ItemStack stack = OreDictionary.getOres(ore.getEntry()).get(0);
				stack.stackSize = stack.stackSize + ore.getAmount();
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
	
	public double modifyReactionRate(ICatalystModuleItem... catalyst) {
		return 0D;
	}
	
	public void produce() {
		
	}
	
	public boolean checkIngredient(IAdvChemRecRecipe recipe) {
		if (recipe == null)
			return false;
		
		if (inv[1] == null && inv[2] == null && inv[3] == null && inv[4] == null && inv[5] == null) {
			return false;
		}
		
		ItemStack[] inputs = Arrays.copyOfRange(inv, 1, 5);
		
		for (OreStack ore : recipe.getInputs()) {
			boolean checkPass = false;
			for (ItemStack item : inputs) {
				if (ore.consumable(item)) {
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
		ItemStack[] inputs = Arrays.copyOfRange(inv, 1, 5);
		for (OreStack ore : toBeConsumed) {
			for (ItemStack item : inputs) {
				if (ore.consumable(item)) {
					ore.consume(item);
					break;
				}
			}
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
