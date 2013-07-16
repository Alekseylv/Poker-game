package client;

import java.util.Observable;
import java.util.Observer;

import poker.GUI.ClientView;

public class ClientController implements Observer {

	private ClientModel model;
	private ClientView view;
	
	public ClientController(ClientModel model, ClientView view) {
		this.model = model;
		this.view = view;
	}
	
	public void update(Observable obj, Object arg) {
		
	}
}
