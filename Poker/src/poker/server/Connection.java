package poker.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import message.data.ClientResponse;

public class Connection implements Runnable {

	private static final int READ_TIMEOUT = 4500;


	public Connection(Socket client) {
		this.client = client;
	}

	public void run() {

	}

	public ClientResponse getMove() {
		try {
			client.setSoTimeout(READ_TIMEOUT);
			in = new ObjectInputStream(client.getInputStream());
			ClientResponse move = null;
			try {
				move = (ClientResponse) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in.close();
			return move;
		} catch (IOException e) {
			return null;
		}
	}

	public Socket getClient() {
		return client;
	}

	private Socket client;
	public ObjectInputStream in;
}
