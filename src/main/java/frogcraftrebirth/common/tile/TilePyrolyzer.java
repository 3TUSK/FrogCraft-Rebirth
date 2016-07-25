package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.PyrolyzerRecipe;
import frogcraftrebirth.common.lib.tile.TileFrogEnergySink;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TilePyrolyzer extends TileFrogEnergySink {

	private final int INPUT = 0, OUTPUT = 1, INPUT_F = 2, OUTPUT_F = 3;
	
	public final ItemStackHandler inv = new ItemStackHandler(4);
	protected FrogFluidTank tank = new FrogFluidTank(16000);

	public int process, processMax;
	public boolean working;
	
	public int charge;
	
	private PyrolyzerRecipe recipe;

	public TilePyrolyzer() {
		super(2, 10000);
		// 0 input 1 output 2 fluidContainer input 3 fluidContainer output
	}

	@Override
	public void update() {
		if (this.worldObj.isRemote)
			return;

		if (inv.getStackInSlot(INPUT) == null || this.charge <= 128) {
			this.working = false;
			this.process = 0;
			this.processMax = 0;
			return;
		}
		
		if (!working) {
			recipe = FrogAPI.managerPyrolyzer.<ItemStack>getRecipe(inv.getStackInSlot(INPUT));
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
		
		if (!inv.getStackInSlot(INPUT).isItemEqual(recipe.getInput()))
			return false;
		
		return inv.extractItem(INPUT, 1, true) != null;
	}
	
	private void pyrolyze() {
		inv.extractItem(INPUT, 1, false);
		inv.insertItem(OUTPUT, recipe.getOutput(), false);
		if (recipe.getOutputFluid() != null)
			this.tank.fill(recipe.getOutputFluid(), !this.worldObj.isRemote);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
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
		tag.setTag("inv", inv.serializeNBT());
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
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		switch (facing) {
		case UP:
		case DOWN:
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
		default:
			return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		switch (facing) {
		case UP:
		case DOWN:
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inv : super.getCapability(capability, facing);
		default:
			return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T)tank : super.getCapability(capability, facing);
		}
	}

}
