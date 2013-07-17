package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import message.data.ClientResponse;

public class Conn {
	
	public final Socket socket;
	public final ObjectOutputStream out;
	
	public Conn(Socket socket, OutputStream out) throws IOException {
		this.socket = socket;
		this.out =  new ObjectOutputStream(out);
		out.flush();
		
	}
	
	
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
