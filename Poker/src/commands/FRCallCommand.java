package commands;

import client.ClientGame;
import client.State;

/**
 * Notifies Client that the server is expecting input from
 * the user and that Call option is allowed.
 * @author Aleksey
 *
 */

public class FRCallCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4007695118305319542L;

	public FRCallCommand() {
		
	}
	
	public void execute(ClientGame game) {
		game.model.changeState(State.INPUTCALL);
	}

}
