/**
 * This file is a part of FrogCraftRebirth, created by U_Knowledge at 11:26:34 PM, Nov 28, 2015, 2015 EST
 * The project, FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for more info
 */
package frogcraftrewrite.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrewrite.common.lib.item.ItemFrogCraft;

public class PacketFrog03ItemTicking implements IFrogPacket {
	
	private ItemFrogCraft item;
	
	PacketFrog03ItemTicking() {}
	
	public PacketFrog03ItemTicking(ItemFrogCraft item) {
		this.item = item;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_ITEM);
		item.writeData(output);
		
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		//todo: identify
		item.readData(input);
	}

}
