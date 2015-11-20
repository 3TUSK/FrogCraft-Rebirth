package frogcraftrewrite.common.lib.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileFrogMachine extends TileFrogInventory implements ISidedInventory, IEnergySink {
	
	public int charge, maxCharge, sinkTier;
	protected boolean isInENet;
	
	protected TileFrogMachine(int invSize, String invName, int sinkTier, int maxEnergy) {
		super(invSize, invName);
		this.sinkTier = sinkTier;
		this.maxCharge = maxEnergy;
	}
	
	@Override
	public abstract int[] getAccessibleSlotsFromSide(int side);

	@Override
	public abstract boolean canInsertItem(int slot, ItemStack item, int side);
	
	@Override
	public abstract boolean canExtractItem(int slot, ItemStack item, int side);
	
	@Override
	public void invalidate() {
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();	
		if (!worldObj.isRemote && !isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.charge = tag.getInteger("charge");
		this.maxCharge = tag.getInteger("maxCharge");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("charge", this.charge);
		tag.setInteger("maxCharge", maxCharge);
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getDemandedEnergy() {
		return maxCharge - charge;
	}

	@Override
	public int getSinkTier() {
		return sinkTier;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		this.charge += amount;
		this.charge = charge > maxCharge ? maxCharge : charge;
		return 0;
	}

}
