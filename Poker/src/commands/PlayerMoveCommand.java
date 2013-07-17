package commands;

import message.data.PlayerMove;
import client.ClientController;
import client.ClientModel;

public class PlayerMoveCommand implements Command {

	private PlayerMove move;
	
	public PlayerMoveCommand(PlayerMove move) {
		this.move = move;
	}
	
	public void execute(ClientModel model, ClientController controller) {
		// implement PlayerMoveCommand here
	}
}
