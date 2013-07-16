package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import poker.arturka.Game;

public class Room implements Runnable {
	
	private int clientSessionID = 1;
	
	public Room(int roomID) {
		connections = new HashMap<Integer, Connection>();
		this.roomID = roomID;
		System.out.println("Room with ID: " + roomID + " created!");
	}
	
	public void run() {
		pokerGame = new Game();
		//Thread t = new Thread(pokerGame);
		//t.start();
	}
	
	public void addUser(Socket client) {
		Connection clientConnection = new Connection(client);
		connections.put(clientSessionID++, clientConnection);
		Thread t = new Thread(clientConnection);
		t.start();
		System.out.println("Player added to the room with ID: " + roomID);
	}
	
	public static List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		for(Integer id: connections.keySet()) {
			users.add(id);
		}
		return users;
	}
	
	public static String getClientMove(int clientSessionID) {
		if (connections.containsKey(clientSessionID)
				&& connections.get(clientSessionID).getClient().isConnected()) {
			return connections.get(clientSessionID).getMove();
		}
		return null;
	}

	public int roomID;
	public static HashMap<Integer, Connection> connections;
	public Game pokerGame;
}
