package frogcraftrebirth.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * Implementing this interface marks the target has ability to synchronizing
 * data between server and client. Note: this interface is not considered as
 * completed yet.
 * @author 3TUSK
 */
public interface IFrogNetworkObject {

	void writePacketData(DataOutputStream output) throws IOException;

	void readPacketData(DataInputStream input) throws IOException;

}
