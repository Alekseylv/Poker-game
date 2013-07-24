package commands;

import client.ClientController;
import client.ClientGame;
import client.ClientModel;
import client.State;

/**
 * Notifies Client that the server is expecting input from
 * the user and that Check option is allowed.
 * @author Aleksey
 *
 */

@SuppressWarnings("serial")
public class FRCheckCommand implements Command {

	public FRCheckCommand() {
		
	}
	
	public void execute(ClientGame game) {
		game.model.changeState(State.INPUTCHECK);
	}

}

