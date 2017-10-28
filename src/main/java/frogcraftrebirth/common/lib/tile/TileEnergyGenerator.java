package frogcraftrebirth.common.lib.tile;

import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public abstract class TileEnergyGenerator extends TileEnergy implements ITickable, IEnergySource {

	public int charge;
	private final int sourceTier;
	private final int output;
	protected boolean isInENet;
	
	protected TileEnergyGenerator(int sourceTier, int output) {
		this.sourceTier = sourceTier;
		this.output = output;
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
		if (charge <= amount)
			charge = 0;
		else
			this.charge -= (int)amount;
	}

	@Override
	public int getSourceTier() {
		return sourceTier;
	}

}
