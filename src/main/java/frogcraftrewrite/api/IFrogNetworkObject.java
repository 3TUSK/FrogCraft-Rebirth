package frogcraftrewrite.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface IFrogNetworkObject {
	
	void writePacketData(DataOutputStream output) throws IOException;
	
	void readPacketData(DataInputStream input) throws IOException;

}
