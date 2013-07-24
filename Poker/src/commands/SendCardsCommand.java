package commands;

import message.data.Card;
import client.ClientController;
import client.ClientModel;

/**
 * Sends the hand held cards of a player to Client
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class SendCardsCommand implements Command {

	private final int id;
	private final Card card1;
	private final Card card2;
	
	/**
	 * Creates a command
	 * @param id - id of the player
	 * @param card1 - first card
	 * @param card2 - second card
	 */
	public SendCardsCommand(int id, Card card1, Card card2) {
		this.card1 = card1;
		this.card2 = card2;
		this.id = id;
	}
	
	@Override
	public void execute(ClientModel model, ClientController controller) {
		model.getPlayer(id).giveCards(card1, card2);
	}

}
