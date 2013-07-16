package client;

import java.util.Observable;

import poker.arturka.Card;

public class ClientModel extends Observable {

	private Card mycards[];
	private Card fieldcards[];
	private state state;
	private int cash;
	
	
	public ClientModel() {
		this.mycards = new Card[2];
		this.fieldcards = new Card[5];
		this.state = state.READY;
	}
	
	public void setCash(int newCash) {
		this.cash = newCash;
		
		setChanged();
        notifyObservers(mycards);
	}
	
	public int getCash() {
		return this.cash; 
	}
	
	public Card[] getMyCards() {
		return mycards.clone();
	}
	
	public void setMyField(Card card1, Card card2) {
		mycards[1] = card1;
		mycards[2] = card2;
		
		setChanged();
        notifyObservers(mycards);
	}
	
	public void changeFieldCards(Card newField[]) {
		assert(newField.length == 5);
		this.fieldcards = newField;
		
		setChanged();
        notifyObservers(fieldcards);
	}
	
	public state getState() {
		return this.state;
	}
	
	public void changeState(state newState) {
		this.state = newState;
		setChanged();
        notifyObservers(state);
	}
	
	
}
