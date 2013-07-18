package commands;

import message.data.PlayerMove;
import client.ClientController;
import client.ClientModel;
import client.ClientSidePlayer;

@SuppressWarnings("serial")
public class PlayerMoveCommand implements Command {

	private PlayerMove move;
	
	public PlayerMoveCommand(PlayerMove move) {
		this.move = move;
	}
	
	public void execute(ClientModel model, ClientController controller) {
		ClientSidePlayer player = model.getPlayer(move.id);
		player.setBetTurnCash(move.currentBet, move.turn, move.moneyLeft);
	}
}
