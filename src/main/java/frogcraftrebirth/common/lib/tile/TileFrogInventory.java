package frogcraftrebirth.common.lib.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
@Deprecated //Will switch to ItemHandler
public abstract class TileFrogInventory extends TileFrog implements IInventory {

	protected ItemStack[] inv;
	protected String name;
	
	protected TileFrogInventory(final int invSize) {
		this(invSize, "DummyInvName");
	}
	
	protected TileFrogInventory(final int invSize, final String invName) {
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
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
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
		return super.writeToNBT(tag);
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
	public ItemStack removeStackFromSlot(int index) {
		ItemStack copy = inv[index].copy();
		inv[index] = null;
		return copy;
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean hasCustomName() {
		return name != null && !name.equals("");
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
	
	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < inv.length; ++i) {
			inv[i] = null;
		}
	}

}
