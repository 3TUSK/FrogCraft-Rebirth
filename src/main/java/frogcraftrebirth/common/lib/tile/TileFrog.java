package frogcraftrebirth.common.lib.tile;

import static frogcraftrebirth.common.network.NetworkHandler.FROG_NETWORK;

import frogcraftrebirth.api.IFrogNetworkObject;
import frogcraftrebirth.common.network.PacketFrog00TileUpdate;
import net.minecraft.tileentity.TileEntity;

public abstract class TileFrog extends TileEntity implements IFrogNetworkObject {
	
	protected void sendTileUpdatePacket(TileFrog tile) {
		FROG_NETWORK.sendToAll(new PacketFrog00TileUpdate(tile));
	}

}
