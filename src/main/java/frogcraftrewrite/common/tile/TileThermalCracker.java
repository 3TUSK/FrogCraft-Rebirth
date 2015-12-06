package frogcraftrewrite.common.tile;

import static frogcraftrewrite.common.network.NetworkHandler.FROG_NETWORK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrewrite.api.FrogAPI;
import frogcraftrewrite.api.impl.FrogFluidTank;
import frogcraftrewrite.api.recipes.ThermalCrackerRecipe;
import frogcraftrewrite.common.lib.tile.TileFrogMachine;
import frogcraftrewrite.common.network.PacketFrog00TileUpdate;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileThermalCracker extends TileFrogMachine implements IFluidHandler {
	
	private FrogFluidTank tank = new FrogFluidTank(16000);

	public int process, processMax;
	public boolean working;
	
	public TileThermalCracker() {
		super(4, "TileThermalCracker", 2, 10000);
		//0 input 1 output 2 fluidContainer input 3 fluidContainer output
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.isRemote) return;
		if (inv[0] == null) {
			this.working = false;
			this.process = 0;
			return;
		}
		ThermalCrackerRecipe recipe;
		if (!working) {
			recipe = FrogAPI.managerTC.<ItemStack>getRecipe(this.inv[0]);
			if (recipe != null)
					if (inv[1] == null || recipe.getOutput().isItemEqual(inv[1]) && recipe.getOutputFluid() == null || canFill(ForgeDirection.UNKNOWN, recipe.getOutputFluid().getFluid())) {
						this.process = 0;
						this.processMax = recipe.getTime();
						this.working = true;
					}
		} else {
			if (this.charge <= 256)
				return;
			this.charge -= 256;
			process++;
			if (process == processMax) {
				recipe = FrogAPI.managerTC.<ItemStack>getRecipe(this.inv[0]);
				this.decrStackSize(0, recipe.getInput().stackSize);
				if (this.getStackInSlot(1) == null)
					this.setInventorySlotContents(1, recipe.getOutput());
				else {
					inv[1].stackSize += recipe.getOutput().stackSize;
				}
				if (recipe.getOutputFluid() != null)
					this.fill(ForgeDirection.UNKNOWN, recipe.getOutputFluid(), true);
				process = 0;
				working = false;
			}
		}
		
		FROG_NETWORK.sendToAllAround(new PacketFrog00TileUpdate(this), this.worldObj.provider.terrainType.getWorldTypeID(), xCoord, yCoord, zCoord, 2);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.process = tag.getInteger("process");
		this.processMax = tag.getInteger("processMax");
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
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (side) {
			case 0: 
				return new int[] {1};
			case 1: 
				return new int[] {0};
			default: 
				return null;
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return side == 0 && slot == 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return side == 1 && slot == 0;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
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
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (tank.getFluidAmount() >= tank.getCapacity())
			return false;
		switch (from) {
			case UNKNOWN:
				if (fluid == null)
					return true;
				else return false;
			default:
				return true;
		}
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {this.tank.getInfo()};
	}
	
	public FluidTankInfo getTankInfo() {
		return this.getTankInfo(ForgeDirection.UNKNOWN)[0];
	}

}
