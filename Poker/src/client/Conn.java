package client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Conn {
	
	public final Socket socket;
	public final PrintWriter out;
	
	public Conn(Socket socket, PrintWriter out) {
		this.socket = socket;
		this.out = out;
	}
	
}
