package commands;

import client.ClientController;
import client.ClientGame;
import client.ClientModel;

/**
 * Changes dealer in player list of a client
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class ChangeDealersCommand implements Command {

	private int idOld;
	private int idNew;
	
	/**
	 * Creaetes a command
	 * @param idOld - id of the old dealer
	 * @param idNew - id of the new dealer
	 */
	public ChangeDealersCommand(int idOld, int idNew) {
		this.idNew = idNew;
		this.idOld = idOld;
	}
	
	public void execute(ClientGame game) {
		game.model.getPlayer(idOld).toggleDealer();
		game.model.getPlayer(idNew).toggleDealer();
	}
}
