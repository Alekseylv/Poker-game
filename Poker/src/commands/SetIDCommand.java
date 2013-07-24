package commands;

import client.ClientController;
import client.ClientGame;
import client.ClientModel;

/**
 * Set's id of a client
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class SetIDCommand implements Command {

	private int id;
	/**
	 * Creates command
	 * @param id - the id
	 */
	public SetIDCommand(int id) {
		this.id = id;
	}
	
	public void execute(ClientGame game) {
		game.model.setID(this.id);
	}

}
