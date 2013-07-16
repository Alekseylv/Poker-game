package client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Conn {
	
	public final Socket socket;
	public final Scanner in;
	public final PrintWriter out;
	
	public Conn(Socket socket, Scanner in, PrintWriter out) {
		this.socket = socket;
		this.in = in;
		this.out = out;
		in.useDelimiter("\n");
	}
	
}
