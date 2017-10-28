/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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
import java.util.UUID;

import frogcraftrebirth.api.mps.IMobilePowerStation;
import frogcraftrebirth.api.mps.MPSUpgradeManager;
import frogcraftrebirth.client.gui.GuiMPS;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerMPS;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileMobilePowerStation extends TileFrog implements IHasGui, ITickable, IEnergySource, IMobilePowerStation {

	private static final int 
	UPGRADE_SOLAR = 0, UPGRADE_VOLTAGE = 1, UPGRADE_STORAGE = 2, 
	CHAGRE_IN = 3, CHARGE_OUT = 4; 
	
	public final ItemStackHandler inv = new ItemStackHandler(5);

	private int energy;
	
	protected int energyMax = 60000;
	protected int tier = 1;
	
	private UUID owner;
	
	private boolean isInENet = false;
	
	@Override
	public void invalidate() {
		if (!getWorld().isRemote && isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			isInENet = false;
		}
		super.invalidate();
	}
	
	@Override
	public void validate() {
		super.validate();
	}
	
	@Override
	public void update() {
		if (getWorld().isRemote)
			return;	
		if (!isInENet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			isInENet = true;
		}
		//Check storage upgrade, if pass, increase energy capacity
		if (inv.getStackInSlot(UPGRADE_STORAGE).isEmpty()) {
			energyMax = 60000;
		} else {
			energyMax = 60000 + MPSUpgradeManager.INSTANCE.getEnergyStoreIncreasementFrom((inv.getStackInSlot(UPGRADE_STORAGE)));
		}
		//Check transformer upgrade, if pass, increase voltage level
		if (inv.getStackInSlot(UPGRADE_VOLTAGE).isEmpty()) {
			tier = 1;
		} else {
			tier = 1 + MPSUpgradeManager.INSTANCE.getVoltageIncreasementFrom(inv.getStackInSlot(UPGRADE_VOLTAGE));
		}
		//Check solar upgrade, if pass, generate energy from sunlight
		if (MPSUpgradeManager.INSTANCE.isSolarUpgradeValid(inv.getStackInSlot(UPGRADE_SOLAR)) && getWorld().isDaytime() && getWorld().canBlockSeeSky(getPos())) {
			energy += 1;
		}
		// For each tick, there is 10% probability that overflowed energy disappears
		if (energy > energyMax && getWorld().rand.nextInt(10) == 1)
			energy = energyMax;
		//Extract energy from charge-in slot
		if (!inv.getStackInSlot(CHAGRE_IN).isEmpty() && inv.getStackInSlot(CHAGRE_IN).getItem() instanceof IElectricItem) {
			this.energy += ElectricItem.manager.discharge(inv.getStackInSlot(CHAGRE_IN), 32, getSourceTier(), true, true, false);
		}
		//Offer energy to item that is in charge-out slot
		if (!inv.getStackInSlot(CHARGE_OUT).isEmpty() && inv.getStackInSlot(CHARGE_OUT).getItem() instanceof IElectricItem) {
			this.energy -= ElectricItem.manager.charge(inv.getStackInSlot(CHARGE_OUT), this.getOfferedEnergy(), getSourceTier(), false, false);
		}
		
		this.sendTileUpdatePacket(this);
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
	public void writePacketData(DataOutputStream output) throws IOException {
		output.writeInt(energy);
		output.writeInt(energyMax);
		output.writeInt(tier);
		output.writeUTF(owner != null ? owner.toString() : "NULL");
	}


	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		energy = input.readInt();
		energyMax = input.readInt();
		tier = input.readInt();
		try {
			owner = UUID.fromString(input.readUTF());
		} catch (IllegalArgumentException e) {
			owner = null;
		}
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
		try {
			owner = UUID.fromString(tag.getString("owner"));
		} catch (IllegalArgumentException e) {
			owner = null;
		}
	}
	
	@Override
	public void saveDataTo(NBTTagCompound tag) {
		tag.setInteger("charge", energy);
		tag.setInteger("maxCharge", energyMax);
		tag.setInteger("tier", tier);
		tag.setTag("inv", inv.serializeNBT());
		tag.setString("owner", owner != null ? owner.toString() : "NULL");
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
	public ContainerTileFrog<? extends TileFrog> getGuiContainer(World world, EntityPlayer player) {
		return new ContainerMPS(player.inventory, this);
	}

	@Override
	public GuiTileFrog<? extends TileFrog, ? extends ContainerTileFrog<? extends TileFrog>> getGui(World world, EntityPlayer player) {
		return new GuiMPS(player.inventory, this);
	}

}
