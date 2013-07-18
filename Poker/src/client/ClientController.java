package client;

import java.util.List;
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
			// rewrite Deck cards
		} else if(arg instanceof State) {
			if(model.getState() == State.READY){

            }
		} else if(arg instanceof Integer) {
			// we just got an id
		} else if(arg instanceof List) {
			// we just got player list
		}
			
			
	/*	private Card fieldcards[];
		private State state;
		private List<ClientSidePlayer> players;	
		private int id;
	*/
	}
	
	public void update(ClientSidePlayer player, Object arg) {
		// rewrite concrete player on screen
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
