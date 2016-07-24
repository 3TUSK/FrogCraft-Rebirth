package frogcraftrebirth.common.lib.tile;

import static frogcraftrebirth.common.network.NetworkHandler.FROG_NETWORK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.api.IFrogNetworkObject;
import frogcraftrebirth.common.network.PacketFrog00TileUpdate;
import net.minecraft.tileentity.TileEntity;

public abstract class TileFrog extends TileEntity implements IFrogNetworkObject {

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		
	}
	
	protected void sendTileUpdatePacket(TileFrog tile) {
		FROG_NETWORK.sendToAllAround(new PacketFrog00TileUpdate(tile), this.worldObj.provider.getDimensionType().getId(), tile.pos.getX(), tile.pos.getY(), tile.pos.getZ(), 2);
	}

}
