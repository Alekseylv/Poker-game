package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import poker.GUI.Login;
import poker.server.StartServer;


/**
 * Client Class used as a main entry point for the program and for
 * initializing parameters.
 * 
 * @author Aleksey
 */

public class Client {
	
	/**
	 * Main entry point in the client side program
	 * 
	 * @param args (is ignored)
	 */
    private static Login login;

	public static void main(String args[]) throws UnknownHostException {
       login = new Login();
	}
	
	public static void start(String name, Integer port, byte[] address) {
		
		if(port == null) port = 9876;
		if(address == null)
			try {
				address = InetAddress.getLocalHost().getAddress();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
		try {
			Socket socket = new Socket(InetAddress.getByAddress(address), port);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println(name);
			out.flush();

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
	
	
	public static void startServer() {
		Thread t1 = new Thread(new StartServer());
		t1.start();
	}
}
			

			
			


