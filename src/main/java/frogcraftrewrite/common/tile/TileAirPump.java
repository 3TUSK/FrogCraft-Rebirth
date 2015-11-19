package frogcraftrewrite.common.tile;

import static frogcraftrewrite.common.lib.config.ConfigMain.airPumpPowerRate;
import static frogcraftrewrite.common.lib.config.ConfigMain.airPumpGenerateSpeed;

import frogcraftrewrite.api.tile.IAirPump;
import frogcraftrewrite.common.lib.tile.TileFrog;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAirPump extends TileFrog implements IEnergySink, IAirPump {
	
	public int charge, maxCharge;
	private int airAmount, maxAirAmount = 1000, tick;
	
	public void updateEntity() {
		super.updateEntity();
		if (airAmount >= maxAirAmount) {
			this.airAmount = maxAirAmount;
			return;
		}

		++tick;
		if (tick == 20) {
			this.airAmount+=airPumpGenerateSpeed;
			this.charge-=airPumpPowerRate;
			tick = 0;
			this.markDirty();
		}
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.charge = tag.getInteger("charge");
		this.airAmount = tag.getInteger("air");
		this.tick = tag.getInteger("tick");
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("charge", this.charge);
		tag.setInteger("air", this.airAmount);
		tag.setInteger("tick", this.tick);
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
	public int airAmount() {
		return airAmount;
	}

	@Override
	public void extractAir(ForgeDirection from, int amount, boolean simluated) {
		if (simluated) return;
		this.airAmount -= amount;
		if (airAmount < 0) this.airAmount = 0;
	}

}
