package frogcraftrewrite.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class PacketFrog02GuiDataUpdate implements IFrogPacket {
	
	private int guiID, dataID, dataValue;
	
	PacketFrog02GuiDataUpdate() {}
	
	public PacketFrog02GuiDataUpdate(int guiID, int dataID, int dataValue) {
		this.guiID = guiID;
		this.dataID = dataID;
		this.dataValue = dataValue;
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_GUI);
		output.writeInt(guiID);
		output.writeInt(dataID);
		output.writeInt(dataValue);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int gui = input.readInt();
		int data = input.readInt();
		int value = input.readInt();
		EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
		if (player.openContainer != null && player.openContainer.windowId == gui)
			player.openContainer.updateProgressBar(data, value);
	}

}
