package client;

import message.data.ClientTurn;
import poker.arturka.Player;



public class ClientSidePlayer extends Player {

	private ClientTurn lastTurn;
	
	public ClientSidePlayer(Player player) {
		super(player.getId());
		this.lastTurn = null;
	}
	
	public void setLastTurn(ClientTurn turn) {
		this.lastTurn = turn;
	}
	
	public ClientTurn getLastTurn() {
		return this.lastTurn;
	}

}
