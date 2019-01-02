/*
 * Copyright (c) 2015 - 2019 3TUSK, et al.
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.client.gui.GuiCombustionFurnace;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.FrogConfig;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.capability.FluidHandlerOutputWrapper;
import frogcraftrebirth.common.lib.capability.ItemHandlerInputWrapper;
import frogcraftrebirth.common.lib.capability.ItemHandlerOutputWrapper;
import frogcraftrebirth.common.lib.tile.TileEnergyGenerator;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;

public class TileCombustionFurnace extends TileEnergyGenerator implements IHasGui, IHasWork {

	private static final int CHARGE_MAX = 5000;

	private final ItemStackHandler input = new ItemStackHandler();
	private final ItemStackHandler output = new ItemStackHandler();
	private final ItemStackHandler fluidIO = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	
	public boolean working = false;
	public int time = 0, timeMax = 0;
	private ItemStack burning;
	private boolean requireRefresh;

	public TileCombustionFurnace() {
		super(1, 16);
	}
	
	public boolean isWorking() {
		return working;
	}

	@Override
	public void update() {
		if (getWorld().isRemote) {
			if (requireRefresh) {
				getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
				requireRefresh = false;
			}
			return;
		}
		
		//Don't stuck the inventory
		if (!output.getStackInSlot(0).isEmpty() && output.getStackInSlot(0).getCount() >= output.getStackInSlot(0).getMaxStackSize()) {
			this.sendTileUpdatePacket(this);
			this.markDirty();
			this.requireRefresh = true;
			return;
		}
		
		if (working) {
			this.charge += FrogConfig.combustionFurnaceGenRate;
			this.time--;
		} else if (!input.extractItem(0, 1, true).isEmpty()) {
			int burnTime = ForgeEventFactory.getItemBurnTime(input.getStackInSlot(0));
			if (burnTime > 0) {
				this.working = true;
				this.burning = input.extractItem(0, 1, false);
				this.timeMax = burnTime / 4;
				this.time = burnTime / 4;
			}
		}
		//Void overflowed power
		if (this.charge > CHARGE_MAX)
			this.charge = CHARGE_MAX;

		if (this.time <= 0) {
			this.timeMax = 0;
			if (working && !burning.isEmpty())
				bonus(burning);
			burning = null;
			this.working = false;
			this.requireRefresh = true;
		}
		
		if (tank.getFluidAmount() != 0 && !fluidIO.getStackInSlot(0).isEmpty()) {
			if (fluidIO.getStackInSlot(0).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				FluidActionResult result = FluidUtil.tryFillContainer(fluidIO.extractItem(0, 1, true), tank, 1000, null, true);
				if (result.isSuccess() && result.result.getCount() > 0) {
					fluidIO.extractItem(0, 1, false);
					ItemStack remainder = fluidIO.insertItem(1, result.result, false);
					if (!remainder.isEmpty() && remainder.getCount() > 0)
						ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
				}
			}
		}
		
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}
	
	private void bonus(ItemStack input) {
		if (input.isEmpty())
			return;
		
		int[] oreIDs = OreDictionary.getOreIDs(input);
		if (oreIDs.length != 0) { //Why is 0? According to my experience, having more than one ore dict entry is very very rare.
			String oreName = OreDictionary.getOreName(oreIDs[0]);
			//Feature: if there is no space for byproduct, they just go disappear
			output.insertItem(0, FrogAPI.FUEL_REG.getItemByproduct(oreName), false);
			tank.fill(FrogAPI.FUEL_REG.getFluidByproduct(oreName), true);
		} else {
			output.insertItem(0, FrogAPI.FUEL_REG.getItemByproduct(input), false);
			tank.fill(FrogAPI.FUEL_REG.getFluidByproduct(input), true);
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
		this.burning = new ItemStack(tag.getCompoundTag("burning"));
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
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		if (facing == null) {
			return false;
		}
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return true;
		}
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
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != null) {
			switch (facing) {
				case DOWN:
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandlerOutputWrapper(output));
				case UP: 
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY .cast(new ItemHandlerInputWrapper(input));
				default:
					break;
			}
		} else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new FluidHandlerOutputWrapper(tank));
		}
		
		return super.getCapability(capability, facing);
	}


	@Override
	public void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state) {
		ItemUtil.dropInventoryItems(worldIn, pos, input, output, fluidIO);
	}

	@Override
	public ContainerTileFrog getGuiContainer(World world, EntityPlayer player) {
		return ContainerTileFrog.Builder.from(this)
				.withFurnaceFuelSlot(input, 0, 24, 28)
				.withOutputSlot(output, 0, 75, 28)
				.withStandardSlot(fluidIO, 0, 113, 21)
				.withOutputSlot(fluidIO, 1, 113, 56)
				.withPlayerInventory(player.inventory)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiCombustionFurnace(this.getGuiContainer(world, player), this);
	}

}
