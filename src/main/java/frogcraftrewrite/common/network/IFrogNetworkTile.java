package frogcraftrewrite.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IFrogNetworkTile {
	
	void writePacketData(DataOutputStream output) throws IOException;
	
	void readPacketData(DataInputStream input) throws IOException;

}
