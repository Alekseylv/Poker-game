package commands;

import client.ClientModel;
import poker.arturka.Card;

public class TurnRiverCommand implements Command {

	private Card card4;
	private RorT cmd;
	
	public enum RorT {
		Turn,
		River;
	}
	
	public TurnRiverCommand(Card card4, RorT cmd) {
		this.card4 = card4;
		this.cmd = cmd;
	}
	
	public void execute(ClientModel model) {
		// implement TurnOrRiver here
	}
}
