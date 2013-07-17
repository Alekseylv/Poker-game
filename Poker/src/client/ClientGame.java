package client;

import java.util.Observer;

import commands.Command;
import poker.GUI.ClientView;

public class ClientGame implements Runnable {

	private Conn conn;
	private ClientModel model;
	private ClientView view;
	private ClientController controller;
	private volatile boolean running;
	private TaskQueue taskList;
	
	
	public ClientGame(Conn conn, TaskQueue queue) {
		
		this.taskList = queue;
		this.running = true;
		this.conn = conn;
		this.model = new ClientModel();
		this.view = new ClientView(model);
		this.controller = new ClientController(model, view);
		
		model.addObserver(this.controller);
		view.setVisible(true);
		
	}
	
	public void run() {
		
		while(running) {
			synchronized (this.taskList) {
				if (this.taskList.isEmpty()) {
					try {
						taskList.wait();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}
		
		Command task = taskList.getNextTask();
		task.execute(model);
		}
	}
	
	public void stop(){
		this.running = false;
	}
}
