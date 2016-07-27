package frogcraftrebirth.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.gui.ContainerTileFrog;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.client.FMLClientHandler;

public class PacketFrog02GuiDataUpdate implements IFrogPacket {
	
	private ContainerTileFrog<?> guiContainer;
	
	PacketFrog02GuiDataUpdate() {}
	
	public PacketFrog02GuiDataUpdate(ContainerTileFrog<?> guiContainer) {
		this.guiContainer = guiContainer;
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_GUI);
		output.writeInt(guiContainer.windowId);
		guiContainer.writeDataForSync(output);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int gui = input.readInt();
		EntityPlayerSP player = FMLClientHandler.instance().getClient().thePlayer;
		if (player.openContainer.windowId == gui && player.openContainer instanceof ContainerTileFrog)
			((ContainerTileFrog<?>)player.openContainer).updateContainer(input);
	}

}
