package commands;

import message.data.Card;
import client.ClientController;
import client.ClientGame;
import client.ClientModel;

/**
 * Command to open the first three cards on the table
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class FlopCommand implements Command {

	private Card card1;
	private Card card2;
	private Card card3;
	
	/**
	 * Creates the flop command
	 * @param card1 
	 * @param card2
	 * @param card3
	 */
	
	public FlopCommand(Card card1, Card card2, Card card3) {
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
	}
	

	public void execute(ClientGame game) {
		Card[] fieldcards = {card1, card2, card3, null, null};
		game.model.bet.setOldMaxBet(game.model.getMaxBet());
		game.model.changeFieldCards(fieldcards);	
	}
	
}
