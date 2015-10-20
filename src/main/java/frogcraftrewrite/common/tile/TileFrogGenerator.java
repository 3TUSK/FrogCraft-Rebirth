package frogcraftrewrite.common.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileFrogGenerator extends TileFrogInventory implements ISidedInventory, IEnergySource {

	public int energy, sourceTier, output;
	protected boolean isInENet;
	
	public TileFrogGenerator (int invSize, String name, int sourceTier, int output) {
		super(invSize, name);
		this.sourceTier = sourceTier;
		this.output = output;
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
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.energy = tag.getInteger("charge");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("charge", this.energy);
	}
	
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		return Math.min(energy, output);
	}

	@Override
	public void drawEnergy(double amount) {
		this.energy -= amount;
		this.energy = energy < 0 ? 0 : energy;
	}

	@Override
	public int getSourceTier() {
		return sourceTier;
	}

	@Override
	public abstract int[] getAccessibleSlotsFromSide(int side);

	@Override
	public abstract boolean canInsertItem(int slot, ItemStack item, int side);

	@Override
	public abstract boolean canExtractItem(int slot, ItemStack item, int side);

}
