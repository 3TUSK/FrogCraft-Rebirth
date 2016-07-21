package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileFrogInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileFluidOutputHatch extends TileFrogInventory implements ITickable, ICondenseTowerOutputHatch {

	protected FrogFluidTank tank = new FrogFluidTank(8000);

	public TileFluidOutputHatch() {
		super(2, "TileEntityFluidOutput");
		// 0==input slot, 1==output slot
	}

	private int tick = 0;

	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		tick++;
		if (tank.getFluid() == null)
			return;
		if (inv[0] == null)
			return;

		if (inv[0].hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null) && tick >= 20) {
			if (inv[1] == null) {
				inv[1].getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).fill(tank.getFluid(), !worldObj.isRemote);
				this.tank.drain(1000, true);
			}
			if (inv[1].hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				//TODO more check
				inv[1].stackSize += 1;
				this.tank.drain(1000, true);
			}
			tick = 0;
		}
		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		tank.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tank.writeToNBT(tag);
		return super.writeToNBT(tag);
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
	public boolean canInject(FluidStack stack) {
		return stack != null ? this.tank.canFill(stack.getFluid()) : false;
		//How can one fill within non-existed fluid?
	}

	@Override
	public void inject(FluidStack stack, boolean simluated) {
		this.tank.fill(stack, simluated);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		else return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T)tank;
		else return super.getCapability(capability, facing);
	}

}
