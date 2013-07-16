package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection implements Runnable {

	private static final int READ_TIMEOUT = 4500;
	
	private BufferedReader clientInput = null;
	
	public Connection(Socket client) {
		this.client = client;
	}
	
	public void run() {
		
	}

	public String getMove() {
		try {
			client.setSoTimeout(READ_TIMEOUT);
			clientInput = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			return clientInput.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Socket getClient() {
		return client;
	}

	private Socket client;
}
