package frogcraftrewrite.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public abstract class TileFrogInventory extends TileEntityFrog implements IInventory {

	protected ItemStack[] inv;
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public abstract ItemStack decrStackSize(int slot, int decrNum);
	
	@Override
	public abstract ItemStack getStackInSlotOnClosing(int slot);

	@Override
	public abstract void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_);
	

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return getInventoryName() != null;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public short getFacing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFacing(short facing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

}
