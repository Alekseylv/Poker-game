package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import poker.arturka.Card;

public class ClientModel extends Observable {

	private Card mycards[];
	private Card fieldcards[];
	private State state;
	private List<ClientSidePlayer> players;	
	private int id;
	
	public ClientModel() {
		this.mycards = new Card[2];
		this.fieldcards = new Card[5];
		this.state = State.READY;
		this.players = new ArrayList<ClientSidePlayer>();
	}
	
	public void setPlayerList(List<ClientSidePlayer> players) {
		this.players = players;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
		
		setChanged();
        notifyObservers(this.id);
	}
	
	public Card[] getMyCards() {
		for(ClientSidePlayer i: players) {
			if(i.getId() == id) {
				return i.g
			}
		}
	}
	
	public void setMyField(Card card1, Card card2) {
		mycards[1] = card1;
		mycards[2] = card2;
		
		setChanged();
        notifyObservers(mycards);
	}
	
	public Card[] getFieldCards() {
		return fieldcards.clone();
	}
	
	public void changeFieldCards(Card newField[]) {
		assert(newField.length == 5);
		this.fieldcards = newField;
		
		setChanged();
        notifyObservers(fieldcards);
	}
	
	public State getState() {
		return this.state;
	}
	
	public void changeState(State newState) {
		this.state = newState;
		setChanged();
        notifyObservers(state);
        
	}
	
	
}
