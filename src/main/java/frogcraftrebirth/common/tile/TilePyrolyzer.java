package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.impl.FrogFluidTank;
import frogcraftrebirth.api.recipes.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TilePyrolyzer extends TileFrogMachine implements IFluidHandler {

	protected FrogFluidTank tank = new FrogFluidTank(16000);

	public int process, processMax;
	public boolean working;
	
	private PyrolyzerRecipe recipe;

	public TilePyrolyzer() {
		super(4, "TileThermalCracker", 2, 10000);
		// 0 input 1 output 2 fluidContainer input 3 fluidContainer output
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.isRemote)
			return;
		
		if (inv[0] == null || this.charge <= 128) {
			this.working = false;
			this.process = 0;
			return;
		}
		
		if (!working) {
			recipe = FrogAPI.managerPyrolyzer.<ItemStack>getRecipe(this.inv[0]);
			if (canWork(recipe)) {
				this.process = 0;
				this.processMax = recipe.getTime();
				this.working = true;
				this.markDirty();
			}
		} else {
			this.charge -= 128;
			process++;
			if (process == processMax) {
				recipe = FrogAPI.managerPyrolyzer.<ItemStack> getRecipe(this.inv[0]);
				this.decrStackSize(0, recipe.getInput().stackSize);
				if (this.getStackInSlot(1) == null)
					this.setInventorySlotContents(1, recipe.getOutput());
				else {
					inv[1].stackSize += recipe.getOutput().stackSize;
				}
				if (recipe.getOutputFluid() != null)
					this.fill(ForgeDirection.UP, recipe.getOutputFluid(), true);
				process = 0;
				this.markDirty();
				working = false;
			}
		}

		this.sendTileUpdatePacket(this);
	}
	
	public boolean canWork(PyrolyzerRecipe recipe) {
		if (recipe == null)
			return false;
		
		if (!canFill(ForgeDirection.UP, recipe.getOutputFluid().getFluid()))
			return false;
		
		if (!inv[1].isItemEqual(recipe.getOutput()))
			return false;
		
		int a = recipe.getOutput().stackSize, max = recipe.getOutput().getMaxStackSize();	
		if (a + inv[1].stackSize > max)
			return false;
		
		return true;
	}
	
	public void pyrolyze() {
		
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
			return new int[] { 1 };
		case 1:
			return new int[] { 0 };
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
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { this.tank.getInfo() };
	}

}
