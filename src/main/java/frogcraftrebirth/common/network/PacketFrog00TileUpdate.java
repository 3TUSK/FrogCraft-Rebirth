package frogcraftrebirth.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import frogcraftrebirth.api.IFrogNetworkObject;
import frogcraftrebirth.common.lib.tile.TileFrog;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class PacketFrog00TileUpdate implements IFrogPacket {
	
	private TileFrog tile;
	
	PacketFrog00TileUpdate() {}
	
	public PacketFrog00TileUpdate(TileFrog tile) {
		this.tile = tile;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_TILE);
		output.writeInt(tile.getPos().getX());
		output.writeInt(tile.getPos().getY());
		output.writeInt(tile.getPos().getZ());
		tile.writePacketData(output);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void readData(DataInputStream input) throws IOException {
		int
		x = input.readInt(),
		y = input.readInt(),
		z = input.readInt();
		
		TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(new BlockPos(x, y, z));
		
		if  (tile instanceof IFrogNetworkObject)
			((IFrogNetworkObject)tile).readPacketData(input);
		
	}

}
