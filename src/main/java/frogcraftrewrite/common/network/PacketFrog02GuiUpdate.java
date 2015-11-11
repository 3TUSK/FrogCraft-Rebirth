package frogcraftrewrite.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrewrite.common.gui.ContainerTileFrog;
import frogcraftrewrite.common.lib.tile.TileFrog;

public class PacketFrog02GuiUpdate implements IFrogPacket {

	ContainerTileFrog<? extends TileFrog> tileContainer;
	
	public PacketFrog02GuiUpdate() {}
	
	public PacketFrog02GuiUpdate(ContainerTileFrog<? extends TileFrog> tileContainer) {
		this.tileContainer = tileContainer;
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_GUI);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		//TODO
	}

}
