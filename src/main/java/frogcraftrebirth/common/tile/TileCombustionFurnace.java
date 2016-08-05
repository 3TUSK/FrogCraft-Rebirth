package frogcraftrebirth.common.tile;

import static frogcraftrebirth.api.FrogAPI.FUEL_REG;
import static net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileEnergyGenerator;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileCombustionFurnace extends TileEnergyGenerator implements IHasWork {

	private static final int CHARGE_MAX = 5000;
	/** Index: 0 input; 1 output; 2 fluid container input; 3 fluid container output.*/
	public final ItemStackHandler input = new ItemStackHandler();
	public final ItemStackHandler output = new ItemStackHandler();
	public final ItemStackHandler fluidIO = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	
	public boolean working = false;
	public int time = 0, timeMax = 0;
	private ItemStack burning;

	public TileCombustionFurnace() {
		super(1, 16);
	}
	
	public boolean isWorking() {
		return working;
	}

	@Override
	public void update() {
		if (worldObj.isRemote) {
			worldObj.markBlockRangeForRenderUpdate(getPos(), getPos());
			return;
		}
		super.update();
		
		//Don't stuck the inventory
		if (output.getStackInSlot(0) != null && output.getStackInSlot(0).stackSize >= output.getStackInSlot(0).getMaxStackSize()) {
			this.sendTileUpdatePacket(this);
			this.markDirty();
			return;
		}
		
		if (working) {
			this.charge += 10;
			this.time--;
		} else if (input.extractItem(0, 1, true) != null && getItemBurnTime(input.getStackInSlot(0)) > 0) {	
			this.working = true;
			this.burning = input.extractItem(0, 1, false);
			this.timeMax = getItemBurnTime(burning) / 4;
			this.time = getItemBurnTime(burning) / 4;		
		}
		//Overflowed power will be voided 
		if (this.charge > CHARGE_MAX)
			this.charge = CHARGE_MAX;

		if (this.time <= 0) {
			this.timeMax = 0;
			if (working && burning != null)
				bonus(burning);
			burning = null;
			this.working = false;
		}
		
		if (tank.getFluidAmount() != 0 && fluidIO.getStackInSlot(0) != null) {
			if (fluidIO.getStackInSlot(0).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				ItemStack result = FluidUtil.tryFillContainer(fluidIO.extractItem(0, 1, true), tank, 1000, null, true);
				if (result != null && result.stackSize > 0) {
					fluidIO.extractItem(0, 1, false);
					ItemStack remainder = fluidIO.insertItem(1, result, false);
					if (remainder != null && remainder.stackSize > 0)
						ItemUtil.dropItemStackAsEntityInsanely(worldObj, getPos(), remainder);
				}
			}
		}
		
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}
	
	private void bonus(ItemStack input) {
		if (input == null)
			return;
		
		int[] oreIDs = OreDictionary.getOreIDs(input);
		if (oreIDs.length != 0) { //Why is 0? According to my experience, having more than one ore dict entry is very very rare.
			String oreName = OreDictionary.getOreName(oreIDs[0]);
			if (!oreName.equals("Unknown")) {
				//Feature: if there is no space for byproduct, they just go disappear
				output.insertItem(0, FUEL_REG.getItemByproduct(oreName), false);
				tank.fill(FUEL_REG.getFluidByproduct(oreName), true);
			}
		} else {
			output.insertItem(0, FUEL_REG.getItemByproduct(input), false);
			tank.fill(FUEL_REG.getFluidByproduct(input), true);
		}	
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.working = tag.getBoolean("working");
		this.time = tag.getInteger("time");
		this.timeMax = tag.getInteger("timeMax");
		this.input.deserializeNBT(tag.getCompoundTag("input"));
		this.output.deserializeNBT(tag.getCompoundTag("output"));
		this.fluidIO.deserializeNBT(tag.getCompoundTag("fluidIO"));
		this.burning = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("burning"));
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		tank.readPacketData(input);
		this.time = input.readInt();
		this.timeMax = input.readInt();
		this.working = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		tank.writePacketData(output);
		output.writeInt(time);
		output.writeInt(timeMax);
		output.writeBoolean(working);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setBoolean("working", this.working);
		tag.setInteger("time", this.time);
		tag.setInteger("timeMax", this.timeMax);
		tag.setTag("input", this.input.serializeNBT());
		tag.setTag("output", this.output.serializeNBT());
		tag.setTag("fluidIO", this.fluidIO.serializeNBT());
		tag.setTag("burning", this.burning != null ? burning.writeToNBT(new NBTTagCompound()) : new NBTTagCompound());
		return super.writeToNBT(tag);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return true;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			switch (facing) {
				case DOWN:
				case UP: 
					return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
				default:
					break;
			}
		}
		
		return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			switch (facing) {
				case DOWN:
					return (T)output;
				case UP: 
					return (T)input;
				default:
					break;
			}
		} else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return (T)tank;
		}
		
		return super.getCapability(capability, facing);
	}

}
