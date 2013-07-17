package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import commands.Command;

public class ServerListener implements Runnable {

	private ObjectInputStream in;
	private TaskQueue que;
	
	public ServerListener(InputStream in, TaskQueue que) {
		try {
			this.in =  new ObjectInputStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.que = que;
	}
	
	
	public void run() {
		
		while(true) {
			try {
				Object token = in.readObject();
				que.addTask((Command)token);
				// String token = in.next();
				// que.addTask(token)
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Failed to load the right Class");
				e.printStackTrace();
			}
		}
	}
}
