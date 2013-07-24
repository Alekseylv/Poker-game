package commands;

import java.util.ArrayList;
import java.util.List;

import message.data.Card;
import message.data.Player;
import client.ClientGame;
import client.ClientSidePlayer;
import client.State;

/**
 * Sends list of all players to a Client
 * @author Aleksey
 *
 */

public class SendPlayerListCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2365690181021688137L;
	private List<Player> list;
	/**
	 *  Creates the command
	 * @param list - player list
	 */
	public SendPlayerListCommand(List <Player>list) {
		this.list = list;
	}
	
	public void execute(ClientGame game) {
		List<ClientSidePlayer> playList = new ArrayList<ClientSidePlayer>();
		
		for(Player i: list) {
			ClientSidePlayer tempPlayer = new ClientSidePlayer(i);
			tempPlayer.addObserver(game.controller);
			playList.add(tempPlayer);
		}
		
		game.model.setPlayerList(playList);	
		game.model.changeState(State.PLAYING);
		Card[] newField = new Card[] {null, null, null, null, null};
		game.model.changeFieldCards(newField);
	}

}
