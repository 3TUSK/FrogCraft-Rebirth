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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TilePyrolyzer extends TileFrogMachine {

	protected FrogFluidTank tank = new FrogFluidTank(16000);

	public int process, processMax;
	public boolean working;
	
	private PyrolyzerRecipe recipe;

	public TilePyrolyzer() {
		super(4, "TileThermalCracker", 2, 10000);
		// 0 input 1 output 2 fluidContainer input 3 fluidContainer output
	}

	@Override
	public void update() {
		if (this.worldObj.isRemote)
			return;
		super.update();

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
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setBoolean("working", this.working);
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
		if (recipe != null) {
			NBTTagCompound item = recipe.getInput().writeToNBT(new NBTTagCompound());
			tag.setTag("recipeInput", item);
		}
		return super.writeToNBT(tag);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		switch (side) {
		case UP:
			return new int[] { 0 };
		case DOWN:
			return new int[] { 1 };
		default:
			return null;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack item, EnumFacing direction) {
		return direction == EnumFacing.UP && index == 0;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack item, EnumFacing direction) {
		return direction == EnumFacing.DOWN && index == 1;
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
