package frogcraftrewrite.common.tile;

import static frogcraftrewrite.common.network.NetworkHandler.FROG_NETWORK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrewrite.common.lib.tile.TileFrog;
import frogcraftrewrite.common.network.PacketFrog00TileUpdate;
import net.minecraft.nbt.NBTTagCompound;

public class TileAcademyWindmillTurbine extends TileFrog {

	public boolean canGenEnergy, hasRotor, isBlocked, isHighEnough, isRaining;

	public TileAcademyWindmillTurbine() {
		this.hasRotor = false;
	}

	private int count = 0;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.isRemote)
			return;
		this.isRaining = this.worldObj.isRaining();

		this.isHighEnough = this.yCoord > 78;

		for (int a = -5; a < 6; a++)
			for (int b = -5; b < 6; b++)
				for (int c = -5; c < 6; c++)
					if (!worldObj.isAirBlock(xCoord + a, yCoord + b, zCoord + c))
						++count;
		this.isBlocked = count < 13;
		count = 0;

		this.canGenEnergy = hasRotor && !isBlocked && !isRaining;
		markDirty();
		FROG_NETWORK.sendToAll(new PacketFrog00TileUpdate(this));
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.hasRotor = tag.getBoolean("rotor");
		this.isBlocked = tag.getBoolean("isBlocked");
		this.isHighEnough = tag.getBoolean("enoughHeight");
		this.isRaining = tag.getBoolean("raining");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setBoolean("rotor", hasRotor);
		tag.setBoolean("isBlocked", isBlocked);
		tag.setBoolean("enoughHeight", isHighEnough);
		tag.setBoolean("raining", isRaining);
	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {

	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {

	}

}