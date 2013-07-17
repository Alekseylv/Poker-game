package client;

import java.util.Observable;
import java.util.Observer;

import poker.GUI.ClientView;
import poker.arturka.Card;

public class ClientController implements Observer {

	private ClientModel model;
	private ClientView view;
	
	public ClientController(ClientModel model, ClientView view) {
		this.model = model;
		this.view = view;
	}
	
	public void update(ClientModel model, Object arg) {
		if(arg instanceof Card[] ) {
			
		}
			
			
	/*	private int id;
	    private int cash;
	    private Card[] hand;
	    private boolean dealer;
	    private boolean fold;
	    private int bet;
	    private boolean inGame; 
	*/
	}
	
	public void update(ClientSidePlayer player, Object arg) {
		
	}
	
	public void update(Observable obj, Object arg) {
		if(obj instanceof ClientSidePlayer) {
			this.update((ClientSidePlayer) obj, arg);
		} else if(obj instanceof ClientModel) {
			this.update((ClientModel) obj, arg);
		} else {
			System.out.println("Not a valid object" + obj);
		}
	}
}
