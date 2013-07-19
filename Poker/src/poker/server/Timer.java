package poker.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Timer implements Runnable {

	/* Initializes instance variables that are needed to simulate client connection. */
	public Timer(int waitingTime, InetAddress serverAddress, int serverPort,
			Room currentRoom) {
		this.waitingTime = waitingTime;
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		this.currentRoom = currentRoom;
	}

	/* Waits for a timeout and then starts the game. */
	public void run() {
		try {
			Thread.sleep(waitingTime);
			startGame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void startGame() {
		simulateClientConnection();
	}

	/* Simulates a client 'Socket' connection to interrupt the ServerSocket.accept() method. */
	private void simulateClientConnection() {
		try {
			Socket clientConnection = new Socket(serverAddress, serverPort);
			// Sets 'waitingTimeExceeded to true so that new connection to this room weren't made.
			Server.setWaitingTimeExceeded(true);
			// Removes simulation connection from the game room.
			currentRoom.removeUser(currentRoom.getUsers().size() - 1);
			// Closes simulated client connection.
			clientConnection.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Private instance variables */
	private int waitingTime;
	private InetAddress serverAddress;
	private int serverPort;
	private Room currentRoom;
}
