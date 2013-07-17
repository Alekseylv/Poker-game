package commands;

import poker.arturka.Card;
import client.ClientController;
import client.ClientModel;

public class FlopCommand implements Command {

	private Card card1;
	private Card card2;
	private Card card3;

	
	
	public FlopCommand(Card card1, Card card2, Card card3) {
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
	}
	

	public void execute(ClientModel model, ClientController controller) {
		Card[] fieldcards = {card1, card2, card3, null, null};
		model.changeFieldCards(fieldcards);	
	}
	
}
