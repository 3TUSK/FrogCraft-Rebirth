/*
 * Copyright (c) 2015 - 2020 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.tile;

import frogcraftrebirth.api.air.IAirPump;
import frogcraftrebirth.client.gui.GuiLiquefier;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.FrogFluids;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.capability.FluidHandlerOutputWrapper;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileLiquefier extends TileEnergySink implements IHasGui, IHasWork, ITickable {

	private static final int INPUT_AIR_IN = 0, INPUT_AIR_OUT = 1, OUTPUT_LIQUID_IN = 2, OUTPUT_LIQUID_OUT = 3;

	private final ItemStackHandler inv = new ItemStackHandler(4);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	
	public int process;
	private boolean working;

	public TileLiquefier() {
		super(2, 10000);
	}
	
	@Override
	public boolean isWorking() {
		return working;
	}
	
	@Override
	public void update() {
		if (this.getWorld().isRemote) {
			return;
		}

		if (!tank.isFull()) {
			this.doWorkCycle();
		}

		if (!inv.getStackInSlot(OUTPUT_LIQUID_IN).isEmpty()) {
			if (inv.getStackInSlot(OUTPUT_LIQUID_IN).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
				FluidActionResult result = FluidUtil.tryFillContainer(inv.extractItem(OUTPUT_LIQUID_IN, 1, true), tank, 1000, null, true);
				if (result.isSuccess() && result.result.getCount() > 0) {
					inv.extractItem(OUTPUT_LIQUID_IN, 1, false);
					ItemStack remainder = inv.insertItem(OUTPUT_LIQUID_OUT, result.result, false);
					if (!remainder.isEmpty() && remainder.getCount() > 0) {
						ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
					}
					this.markDirty();
				}
			}
		}
	}

	private void doWorkCycle() {
		TileEntity tile = getWorld().getTileEntity(getPos().down());
		if (tile instanceof IAirPump) {
			this.working = true;
		} else if (!inv.getStackInSlot(INPUT_AIR_IN).isEmpty()) {
			FluidStack contained = FluidUtil.getFluidContained(inv.getStackInSlot(INPUT_AIR_IN));
			if (contained != null && contained.getFluid() == FrogFluids.liquefiedAir) {
				this.working = true;
			}
		} else {
			this.working = false;
			this.syncToTrackingClients();
			this.markDirty();
			return;
		}

		if (this.charge >= 32) {
			this.charge -= 32;
			this.process++;
		} else if (this.charge < 0) {
			this.charge = 0;
		}

		if (this.process == 100) {
			// According to original FrogCraft, best match
			if (tile instanceof IAirPump && ((IAirPump) tile).extractAir(EnumFacing.UP, 1200, true) >= 1200) {
				((IAirPump) tile).extractAir(EnumFacing.UP, 1200, false);
				this.tank.fill(new FluidStack(FrogFluids.liquefiedAir, 1000), true);
			} else {
				IFluidHandlerItem fluidIn = FluidUtil.getFluidHandler(inv.extractItem(INPUT_AIR_IN, 1, true));
				if (fluidIn != null) {
					this.inv.extractItem(INPUT_AIR_IN, 1, false);
					fluidIn.drain(Integer.MAX_VALUE, true); // Blame IC2.
					ItemStack remainder = inv.insertItem(INPUT_AIR_OUT, fluidIn.getContainer(), false);
					this.tank.fill(new FluidStack(FrogFluids.liquefiedAir, 1000), true);
					if (!remainder.isEmpty() && remainder.getCount() > 0) {
						ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
					}
				}
			} // else? this round is waste.

			this.process = 0;
		}
	}
	
	@Override
	public void readPacketData(NBTTagCompound data) {
		super.readPacketData(data);
		this.tank.readFromNBT(data);
		this.process = data.getInteger("process");
		this.working = data.getBoolean("working");
	}
	
	@Override
	public NBTTagCompound writePacketData(NBTTagCompound data) {
		this.tank.writeToNBT(data);
		data.setInteger("process", this.process);
		data.setBoolean("working", this.working);
		return super.writePacketData(data);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
		this.process = tag.getInteger("process");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setTag("inv", this.inv.serializeNBT());
		tag.setInteger("process", this.process);
		return super.writeToNBT(tag);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new FluidHandlerOutputWrapper(tank));
		else 
			return super.getCapability(capability, facing);
	}

	@Override
	public void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state) {
		ItemUtil.dropInventoryItems(worldIn, pos, inv);
	}

	@Override
	public ContainerTileFrog getGuiContainer(World world, EntityPlayer player) {
		return ContainerTileFrog.Builder.from(this)
				.withStandardSlot(inv, 0, 47, 21)
				.withOutputSlot(inv, 1, 47, 56)
				.withStandardSlot(inv, 2, 113, 21)
				.withOutputSlot(inv, 3, 113, 56)
				.withPlayerInventory(player.inventory)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiLiquefier(this.getGuiContainer(world, player), this);
	}

}
