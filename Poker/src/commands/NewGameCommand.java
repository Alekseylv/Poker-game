package commands;

import client.ClientController;
import client.ClientModel;
import client.State;

@SuppressWarnings("serial")
public class NewGameCommand implements Command {

	
	public void execute(ClientModel model, ClientController controller) {
		model.changeState(State.PLAYING);
	}

}
