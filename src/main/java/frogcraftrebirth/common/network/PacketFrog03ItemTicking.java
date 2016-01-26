/**
 * This file is a part of FrogCraftRebirth, 
 * created by 3TUSK at 11:26:34 PM, Nov 28, 2015 EST
 * FrogCraftRebirth, is open-source under MIT license,
 * check https://github.com/FrogCraft-Rebirth/
 * FrogCraft-Rebirth/LICENSE_FrogCraft_Rebirth for more info
 */
package frogcraftrebirth.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.common.lib.item.ItemFrogCraft;

public class PacketFrog03ItemTicking implements IFrogPacket {
	
	//note: this packet cannot function yet. Don't use it.
	
	private ItemFrogCraft item;
	
	PacketFrog03ItemTicking() {}
	
	public PacketFrog03ItemTicking(ItemFrogCraft item) {
		this.item = item;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(PACKET_ITEM);
		item.writePacketData(output);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		item.readPacketData(input);
	}

}
