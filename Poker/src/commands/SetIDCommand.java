package commands;

import client.ClientGame;

/**
 * Set's id of a client
 * @author Aleksey
 *
 */
public class SetIDCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6807506502828907080L;
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
