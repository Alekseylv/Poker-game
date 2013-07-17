package commands;

import client.ClientModel;

public class SetIDCommand implements Command {

	private int id;
	
	public SetIDCommand(int id) {
		this.id = id;
	}
	
	public void execute(ClientModel model) {
		model.setID(id);
	}

}
