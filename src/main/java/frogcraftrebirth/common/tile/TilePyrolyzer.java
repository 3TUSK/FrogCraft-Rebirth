/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
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
import frogcraftrebirth.api.recipes.IPyrolyzerRecipe;
import frogcraftrebirth.client.gui.GuiPyrolyzer;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.capability.FluidHandlerOutputWrapper;
import frogcraftrebirth.common.lib.capability.ItemHandlerInputWrapper;
import frogcraftrebirth.common.lib.capability.ItemHandlerOutputWrapper;
import frogcraftrebirth.common.lib.recipes.FrogRecipeInputItemStack;
import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TilePyrolyzer extends TileEnergySink implements IHasGui, IHasWork, ITickable {

	private static final int INPUT = 0, OUTPUT = 0, INPUT_F = 0, OUTPUT_F = 1;
	public final ItemStackHandler input = new ItemStackHandler();
	public final ItemStackHandler output = new ItemStackHandler();
	public final ItemStackHandler fluidIO = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(16000);
	public int process, processMax;
	private boolean working;
	private IPyrolyzerRecipe recipe;

	public TilePyrolyzer() {
		super(2, 20000);
	}
	
	@Override
	public boolean isWorking() {
		return working;
	}

	private int count = 0;
	@Override
	public void update() {
		if (this.getWorld().isRemote) {
			if (count++ > 20) {
				getWorld().markBlockRangeForRenderUpdate(getPos(), getPos());
				count = 0;
			}
			return;
		}
		
		if (!fluidIO.getStackInSlot(INPUT_F).isEmpty()) {
			if (fluidIO.getStackInSlot(INPUT_F).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
				FluidActionResult result = FluidUtil.tryFillContainer(fluidIO.extractItem(INPUT_F, 1, true), tank, 1000, null, true);
				if (result.isSuccess() && result.result.getCount() > 0) {
					fluidIO.extractItem(INPUT_F, 1, false);
					ItemStack remainder = fluidIO.insertItem(OUTPUT_F, result.result, false);
					if (!remainder.isEmpty() && remainder.getCount() > 0)
						ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
				}
			}
		}

		if (input.getStackInSlot(INPUT).isEmpty()) { //Operation aborted
			this.working = false;
			this.recipe = null;
			this.process = 0;
			this.processMax = 0;
		}
		
		if (!working || recipe == null) {
			recipe = FrogAPI.managerPyrolyzer.getRecipe(new FrogRecipeInputItemStack(input.getStackInSlot(INPUT)));
			if (canWork(recipe)) {
				this.process = 0;
				this.processMax = recipe.getTime();
				this.working = true;
			} else {
				this.recipe = null;
				this.working = false;
				this.sendTileUpdatePacket(this);
				this.markDirty();
				return;
			}
		} else {
			if (this.charge <= recipe.getEnergyPerTick()) {
				process = 0; //Similar as GregTech machine, all progress will lose when power is insufficient
			} else {
				this.charge -= recipe.getEnergyPerTick();
				process++;
			}
			
			if (process == processMax) {
				pyrolyze();
				process = 0;
				processMax = 0;
				recipe = null;
			}
		}
		
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}
	
	private boolean canWork(@Nullable IPyrolyzerRecipe recipe) {
		if (recipe == null)
			return false;

		if (tank.getFluid() != null) {
			if (!tank.getFluid().equals(recipe.getOutputFluid()))
				return false;
			else if (tank.getFluidAmount() + recipe.getOutputFluid().amount > tank.getCapacity())
				return false;
		}

		return input.getStackInSlot(INPUT).isItemEqual(recipe.getInput()) && !input.extractItem(INPUT, recipe.getInput().getCount(), true).isEmpty();
	}
	
	private void pyrolyze() {
		input.extractItem(INPUT, recipe.getInput().getCount(), false);
		ItemStack remainder = output.insertItem(OUTPUT, recipe.getOutput(), false);
		if (!remainder.isEmpty() && remainder.getCount() > 0)
			ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
		if (recipe.getOutputFluid() != null) {
			tank.fill(recipe.getOutputFluid(), true);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.tank.readFromNBT(tag);
		this.input.deserializeNBT(tag.getCompoundTag("input"));
		this.output.deserializeNBT(tag.getCompoundTag("output"));
		this.fluidIO.deserializeNBT(tag.getCompoundTag("fluidIO"));
		this.working = tag.getBoolean("working");
		this.process = tag.getInteger("process");
		this.processMax = tag.getInteger("processMax");
		this.recipe = FrogAPI.managerPyrolyzer.getRecipe(new FrogRecipeInputItemStack(new ItemStack(tag.getCompoundTag("recipeInput"))));
	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		tank.readPacketData(input);
		working = input.readBoolean();
		process = input.readInt();
		processMax = input.readInt();
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		tank.writePacketData(output);
		output.writeBoolean(working);
		output.writeInt(process);
		output.writeInt(processMax);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		this.tank.writeToNBT(tag);
		tag.setTag("input", this.input.serializeNBT());
		tag.setTag("output", this.output.serializeNBT());
		tag.setTag("fluidIO", this.fluidIO.serializeNBT());
		tag.setBoolean("working", this.working);
		tag.setInteger("process", this.process);
		tag.setInteger("processMax", this.processMax);
		if (recipe != null)
			tag.setTag("recipeInput", recipe.getInput().writeToNBT(new NBTTagCompound()));
		return super.writeToNBT(tag);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		if (facing == null)
			return false;

		switch (facing) {
			case UP:
			case DOWN:
				return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
			case NORTH:
			case EAST:
			case SOUTH:
			case WEST:
				return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
			default:
				return super.hasCapability(capability, facing);
		}
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != null) {
			switch (facing) {
				case UP:
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandlerInputWrapper(input));
				case DOWN:
					return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandlerOutputWrapper(output));
				default:
					break;
			}
		} else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			if (facing != null)
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
				.withPlayerInventory(player.inventory)
				.withStandardSlot(input, 0, 24, 28)
				.withOutputSlot(output, 0, 75, 28)
				.withStandardSlot(fluidIO, 0, 113, 21)
				.withOutputSlot(fluidIO, 1, 113, 56)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiPyrolyzer(this.getGuiContainer(world, player), this);
	}

}
