package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


/**
 * Client Class used as a main entry point for the program and for
 * initializing parameters.
 * 
 * @author Aleksey
 * 
 * 
 */

public class Client {
	
	/**
	 * Main entry point in the client side program
	 * 
	 * @param args (is ignored)
	 */
	
	public static void main(String args[]) {
		
		
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
						
			TaskQueue que = new TaskQueue();
			Conn conn = new Conn(socket, socket.getOutputStream());
			
			ServerListener listener = new ServerListener(
					socket.getInputStream() , que);
			ClientGame game = new ClientGame(conn, que);	
			
			Thread listenerThread = new Thread(listener);
			Thread gameThread = new Thread(game);
			
			listenerThread.start();
			gameThread.start();
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
			

			
			


