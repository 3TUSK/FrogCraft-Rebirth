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

import frogcraftrebirth.api.air.IAirPump;
import frogcraftrebirth.client.gui.GuiLiquefier;
import frogcraftrebirth.client.gui.GuiTileFrog;
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
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileLiquefier extends TileEnergySink implements IHasGui, IHasWork, ITickable {
	
	private final ItemStackHandler inv = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);
	
	public int process;
	private boolean working;
	private boolean requireRefresh;

	public TileLiquefier() {
		super(2, 10000);
	}
	
	@Override
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
		
		if (!inv.getStackInSlot(0).isEmpty()) {
			if (inv.getStackInSlot(0).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				FluidActionResult result = FluidUtil.tryFillContainer(inv.extractItem(0, 1, false), tank, 1000, null, true);
				if (result.isSuccess() && result.result.getCount() > 0) {
					ItemStack remainder = inv.insertItem(1, result.result, false);
					if (!remainder.isEmpty() && remainder.getCount() > 0)
						ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
				}
			}
		}
		
		TileEntity tile = getWorld().getTileEntity(getPos().down());
		if (tile == null || !(tile instanceof IAirPump) || tank.isFull()) {
			this.working = false;
			this.sendTileUpdatePacket(this);
			this.markDirty();
			this.requireRefresh = true;
			return;
		}
		
		working = true;
		requireRefresh = true;
		
		if (charge >= 128) {
			charge -= 128;
			++process;
		}
		else if (charge < 0)
			charge = 0;

		// TODO Should we restore FrogCraft's own Liquefied Air? So that this machine can have orginal working flow

		if (process == 100) {
			// According to original FrogCraft, best match
			if (((IAirPump)tile).extractAir(EnumFacing.UP, 1200, true) > 1200) {
				((IAirPump)tile).extractAir(EnumFacing.UP, 1200, false);
				tank.fill(FluidRegistry.getFluidStack("ic2air", 1000), true);
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
		this.working = input.readBoolean();
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		this.tank.writePacketData(output);
		output.writeInt(process);
		output.writeBoolean(working);
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
				.withStandardSlot(inv, 0, 113, 21)
				.withOutputSlot(inv, 1, 113, 56)
				.withPlayerInventory(player.inventory)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiLiquefier(this.getGuiContainer(world, player), this);
	}

}
