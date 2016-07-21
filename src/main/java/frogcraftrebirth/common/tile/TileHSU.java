package frogcraftrebirth.common.tile;

import frogcraftrebirth.common.lib.tile.TileFrogEStorage;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;

public class TileHSU extends TileFrogEStorage implements ITickable, IInventory {
	
	public ItemStack[] inv;
	
	public TileHSU() {
		super(100000000, 2048, EnumFacing.DOWN, false);
		this.inv = new ItemStack[2];
	}
	
	protected TileHSU(int maxEnergy, int output, EnumFacing emitTo, boolean allowTelep) {
		super(maxEnergy, output, emitTo, allowTelep);
		this.inv = new ItemStack[2];
	}

	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		
		if (!worldObj.isRemote && !loaded) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.loaded = true;
		}
		
		if (inv[1] != null && inv[1].getItem() instanceof IElectricItem) {
			this.storedE += ElectricItem.manager.discharge(inv[1], output, getSourceTier(), true, false, false);
			this.markDirty();
		}
		
		if (inv[0] != null && inv[0].getItem() instanceof IElectricItem) {
			ElectricItem.manager.charge(inv[0], this.getOutputEnergyUnitsPerTick(), getSourceTier(), false, false);
			this.markDirty();
		}

	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.storedE = tag.getInteger("charge");
		this.maxE = tag.getInteger("maxCharge");
		this.output = tag.getInteger("output");
		NBTTagList invTag = tag.getTagList("inventory", 10);
		for (int i=0;i<inv.length;i++) {
			NBTTagCompound itemTag = invTag.getCompoundTagAt(i);
			byte slot = itemTag.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(itemTag);
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("charge", storedE);
		tag.setInteger("maxCharge", maxE);
		tag.setInteger("output", output);
		NBTTagList invTag = new NBTTagList();
		for (int i=0;i<inv.length;i++) {
			NBTTagCompound s = new NBTTagCompound();
			s.setByte("Slot", (byte)i);
			if (inv[i] != null)
				inv[i].writeToNBT(s);
			invTag.appendTag(s);
		}
		tag.setTag("inventory", invTag);
		return super.writeToNBT(tag);
	}

	//IInventory start. Note: all parameter named as "i" means "inventory slot"
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		if (inv[i] == null) {
			return null;
		} else {
			ItemStack s;
			if (inv[i].stackSize <= num) {
				s = inv[i];
				inv[i] = null;
				this.markDirty();
				return s;
			} else {
				s = inv[i].splitStack(num);
				inv[i] = inv[i].stackSize <= 0 ? null : inv[i];
				this.markDirty();
				return s;
			}
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		if (inv[i] == null) {
			inv[i] = stack;
			this.markDirty();
		}
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public boolean hasCustomName() {
		return false;
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
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return false;
	}
	
	@Override
	public int getTier() {
		return 4;
	}

	// TODO implementation of followings
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		
	}

}
