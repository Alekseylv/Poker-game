package commands;

import java.util.ArrayList;
import java.util.List;

import poker.arturka.Player;
import client.ClientModel;
import client.ClientSidePlayer;

public class SendPlayerListCommand implements Command {

	private List<Player> list;
	
	public SendPlayerListCommand(List <Player>list) {
		this.list = list;
	}
	
	public void execute(ClientModel model) {
		List<ClientSidePlayer> playList = new ArrayList<ClientSidePlayer>();
		for(Player i: list) {
			playList.add(new ClientSidePlayer(i));
		}
		
		model.setPlayerList(playList);	
	}

}
