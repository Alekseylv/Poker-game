package commands;

import client.ClientGame;
import client.State;

/**
 * Notifies Client that the server is expecting input from
 * the user and that Check option is allowed.
 * @author Aleksey
 *
 */

public class FRCheckCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7563953177228213112L;

	public FRCheckCommand() {
		
	}
	
	public void execute(ClientGame game) {
		game.model.changeState(State.INPUTCHECK);
	}

}

