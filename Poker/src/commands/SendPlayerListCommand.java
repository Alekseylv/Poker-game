package commands;

import java.util.ArrayList;
import java.util.List;

import poker.arturka.Player;
import client.ClientController;
import client.ClientModel;
import client.ClientSidePlayer;

@SuppressWarnings("serial")
public class SendPlayerListCommand implements Command {

	private List<Player> list;
	
	public SendPlayerListCommand(List <Player>list) {
		this.list = list;
	}
	
	public void execute(ClientModel model, ClientController controller) {
		List<ClientSidePlayer> playList = new ArrayList<ClientSidePlayer>();
		
		for(Player i: list) {
			ClientSidePlayer tempPlayer = new ClientSidePlayer(i);
			tempPlayer.addObserver(controller);
			playList.add(tempPlayer);
		}
		
		model.setPlayerList(playList);	
	}

}
