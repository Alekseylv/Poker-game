package commands;

import client.ClientController;
import client.ClientModel;

@SuppressWarnings("serial")
public class ChangeCashCommand implements Command {

	private int id;
	private int newCash;
	
	public ChangeCashCommand(int id, int newCash) {
		this.id = id;
		this.newCash = newCash;
	}
	
	public void execute(ClientModel model, ClientController controller) {
		model.getPlayer(id).setCash(newCash);
	}

}
