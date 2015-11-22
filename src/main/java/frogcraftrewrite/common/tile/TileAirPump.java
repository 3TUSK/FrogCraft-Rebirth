package frogcraftrewrite.common.tile;

import static frogcraftrewrite.common.lib.config.ConfigMain.airPumpGenerateSpeed;
import static frogcraftrewrite.common.lib.config.ConfigMain.airPumpPowerRate;

import frogcraftrewrite.api.tile.IAirPump;
import frogcraftrewrite.common.lib.tile.TileFrog;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class TileAirPump extends TileFrog implements IEnergySink, IAirPump {
	
	public int charge, maxCharge;
	private int airAmount, maxAirAmount = 1000;
	private boolean isInENet;
	
	public TileAirPump() {
		this.charge = 0;
		this.airAmount = 0;
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
		
		if (airAmount >= maxAirAmount) {
			this.airAmount = maxAirAmount;
			return;
		}
		this.charge-=airPumpPowerRate;
		this.airAmount+=airPumpGenerateSpeed;
		this.markDirty();
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.charge = tag.getInteger("charge");
		this.airAmount = tag.getInteger("air");
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("charge", this.charge);
		tag.setInteger("air", this.airAmount);
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

}
