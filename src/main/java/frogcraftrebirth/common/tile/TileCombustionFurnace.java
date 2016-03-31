package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.registry.GameRegistry;
import frogcraftrebirth.api.FrogFuelHandler;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileFrogGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileCombustionFurnace extends TileFrogGenerator implements IFluidHandler {

	public boolean working;
	protected FrogFluidTank tank = new FrogFluidTank(8000);
	public int time, timeMax;
	private static final int CHARGE_MAX = 5000;

	public TileCombustionFurnace() {
		super(4, "TileEntityCombustionFurnace", 1, 32);
		// 0 input 1 output 2 fluid container input 3 fluid container output
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		if (this.tank.getFluidAmount() >= this.tank.getCapacity()) {
			this.working = false;
			return;
		}
		// Use vanilla furnace standard.
		if (this.inv[0] != null && GameRegistry.getFuelValue(this.inv[0]) > 0) {
			this.working = true;
			if (this.getStackInSlot(1) == null)
				inv[1] = FrogFuelHandler.FUEL_REG.getItemByproduct(inv[0]);
			else
				++inv[1].stackSize;
			tank.fill(FrogFuelHandler.FUEL_REG.getFluidByproduct(inv[0]), !worldObj.isRemote);
			this.decrStackSize(0, 1);
			this.timeMax = FrogFuelHandler.FUEL_REG.getBurnTime(this.inv[0]);
			this.time = FrogFuelHandler.FUEL_REG.getBurnTime(this.inv[0]);
		} else {
			this.timeMax = 0;
			this.working = false;
		}

		if (this.working) {
			this.charge += 10;
			this.time--;
		}

		if (this.time == 0) {
			this.timeMax = 0;
			this.working = false;
		}
		
		if (this.charge >= CHARGE_MAX)
			this.charge = CHARGE_MAX;

		markDirty();
		
		this.sendTileUpdatePacket(this);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.time = tag.getInteger("time");
		this.timeMax = tag.getInteger("timeMax");
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		tank.readPacketData(input);
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		tank.writePacketData(output);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		this.tank.writeToNBT(tag);
		tag.setBoolean("working", this.working);
		tag.setInteger("time", this.time);
		tag.setInteger("timeMax", this.timeMax);
	}

	@Override
	public double getOfferedEnergy() {
		return working ? super.getOfferedEnergy() : 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 1 ? new int[] { 0 } : null; // fuel slot
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return side == 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return side == 0 && slot == 2;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection direction) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

	@Override
	public int fill(ForgeDirection direction, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return this.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection direction, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

}
