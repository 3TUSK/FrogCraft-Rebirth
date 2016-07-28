package frogcraftrebirth.common.tile;

import frogcraftrebirth.api.air.IAirPump;
import frogcraftrebirth.common.lib.config.ConfigMain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileAirPump extends TileFrog implements ITickable, IEnergySink, IAirPump {
	
	private static final int MAX_AIR = 1000;
	private static final int MAX_CHARGE = 10000;
	
	public int charge;
	private int airAmount, tick;
	private boolean isInENet;
	
	public TileAirPump() {

	}
	
	public void invalidate() {
		if (!worldObj.isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		if (!isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
		
		if (this.worldObj.isBlockIndirectlyGettingPowered(this.pos) != 0)
			return;
		
		if (this.charge < ConfigMain.airPumpPowerRate)
			return;
		
		if (airAmount >= MAX_AIR) {
			this.airAmount = MAX_AIR;
			return;
		}
		
		this.charge -= ConfigMain.airPumpPowerRate;
		this.tick++;
		if (tick == 4) {
			this.airAmount += ConfigMain.airPumpGenerateSpeed;
			tick = 0;
		}
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.charge = tag.getInteger("charge");
		this.airAmount = tag.getInteger("air");
		this.tick = tag.getInteger("tick");
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("charge", this.charge);
		tag.setInteger("air", this.airAmount);
		tag.setInteger("tick", this.tick);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		output.writeInt(charge);
		output.writeInt(airAmount);
		output.writeInt(tick);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		this.charge = input.readInt();
		this.airAmount = input.readInt();
		this.tick = input.readInt();
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing direction) {
		return direction != EnumFacing.UP;
	}

	@Override
	public double getDemandedEnergy() {
		return MAX_CHARGE - this.charge;
	}

	@Override
	public int getSinkTier() {
		return 1;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		this.charge += amount;
		if (this.charge >= MAX_CHARGE)
			this.charge = MAX_CHARGE;
		return 0;	
	}

	@Override
	public int airAmount() {
		return airAmount;
	}

	@Override
	public int extractAir(EnumFacing from, int amount, boolean simluated) {
		if (amount > this.airAmount) {
			int toReturn = this.airAmount;
			if (!simluated)
				this.airAmount = 0;
			return toReturn;
		} else {
			if (!simluated)
				this.airAmount -= amount;
			return amount;
		}
	}
	
	public void setAirAmount(int amount) {
		this.airAmount = amount;
	}
}
