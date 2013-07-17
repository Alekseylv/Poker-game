package commands;

import client.ClientController;
import client.ClientModel;
import client.State;

public class FRCheckCommand implements Command {

	public FRCheckCommand() {
		
	}
	
	public void execute(ClientModel model, ClientController controller) {
		model.changeState(State.INPUTCHECK);
	}

}

