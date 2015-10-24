package frogcraftrewrite.common.tile;

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
	
	public int energy, maxEnergy, sinkTier;
	protected boolean isInENet;
	
	protected TileFrogMachine(int invSize, String invName, int sinkTier, int maxEnergy) {
		super(invSize, invName);
		this.sinkTier = sinkTier;
		this.maxEnergy = maxEnergy;
	}
	
	@Override
	public abstract int[] getAccessibleSlotsFromSide(int side);

	@Override
	public abstract boolean canInsertItem(int slot, ItemStack item, int side);
	
	@Override
	public abstract boolean canExtractItem(int slot, ItemStack item, int side);
	
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
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.energy = tag.getInteger("charge");
		this.maxEnergy = tag.getInteger("maxCharge");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("charge", this.energy);
		tag.setInteger("maxCharge", maxEnergy);
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getDemandedEnergy() {
		return maxEnergy - energy;
	}

	@Override
	public int getSinkTier() {
		return sinkTier;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		this.energy += amount;
		this.energy = energy > maxEnergy ? maxEnergy : energy;
		return 0;
	}

}
