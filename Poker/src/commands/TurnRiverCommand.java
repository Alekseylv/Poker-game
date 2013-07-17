package commands;

import client.ClientModel;
import poker.arturka.Card;

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
	
	public void execute(ClientModel model) {
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
