package poker.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import message.data.ClientResponse;
import commands.Command;
import commands.SetIDCommand;
import poker.arturka.Game;

public class Room implements Runnable {

	private int clientSessionID = 1;

	public Room(int roomID) {
		connections = new HashMap<Integer, Connection>();
		this.roomID = roomID;
		System.out.println("Room with ID: " + roomID + " created!");
	}

	public void run() {
		pokerGame = new Game(this);
		Thread t = new Thread(pokerGame);
		t.start();
		System.out.println("Game started in room ID: " + roomID
				+ ". Player count: " + this.connections.size());
		assignClientID();
	}

	private void assignClientID() {
		for (Integer id : connections.keySet()) {
			try {
				out = new ObjectOutputStream(connections.get(id).getClient()
						.getOutputStream());
                System.out.println(id);
                out.writeObject(new SetIDCommand(id));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addUser(Socket client) {
		clientConnection = new Connection(client);
		connections.put(clientSessionID++, clientConnection);
		Thread t = new Thread(clientConnection);
		t.start();
		System.out.println("Player (ID:" + (clientSessionID - 1)
				+ ") added to the room (ID: " + roomID + ")");
	}

	public List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		for (Integer id : connections.keySet()) {
			users.add(id);
		}
		return users;
	}

	public Object getClientMove(int clientSessionID) {
		if (connections.containsKey(clientSessionID)
				&& connections.get(clientSessionID).getClient().isConnected()) {
			return connections.get(clientSessionID).getMove();
		}
		return null;
	}

	public void removeUser(int clientSessionID) {
		if (connections.containsKey(clientSessionID)
				&& connections.get(clientSessionID).getClient().isConnected()) {
			connections.remove(clientSessionID);
		}
	}

	public ClientResponse sendToUser(int id, Command command) {
		if (connections.containsKey(id)) {
			try {
				out = new ObjectOutputStream(connections.get(id).getClient()
						.getOutputStream());
				out.writeObject(command);
				out.flush();
				return (ClientResponse) getClientMove(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void Broadcast(Command command) {
		for (Connection clientConnection : connections.values()) {
			try {
				out = new ObjectOutputStream(clientConnection.getClient()
						.getOutputStream());
				out.writeObject(command);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int roomID;
	private HashMap<Integer, Connection> connections;
	private Game pokerGame;
	private Connection clientConnection;
	public ObjectOutputStream out;
}
