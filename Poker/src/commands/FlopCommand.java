package commands;

import poker.arturka.Card;
import client.ClientModel;

public class FlopCommand implements Command {

	private Card card1;
	private Card card2;
	private Card card3;

	
	
	public FlopCommand(Card card1, Card card2, Card card3) {
		this.card1 = card1;
		this.card2 = card1;
		this.card3 = card1;
	}
	
	public void execute(ClientModel model) {
		// implement Flop here
	}
	
}
