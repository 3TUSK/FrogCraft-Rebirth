package frogcraftrebirth.common.lib.tile;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;

public abstract class TileEnergyGenerator extends TileFrog implements ITickable, IEnergySource {

	public int charge, sourceTier, output;
	protected boolean isInENet;
	
	public TileEnergyGenerator (int sourceTier, int output) {
		this.sourceTier = sourceTier;
		this.output = output;
	}
	
	@Override
	public void invalidate() {
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}
	
	@Override
	public void update() {
		if (!worldObj.isRemote && !isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.charge = tag.getInteger("charge");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("charge", this.charge);
		return super.writeToNBT(tag);
	}
	
	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing direction) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		return Math.min(charge, output);
	}

	@Override
	public void drawEnergy(double amount) {
		this.charge -= amount;
	}

	@Override
	public int getSourceTier() {
		return sourceTier;
	}

}
