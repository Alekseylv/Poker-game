package commands;

import message.data.ClientTurn;
import message.data.PlayerMove;
import client.ClientController;
import client.ClientGame;
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
	
	public void execute(ClientGame game) {
		if(move.turn == ClientTurn.BLIND && move.currentBet > game.model.getBlind()) {
			game.model.setBlind(move.currentBet);
		} else if(game.model.bet.getBet() < move.currentBet - game.model.bet.getOldMaxBet()){
			game.model.bet.setBet(move.currentBet - game.model.bet.getOldMaxBet());
		}
		
		
		game.model.getPlayer(move.id).setBetTurnCash(move.currentBet,
													move.turn, move.moneyLeft);
	}
}
