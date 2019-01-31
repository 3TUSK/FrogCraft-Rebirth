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

import frogcraftrebirth.client.gui.GuiHybridEStorage;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.tile.TileEnergyStorage;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import ic2.api.item.ElectricItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileHSU extends TileEnergyStorage implements IHasGui, ITickable {
	
	private final ItemStackHandler inv = new ItemStackHandler(2);
	
	public TileHSU() {
		this(100000000, 2048, 5, true);
	}

	TileHSU(int maxEnergy, int output, int tier, boolean allowTp) {
		super(maxEnergy, output, tier, allowTp);
	}

	public void updateOutputDirection(EnumFacing newDirection) {
		this.emitDir = newDirection;
	}

	@Override
	public void update() {
		if (this.getWorld().isRemote) {
			return;
		}
		
		if (!this.inv.getStackInSlot(1).isEmpty()) {
			this.storedE += ElectricItem.manager.discharge(inv.getStackInSlot(1), output, getSourceTier(), true, false, false);
		}
		
		if (!this.inv.getStackInSlot(0).isEmpty()) {
			this.storedE -= ElectricItem.manager.charge(inv.getStackInSlot(0), this.getOutputEnergyUnitsPerTick(), getSourceTier(), false, false);
		}
		
		this.syncToTrackingClients();
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.inv.deserializeNBT(tag.getCompoundTag("inv"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("inv", this.inv.serializeNBT());
		return super.writeToNBT(tag);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ?
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv) : super.getCapability(capability, facing);
	}

	@Override
	public void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state) {
		ItemUtil.dropInventoryItems(worldIn, pos, inv);
	}

	@Override
	public ContainerTileFrog getGuiContainer(World world, EntityPlayer player) {
		return ContainerTileFrog.Builder.from(this)
				.withChargerSlot(inv, 0, 113, 24)
				.withDischargeSlot(inv, 1, 113, 42)
				.withPlayerInventory(player.inventory)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiHybridEStorage(this.getGuiContainer(world, player), this, this instanceof TileHSUUltra);
	}

}
