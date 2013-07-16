package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import poker.arturka.Game;

public class Room implements Runnable {
	
	private int clientSessionID = 1;
	private BufferedReader clientInput = null;
	
	public Room(int roomID) {
		connections = new HashMap<Integer, Socket>();
		this.roomID = roomID;
	}
	
	public void run() {
		pokerGame = new Game();
	}
	
	public void addUser(Socket client) {
		connections.put(clientSessionID++, client);
	}
	
	public List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		for(Integer id: connections.keySet()) {
			users.add(id);
		}
		return users;
	}
	
	public String getClientMove(int clientSessionID) {
		if (connections.containsKey(clientSessionID)) {
			try {
				clientInput = new BufferedReader(new InputStreamReader(
						connections.get(clientSessionID).getInputStream()));
				return clientInput.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int roomID;
	public HashMap<Integer, Socket> connections;
	public Game pokerGame;
}
