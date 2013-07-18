package poker.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	private static final int PORT = 9999;
	private static final int CONNECTION_LIMIT = 20;
	private static final int MAX_PLAYERS_IN_ROOM = 2;
	private static final int WAITING_TIMEOUT = 2000;

	private static int roomID = 1;
	private static boolean waitingTimeExceeded = false;

	public static void main(String[] args) {
		server = null;
		client = null;
		try {
			serverAddress = InetAddress.getLocalHost();
			server = new ServerSocket(PORT, CONNECTION_LIMIT, serverAddress);
			while (true) {
				gameRoom = new Room(roomID++);
				int playersInRoom = 0;
				while (playersInRoom < MAX_PLAYERS_IN_ROOM
						&& !waitingTimeExceeded) {
					client = server.accept();
					gameRoom.addUser(client);
					playersInRoom++;
					if (playersInRoom == 2) {
						//startGameCountdown();
					}
				}
				playersInRoom = 0;
				Thread t = new Thread(gameRoom);
				t.start();
				waitingTimeExceeded = false;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setWaitingTimeExceeded(boolean state) {
		waitingTimeExceeded = state;
	}

	private static void startGameCountdown() {
		Timer timer = new Timer(WAITING_TIMEOUT, serverAddress, PORT, gameRoom);
		countdownThread = new Thread(timer);
		countdownThread.start();
	}

	private static ServerSocket server;
	private static Socket client;
	private static Room gameRoom;
	private static InetAddress serverAddress;
	private static Thread countdownThread;
}
