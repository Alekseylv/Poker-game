package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	private static final int PORT = 6666;
	private static final int CONNECTION_LIMIT = 20;
	private static final int MAX_PLAYERS_IN_ROOM = 10;
	private static final int COUNTDOWN_TO_START = 6000;
	
	private static int roomID = 1;
	
	public static void main(String[] args) {
		server = null;
		client = null;
		try {
			server = new ServerSocket(PORT, CONNECTION_LIMIT, InetAddress.getLocalHost());
			while (true) {
				gameRoom = new Room(roomID++);
				int playersInRoom = 0;
				while (playersInRoom < MAX_PLAYERS_IN_ROOM) {
					if (server.isBound())
						client = server.accept();
					gameRoom.addUser(client);
					playersInRoom++;
					if (playersInRoom > 1) {
						//server.setSoTimeout(COUNTDOWN_TO_START);
					}
				}
				server.setSoTimeout(0);
				playersInRoom = 0;
				Thread t = new Thread(gameRoom);
				t.start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ServerSocket server;
	private static Socket client;
	private static Room gameRoom;
}
