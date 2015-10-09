package frogcraftrewrite.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;

public class TileEntityChemicalReactor extends TileEntityFrog implements ISidedInventory, IEnergySink {

	public ItemStack[] inv;
	public boolean isInENet;
	public int energy, maxEnergy;
	public int process;
	
	public TileEntityChemicalReactor(int maxEnergy) {
		inv = new ItemStack[12];
		this.maxEnergy = maxEnergy;
	}
	
	@Override
	public void invalidate() {
		if (isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (!isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
		
		//TODO WTF!
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
		ItemStack atSlot = inv[slot];
		atSlot.stackSize -= decrNum;
		if (atSlot.stackSize <= 0)
			inv[slot] = null;
		return atSlot;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (inv[slot] == null)
			inv[slot] = stack;
		int preSize = inv[slot].stackSize, addition = stack.stackSize;
		if (preSize + addition <= 64)
			inv[slot].stackSize = preSize + addition;
			else
				for (int n = 0;n<inv.length;n++) {
					if (inv[n] == null) {
						int newNum = preSize + addition - 64;
						ItemStack copy = stack.copy();
						copy.stackSize = newNum;
						inv[n] = copy;
						break;
					}
				}
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return (getInventoryName() != null && getInventoryName() != "");
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
		if (slot == 0){
			//Item anItem = stack.getItem(); TODO what's wrong here?
			//return anItem instanceof ICatalystItem;
		}
		if (slot == 11)
			return stack.getItem() == ic2.api.item.IC2Items.getItem("cell").getItem();
		return (slot >= 1 && slot <= 5);
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getDemandedEnergy() {
		return 128D;
	}

	@Override
	public int getSinkTier() {
		return 2;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		if (energy == maxEnergy)
			return amount;
		
		energy += (int)amount;
		if (energy > maxEnergy)
			energy = maxEnergy;
		return 0D;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// TODO Auto-generated method stub
		return null;
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

}
