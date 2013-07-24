package tests;

import commands.Command;
import poker.GUI.ClientView;
import client.ClientController;
import client.ClientGame;
import client.ClientModel;
import client.Conn;
import client.TaskQueue;

public class FakeClientGame extends ClientGame {

	private TaskQueue taskList;
	private volatile boolean running;
	
	
	public FakeClientGame(Conn conn, TaskQueue queue) {
		super();
		taskList = queue;
		this.running = true;
	}
	
	public void run() {

		Command task = null;
		while(this.running) {
			synchronized (this.taskList) {
				if (this.taskList.isEmpty()) {
					try {
						taskList.wait();
					} catch (InterruptedException e) {
					
					e.printStackTrace();
					}
				} else {
					task = taskList.getNextTask();
					if(task != null) {
						task.execute(this);
						System.out.println("Executed command");
					} else {
						System.out.println("Task is null, discarding it");
					}
				}
			}
		
		
		}
	}
	
}
