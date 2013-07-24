package commands;

import client.ClientController;
import client.ClientGame;
import client.ClientModel;

/**
 * Changes cash amount on a player in clients list of players
 * idenitfied by id
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class ChangeCashCommand implements Command {
	
	private int id;
	private int newCash;
	/**
	 * Creates a new command
	 * @param id 
	 * 	of the player whose cash amount must be changed
	 * @param newCash
	 *  the new amount of cash of this player
	 */
	
	public ChangeCashCommand(int id, int newCash) {
		this.id = id;
		this.newCash = newCash;
	}
	
	public void execute(ClientGame game) {
		game.model.getPlayer(id).setCash(newCash);
	}

}
