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

import frogcraftrebirth.api.air.IAirPump;
import frogcraftrebirth.client.gui.GuiAirPump;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.FrogConfig;
import frogcraftrebirth.common.gui.ContainerAirPump;
import frogcraftrebirth.common.gui.ContainerTileFrog;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.tile.TileEnergySink;
import frogcraftrebirth.common.lib.tile.TileFrog;
import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileAirPump extends TileEnergySink implements IHasGui, ITickable, IAirPump, IHasWork {

	private static final int MAX_AIR = 1000;
	private static final int MAX_CHARGE = 10000;
	
	public int charge;
	private int airAmount, tick;
	
	public TileAirPump() {
		super(1, MAX_CHARGE);
	}
	
	@Override
	public boolean isWorking() {
		return getWorld().isBlockIndirectlyGettingPowered(this.pos) == 0;
	}
	
	@Override
	public void update() {
		if (getWorld().isRemote) {
			return;
		}
		
		if (this.getWorld().isBlockIndirectlyGettingPowered(this.pos) != 0)
			return;
		
		if (this.charge < FrogConfig.airPumpPowerRate)
			return;
		
		if (airAmount >= MAX_AIR) {
			this.airAmount = MAX_AIR;
			return;
		}
		
		this.charge -= FrogConfig.airPumpPowerRate;
		this.tick++;
		if (tick == 4) {
			this.airAmount += FrogConfig.airPumpGenerationRate;
			tick = 0;
		}
		this.sendTileUpdatePacket(this);
		this.markDirty();
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.airAmount = tag.getInteger("air");
		this.tick = tag.getInteger("tick");
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setInteger("air", this.airAmount);
		tag.setInteger("tick", this.tick);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		super.writePacketData(output);
		output.writeInt(airAmount);
		output.writeInt(tick);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		super.readPacketData(input);
		this.airAmount = input.readInt();
		this.tick = input.readInt();
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing direction) {
		return direction != EnumFacing.UP;
	}

	@Override
	public int airAmount() {
		return airAmount;
	}

	@Override
	public int extractAir(EnumFacing from, int amount, boolean simluated) {
		if (amount > this.airAmount) {
			int toReturn = this.airAmount;
			if (!simluated)
				this.airAmount = 0;
			return toReturn;
		} else {
			if (!simluated)
				this.airAmount -= amount;
			return amount;
		}
	}

	@Override
	public ContainerTileFrog<? extends TileFrog> getGuiContainer(World world, EntityPlayer player) {
		return new ContainerAirPump(player.inventory, this);
	}

	@Override
	public GuiTileFrog<? extends TileFrog, ? extends ContainerTileFrog<? extends TileFrog>> getGui(World world, EntityPlayer player) {
		return new GuiAirPump(player.inventory, this);
	}
}
