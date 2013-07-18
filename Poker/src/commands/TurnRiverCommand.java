package commands;

import client.ClientController;
import client.ClientModel;
import poker.arturka.Card;

/**
 * Opens 4th or 5th card on the table depending on
 * the RorT enumerator 
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class TurnRiverCommand implements Command {

	private Card card4;
	private RorT cmd;
	
	/**
	 * TURN - opens 4th card
	 * RIVER - opens 5th card
	 * @author Aleksey
	 *
	 */
	public enum RorT {
		TURN,
		RIVER;
	}
	/**
	 * Creates the Command
	 * @param card4 - the card to be set
	 * @param cmd - the flag to determine card to be opened
	 */
	public TurnRiverCommand(Card card4, RorT cmd) {
		this.card4 = card4;
		this.cmd = cmd;
	}
	
	public void execute(ClientModel model, ClientController controllers) {
		Card[] fieldcards = model.getFieldCards();
		
		switch (cmd) {
			case TURN: {
				fieldcards[3] = card4;
				break;
			}
			case RIVER: {
				fieldcards[4] = card4;
				break;
			}
		}
		
		model.changeFieldCards(fieldcards);
	}
}
