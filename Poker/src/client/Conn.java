package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Conn {
	
	public final Socket socket;
	public final ObjectOutputStream out;
	
	public Conn(Socket socket, OutputStream out) throws IOException {
		this.socket = socket;
		this.out =  new ObjectOutputStream(out);
		out.flush();
		
	}
	
}
