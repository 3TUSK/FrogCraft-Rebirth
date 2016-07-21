package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.tile.TileFrogMachine;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
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
		if (this.worldObj.isRemote)
			return;
		super.updateEntity();

		if (inv[0] == null || this.charge <= 128) {
			this.working = false;
			this.process = 0;
			this.processMax = 0;
			return;
		}
		
		if (!working) {
			recipe = FrogAPI.managerPyrolyzer.<ItemStack>getRecipe(this.inv[0]);
			if (canWork(recipe)) {
				this.process = 0;
				this.processMax = recipe.getTime();
				this.working = true;
				this.markDirty();
			} else {
				return;
			}
		}
		
		this.charge -= 128;
		process++;
		
		if (process == processMax) {
			pyrolyze();
			process = 0;
			this.processMax = 0;
			this.markDirty();
			working = false;
			this.sendTileUpdatePacket(this);
		}
	}
	
	private boolean canWork(PyrolyzerRecipe recipe) {
		if (recipe == null)
			return false;
		
		if (tank.getFluid() != null) {
			if (tank.getFluid().getFluid() != recipe.getOutputFluid().getFluid())
				return false;
			else if (tank.getFluidAmount() + recipe.getOutputFluid().amount >= tank.getCapacity())
				return false;
		}
		
		if (!inv[0].isItemEqual(recipe.getInput()))
			return false;
		
		return inv[1] == null || inv[1].isItemEqual(recipe.getOutput()) && inv[1].stackSize + recipe.getOutput().stackSize <= inv[1].getMaxStackSize();
	}
	
	private void pyrolyze() {
		this.decrStackSize(0, recipe.getInput().stackSize);
		if (this.getStackInSlot(1) == null)
			this.setInventorySlotContents(1, recipe.getOutput());
		else {
			ItemStack newStack = inv[1].copy();
			int newSize = newStack.stackSize + recipe.getOutput().stackSize;
			newStack.stackSize = newSize;
			this.setInventorySlotContents(1, newStack);
		}
		if (recipe.getOutputFluid() != null)
			this.tank.fill(recipe.getOutputFluid(), !this.worldObj.isRemote);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.process = tag.getInteger("process");
		this.processMax = tag.getInteger("processMax");
		this.recipe = FrogAPI.managerPyrolyzer.<ItemStack>getRecipe(ItemStack.loadItemStackFromNBT(tag.getCompoundTag("recipeInput")));
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
		if (recipe != null) {
			NBTTagCompound item = recipe.getInput().writeToNBT(new NBTTagCompound());
			tag.setTag("recipeInput", item);
		}
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
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return this.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[] { this.tank.getInfo() };
	}

}
