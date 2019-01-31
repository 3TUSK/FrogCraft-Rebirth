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

import frogcraftrebirth.api.mps.IMobilePowerStation;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.client.gui.GuiMPS;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.tile.TileEnergy;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileMobilePowerStation extends TileEnergy implements IHasGui, ITickable, IEnergySource, IMobilePowerStation {

	private static final int 
	UPGRADE_SOLAR = 0, UPGRADE_VOLTAGE = 1, UPGRADE_STORAGE = 2, 
	CHARGE_IN = 3, CHARGE_OUT = 4;
	
	private final ItemStackHandler inv = new ItemStackHandler(5);

	private int energy;
	
	private int energyMax = 60000;
	private int tier = 1;
	
	@Override
	public void update() {
		if (this.getWorld().isRemote) {
			return;
		}

		//Check storage upgrade, if pass, increase energy capacity
		if (this.inv.getStackInSlot(UPGRADE_STORAGE).isEmpty()) {
			this.energyMax = 60000;
		} else {
			this.energyMax = 60000 + MPSUpgradeManager.INSTANCE.getEnergyStoreIncrementOf((inv.getStackInSlot(UPGRADE_STORAGE)));
		}
		//Check transformer upgrade, if pass, increase voltage level
		if (this.inv.getStackInSlot(UPGRADE_VOLTAGE).isEmpty()) {
			this.tier = 1;
		} else {
			this.tier = 1 + MPSUpgradeManager.INSTANCE.getVoltageIncrementOf(inv.getStackInSlot(UPGRADE_VOLTAGE));
		}
		//Check solar upgrade, if pass, generate energy from sunlight
		if (MPSUpgradeManager.INSTANCE.isSolarUpgradeValid(inv.getStackInSlot(UPGRADE_SOLAR)) && getWorld().isDaytime() && getWorld().canBlockSeeSky(getPos())) {
			this.energy += 1;
		}
		// For each tick, there is 10% probability that overflowed energy disappears
		if (this.energy > this.energyMax && this.getWorld().rand.nextInt(10) == 1) {
			this.energy = this.energyMax;
		}
		//Extract energy from charge-in slot
		if (!this.inv.getStackInSlot(CHARGE_IN).isEmpty()) {
			this.energy -= ElectricItem.manager.charge(inv.getStackInSlot(CHARGE_IN), this.getOfferedEnergy(), this.tier, false, false);
		}
		//Offer energy to item that is in charge-out slot
		if (!this.inv.getStackInSlot(CHARGE_OUT).isEmpty()) {
			this.energy += ElectricItem.manager.discharge(inv.getStackInSlot(CHARGE_OUT), 32, this.tier, true, true, false);
		}
		
		this.syncToTrackingClients();
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		loadDataFrom(tag);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		saveDataTo(tag);
		return super.writeToNBT(tag);
	}
	
	@Override
	public NBTTagCompound writePacketData(NBTTagCompound data) {
		data.setInteger("charge", energy);
		data.setInteger("maxCharge", energyMax);
		data.setInteger("tier", tier);
		return super.writePacketData(data);
	}

	@Override
	public void readPacketData(NBTTagCompound data) {
		super.readPacketData(data);
		this.energy = data.getInteger("charge");
		this.energyMax = data.getInteger("maxCharge");
		this.tier = data.getInteger("tier");
	}
	
	public int getCurrentEnergy() {
		return this.energy;
	}
	
	public int getCurrentEnergyCapacity() {
		return this.energyMax;
	}
	
	@Override
	public void loadDataFrom(NBTTagCompound tag) {
		energy = tag.getInteger("charge");
		energyMax = tag.getInteger("maxCharge");
		tier = tag.getInteger("tier");
		inv.deserializeNBT(tag.getCompoundTag("inv"));
	}
	
	@Override
	public void saveDataTo(NBTTagCompound tag) {
		tag.setInteger("charge", energy);
		tag.setInteger("maxCharge", energyMax);
		tag.setInteger("tier", tier);
		tag.setTag("inv", inv.serializeNBT());
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing direction) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		return Math.min(energy, getSourceTier() * 32);
	}

	@Override
	public void drawEnergy(double amount) {
		energy -= (int)amount;
		if (energy < 0)
			energy = 0;
	}

	@Override
	public int getSourceTier() {
		return tier;
	}

	@Override
	public void onBlockDestroyed(World worldIn, BlockPos pos, IBlockState state) {}

	@Override
	public ContainerTileFrog getGuiContainer(World world, EntityPlayer player) {
		return ContainerTileFrog.Builder.from(this)
				.withStandardSlot(inv, UPGRADE_SOLAR, 20, 20)
				.withStandardSlot(inv, UPGRADE_VOLTAGE, 38, 20)
				.withStandardSlot(inv, UPGRADE_STORAGE, 56, 20)
				.withChargerSlot(inv, CHARGE_IN, 113, 24)
				.withDischargeSlot(inv, CHARGE_OUT, 113, 42)
				.withPlayerInventory(player.inventory)
				.build();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog> getGui(World world, EntityPlayer player) {
		return new GuiMPS(this.getGuiContainer(world, player), this);
	}

}
