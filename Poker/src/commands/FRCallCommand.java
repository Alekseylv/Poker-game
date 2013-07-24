package commands;

import client.ClientController;
import client.ClientGame;
import client.ClientModel;
import client.State;

/**
 * Notifies Client that the server is expecting input from
 * the user and that Call option is allowed.
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class FRCallCommand implements Command {

	public FRCallCommand() {
		
	}
	
	public void execute(ClientGame game) {
		game.model.changeState(State.INPUTCALL);
	}

}
