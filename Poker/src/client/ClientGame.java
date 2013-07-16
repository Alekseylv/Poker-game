package client;

import poker.GUI.ClientView;

public class ClientGame implements Runnable {

	private Conn conn;
	private ClientModel model;
	private ClientView view;
	private ClientController controller;
	
	public ClientGame(Conn conn) {
		
		this.model = new ClientModel();
		this.view = new ClientView(model);
		this.controller = new ClientController(model, view);
		
		this.conn = conn;
		this.model = model;
		this.view = view;
		this.controller = controller;
		
	}
	
	public void run() {
		
	}
}
