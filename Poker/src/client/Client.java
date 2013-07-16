package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

	
		public static void main(String args[]) {
			
			
			try {
				Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
				Scanner in = new Scanner(socket.getInputStream());
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				
				Conn conn = new Conn(socket, in, out);
				ClientModel model = new ClientModel();
				ClientView view = new ClientView(model);
				ClientController controller = new ClientController(model, view);
				ClientGame game = new ClientGame(conn, model, view, controller);
				
				conn.out.println("PLAY");
				String token = in.next();
				if(token.equals("PLAYER WAIT"));
				token = in.next();
				if(token.equals("GAME START")) game.start();
				
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
}
			

			
			


