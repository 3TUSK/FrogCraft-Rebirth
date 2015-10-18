package frogcraftrewrite.common.tile;

import java.util.Arrays;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.recipes.AdvChemReactorRecipe;
import frogcraftrewrite.common.lib.util.ItemUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileAdvChemReactor extends TileFrogMachine {

	public int process, processMax;
	boolean working, changed, needUpdate;
	
	public TileAdvChemReactor() {
		super(2, 100000);
		//0 for module, 1-5 for input, 6-10 for output, 11 for cell input and 12 for cell output
		this.inv = new ItemStack[13];
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) return;
		//Seriously, fully oreDict support seems very complex due to the registry mechanism
		Object[] input = ItemUtil.asFrogInputsArray(Arrays.copyOfRange(inv, 1, 5));
		//check inv
		AdvChemReactorRecipe recipe = (AdvChemReactorRecipe)FrogAPI.managerACR.getRecipe(input);
		working = recipe == null ? false : true;
		//if available, consume material and start react
		for (int i=1;i<=5;i++) {
			//--inv[i].stackSize; todo: consume certain number of items
		}
		//4.process++ until finish
		if (working) {
			this.energy -= recipe.getEnergyPerTick();
			++process;
		}
		if (process == processMax) {
			//5.clean up
			this.markDirty();
		}
	}
	
	@Override
	public short getFacing() {
		return 2;
	}

	@Override
	public void setFacing(short facing) {
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(this.blockType, 1, this.blockMetadata);
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
	public String getInventoryName() {
		return "";
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		// TODO Auto-generated method stub
		return false;
	}

}
