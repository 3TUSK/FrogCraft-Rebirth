package frogcraftrewrite.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import frogcraftrewrite.common.lib.tile.TileFrog;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public class PacketTileUpdate implements IFrogPacket {
	
	TileFrog tile;
	
	public PacketTileUpdate() {}
	
	public PacketTileUpdate(TileFrog tile) {
		this.tile = tile;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(PACKET_TILE);//packet identity number, will work around it later
		output.writeInt(tile.xCoord);
		output.writeInt(tile.yCoord);
		output.writeInt(tile.zCoord);
		tile.writePacketData(output);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void readData(DataInputStream input) throws IOException {
		int
		x = input.readInt(),
		y = input.readInt(),
		z = input.readInt();
		
		TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
		
		if  (tile instanceof TileFrog)
			((TileFrog)tile).readPacketData(input);
		
	}

}
