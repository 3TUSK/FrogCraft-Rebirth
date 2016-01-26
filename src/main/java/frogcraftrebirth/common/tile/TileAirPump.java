package frogcraftrebirth.common.tile;

import static frogcraftrebirth.common.lib.config.ConfigMain.airPumpGenerateSpeed;
import static frogcraftrebirth.common.lib.config.ConfigMain.airPumpPowerRate;

import frogcraftrebirth.api.tile.IAirPump;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAirPump extends TileFrog implements IEnergySink, IAirPump /*, IFluidHandler*/ {
	
	private static final int MAX_AIR = 1000;
	
	public int charge, maxCharge;
	private int airAmount, tick;
	private boolean isInENet;
	
	public TileAirPump() {
		this.maxCharge = 10000;
	}
	
	public void invalidate() {
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}
	
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote) return;
		if (!isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
		
		if (this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
			return;
		
		if (this.charge <= 0 || this.charge < airPumpPowerRate)
			return;
		if (airAmount >= MAX_AIR) {
			this.airAmount = MAX_AIR;
			return;
		}
		
		this.charge-=airPumpPowerRate;
		this.tick++;
		if (tick == 4) {
			this.airAmount+=airPumpGenerateSpeed;
			tick = 0;
		}
		this.markDirty();
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
		this.charge += amount;
		if (this.charge >= maxCharge)
			this.charge = maxCharge;
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
	
	public void setAirAmount(int amount) {
		this.airAmount = amount;
	}

}
