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
	
	public void update(Observable obj, Object arg) {
		if(arg instanceof Card[]) {
			Card[] cards = (Card[]) arg;
			
			if(cards.length == 2) {
				// update held cards
			} else if(cards.length == 5) {
				// update field cards
			} else {
				System.out.println("Card[] length uncorrect");
			}
			
			
		} else if(arg instanceof Integer) {
			// update cash
		} else if(arg instanceof State) {
			// do something with state logic
		} else 
			System.out.println("arg is weird");
	}
}
