package commands;

import client.ClientController;
import client.ClientModel;

public class SetIDCommand implements Command {

	private int id;
	
	public SetIDCommand(int id) {
		this.id = id;
	}
	
	public void execute(ClientModel model, ClientController controller) {
		model.setID(this.id);
	}

}
