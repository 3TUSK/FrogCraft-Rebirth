package frogcraftrewrite.common.lib.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class TileFrogInventory extends TileFrog implements IInventory {

	protected ItemStack[] inv;
	protected String name;
	
	protected TileFrogInventory(int invSize, String invName) {
		super();
		this.inv = new ItemStack[invSize];
		this.name = invName;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList invList = tag.getTagList("inventory", 10);
		for (int n = 0; n < invList.tagCount(); n++) {
			NBTTagCompound anItem = invList.getCompoundTagAt(n);
			byte slot = anItem.getByte("slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(anItem);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagList invList = new NBTTagList();
		for (int n = 0; n < inv.length; n++) {
			ItemStack stack = inv[n];
			if (stack != null) {
				NBTTagCompound tagStack = new NBTTagCompound();
				tagStack.setByte("slot", (byte) n);
				stack.writeToNBT(tagStack);
				invList.appendTag(tagStack);
			}
		}
		tag.setTag("inventory", invList);
	}
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int decrNum) {
		if (this.inv[slot] != null) {
            ItemStack itemstack;
            if (this.inv[slot].stackSize <= decrNum) {
                itemstack = this.inv[slot];
                this.inv[slot] = null;
                return itemstack;
            } else {
                itemstack = this.inv[slot].splitStack(decrNum);
                if (this.inv[slot].stackSize == 0) {
                    this.inv[slot] = null;
                }
                return itemstack;
            }
        } else {
            return null;
        }
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inv[slot] != null) {
			ItemStack stack = inv[slot];
			inv[slot] = null;
			return stack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
		this.markDirty();
	}
	
	@Override
	public String getInventoryName() {
		return this.name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false; //we will have it later, we need to figure out how it work
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

}
