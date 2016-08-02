/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 2:55:25 PM, Apr 2, 2016, EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for 
 * more information.
 */
package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.air.IAirConsumer;
import frogcraftrebirth.api.air.IAirPump;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileLiquefier extends TileEnergySink implements IAirConsumer {
	
	public final ItemStackHandler inv = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	
	public int process;

	public TileLiquefier() {
		super(2, 10000);
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote)
			return;
		super.update();
		
		if (inv.getStackInSlot(0) != null) {
			if (inv.getStackInSlot(0).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				ItemStack result = FluidUtil.tryFillContainer(inv.extractItem(0, 1, false), tank, 1000, null, true);
				if (result != null && result.stackSize > 0) {
					ItemStack remainder = inv.insertItem(1, result, false);
					if (remainder != null && remainder.stackSize > 0)
						ItemUtil.dropItemStackAsEntityInsanely(worldObj, getPos(), remainder);
				}
			}
		}
		
		TileEntity tile = worldObj.getTileEntity(getPos().down());
		if (tile == null || !(tile instanceof IAirPump) || tank.isFull()) {
			this.sendTileUpdatePacket(this);
			this.markDirty();
			return;
		}
		
		if (++process < 200) {
			charge -= 128;
		} else {
			if (((IAirPump)tile).airAmount() >= 1000) {
				((IAirPump)tile).extractAir(EnumFacing.DOWN, 1000, false);	
				tank.fill(FluidRegistry.getFluidStack("ic2air", 10), true);
			}
			
			process = 0;
		}
		
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}
	
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		this.tank.readPacketData(input);
		this.process = input.readInt();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		this.tank.writePacketData(output);
		output.writeInt(process);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.process = tag.getInteger("process");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setInteger("process", this.process);
		return super.writeToNBT(tag);
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

	@Override
	public int inject(EnumFacing facing, int amount, boolean doInject) {
		return amount; //Currently it does not has any internal air storage.
	}

}
