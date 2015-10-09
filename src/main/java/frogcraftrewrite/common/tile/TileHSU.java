package frogcraftrewrite.common.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileHSU extends TileAbstractEStorage implements IInventory {
	
	public ItemStack[] inv;
	
	public TileHSU() {
		super(100000000, 2048, ForgeDirection.DOWN, false);
		this.inv = new ItemStack[2];
	}
	
	protected TileHSU(int maxEnergy, int output, ForgeDirection emitTo, boolean allowTelep) {
		super(maxEnergy, output, emitTo, allowTelep);
		this.inv = new ItemStack[2];
	}
	
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("charge", storedE);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.storedE = pkt.func_148857_g().getInteger("charge");
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		
		if (!worldObj.isRemote && !loaded) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.loaded = true;
		}
		
		this.markDirty();
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
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
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
	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		if (inv[i] == null) {
			inv[i] = stack;
			this.markDirty();
		}
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
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
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return false;
	}
	
	@Override
	public int getTier() {
		return 4;
	}

}
