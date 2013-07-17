package poker.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Timer implements Runnable {

	public Timer(int waitingTime, InetAddress serverAddress, int serverPort,
			Room currentRoom) {
		this.waitingTime = waitingTime;
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		this.currentRoom = currentRoom;
	}

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

	private void simulateClientConnection() {
		try {
			Socket clientConnection = new Socket(serverAddress, serverPort);
			Server.setWaitingTimeExceeded(true);
			currentRoom.removeUser(currentRoom.getUsers().size() - 1);
			clientConnection.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int waitingTime;
	private InetAddress serverAddress;
	private int serverPort;
	private Room currentRoom;
}
