package commands;

import client.ClientController;
import client.ClientModel;
import poker.arturka.Card;

@SuppressWarnings("serial")
public class TurnRiverCommand implements Command {

	private Card card4;
	private RorT cmd;
	
	public enum RorT {
		TURN,
		RIVER;
	}
	
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
