package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.lib.capability.FluidHandlerOutputWrapper;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileFluidOutputHatch extends TileFrog implements ICondenseTowerOutputHatch, ITickable {

	private ICondenseTowerCore mainBlock;
	
	public final ItemStackHandler inv = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);

	@Override
	public void update() {
		if (!getWorld().isRemote) {
			if (tank.getFluidAmount() != 0 && inv.getStackInSlot(0) != null) {
				if (inv.getStackInSlot(0).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
					ItemStack result = FluidUtil.tryFillContainer(inv.extractItem(0, 1, true), tank, 1000, null, true);
					if (result != null && result.stackSize > 0) {
						inv.extractItem(0, 1, false);
						ItemStack remainder = inv.insertItem(1, result, false);
						if (remainder != null && remainder.stackSize > 0)
							ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
					}
				}
			} 
		}
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
		tank.readPacketData(input);
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		tank.writePacketData(output);
	}
	
	@Override
	public void behave() {
		
	}
	
	@Override
	public ICondenseTowerCore getMainBlock() {
		return mainBlock;
	}
	
	@Override
	public void setMainBlock(ICondenseTowerCore core) {
		this.mainBlock = core;
	}

	@Override
	public boolean canInject(FluidStack stack) {
		return stack != null ? tank.getFluid() == null ? true : this.tank.fill(stack, false) != 0 : false;
	}

	@Override
	public void inject(FluidStack stack, boolean simluated) {
		this.tank.fill(stack, simluated);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing currectFacing = getWorld().getBlockState(getPos()).getValue(BlockFrogWrenchable.FACING_HORIZONTAL);
			return currectFacing == facing;
		}
		
		return super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing currectFacing = getWorld().getBlockState(getPos()).getValue(BlockFrogWrenchable.FACING_HORIZONTAL);
			return currectFacing == facing ? (T)new FluidHandlerOutputWrapper(tank) : super.getCapability(capability, currectFacing);
		}
			
		return super.getCapability(capability, facing);
	}

}
