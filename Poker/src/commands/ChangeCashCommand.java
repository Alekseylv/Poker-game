package commands;

import client.ClientGame;

/**
 * Changes cash amount on a player in clients list of players
 * idenitfied by id
 * @author Aleksey
 *
 */
public class ChangeCashCommand implements Command {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3610987762357426791L;
	/**
	 * 
	 */
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
