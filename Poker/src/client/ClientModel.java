package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import poker.arturka.Card;

public class ClientModel extends Observable {

	private Card fieldcards[];
	private State state;
	private List<ClientSidePlayer> players;	
	private int id;
	
	public ClientModel() {
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
	
	public void setCards(int id, Card card1, Card card2) {
		for(ClientSidePlayer i: players) {
			if(i.getId() == id) {
				i.giveCards(card1, card2);
			}
		}
	}
	
	public Card[] getCards(int idt) {
		for(ClientSidePlayer i: players) {
			if(i.getId() == idt) {
				return i.getHand();
			}
		}
		
		return null;
	}
	
	public Card[] getMyCards() {
		return this.getCards(this.id);
	}
	
	public void setMyField(Card card1, Card card2) {
		this.setCards(id, card1, card2);
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
