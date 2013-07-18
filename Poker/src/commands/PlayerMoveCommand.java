package commands;

import message.data.PlayerMove;
import client.ClientController;
import client.ClientModel;
import client.ClientSidePlayer;

/**
 * Notifies Client that a player has made a move and gives
 * new data of it
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class PlayerMoveCommand implements Command {

	private PlayerMove move;
	
	/**
	 * Makes the command
	 * @param move - the new state of a player
	 */
	public PlayerMoveCommand(PlayerMove move) {
		this.move = move;
	}
	
	public void execute(ClientModel model, ClientController controller) {
		ClientSidePlayer player = model.getPlayer(move.id);
		player.setBetTurnCash(move.currentBet, move.turn, move.moneyLeft);
	}
}
