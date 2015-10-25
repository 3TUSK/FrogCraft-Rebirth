package frogcraftrewrite.common.tile;

import frogcraftrewrite.api.tile.IAirPump;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAirPump extends TileFrog implements IEnergySink, IAirPump {
	
	public double charge, maxCharge;
	private int airAmount, maxAirAmount = 1000;
	
	public void updateEntity() {
		super.updateEntity();
		if (airAmount >= maxAirAmount) {
			this.airAmount = maxAirAmount;
			return;
		}
		//speed should be configurable
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.charge = tag.getDouble("charge");
		this.airAmount = tag.getInteger("air");
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("charge", this.charge);
		tag.setInteger("air", this.airAmount);
	}

	@Override
	public void setFacing(short facing) {
		
	}

	@Override
	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return direction != ForgeDirection.UP;
	}

	@Override
	public double getDemandedEnergy() {
		return this.maxCharge - this.charge;
	}

	@Override
	public int getSinkTier() {
		return 2;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		if (directionFrom == ForgeDirection.UP)
			return amount;
		this.charge += amount;
		this.charge = charge > maxCharge ? maxCharge : charge;
		return 0;	
	}

	@Override
	public int availableAmount() {
		return airAmount;
	}

	@Override
	public void extract(ForgeDirection from, int amount, boolean simluated) {
		if (simluated) return;
		this.airAmount -= amount;
		if (airAmount < 0) this.airAmount = 0;
	}

}
