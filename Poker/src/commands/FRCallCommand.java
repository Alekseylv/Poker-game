package commands;

import client.ClientController;
import client.ClientModel;
import client.State;

@SuppressWarnings("serial")
public class FRCallCommand implements Command {

	public FRCallCommand() {
		
	}
	
	public void execute(ClientModel model, ClientController controller) {
		model.changeState(State.INPUTCALL);
	}

}
