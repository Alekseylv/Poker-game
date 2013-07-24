package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import message.data.ClientResponse;

/**
 * Represents the socket connection with the server
 * @author Aleksey
 *
 */

public class Conn {
	
	/**
	 * socket: open socket with the server
	 * out: outputstream of this socket
	 */
	public final Socket socket;
	public final ObjectOutputStream out;
	
	
	/**
	 * Constructs a connection object using socket and outputstream
	 * @param socket
	 *  The open socket
	 * @param out
	 *  The outputstream
	 * @throws IOException
	 *  Unable to initialize or flush outputstream properly
	 */
	public Conn(Socket socket, OutputStream out) throws IOException {
		this.socket = socket;
		this.out =  new ObjectOutputStream(out);
		out.flush();
		
	}
	
	/**
	 * Sends server a message from the client through the outputstream
	 * @param resp
	 *  The message to be sent
	 */
	public void sendResponse(ClientResponse resp) {
		try {
			out.writeObject(resp);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
