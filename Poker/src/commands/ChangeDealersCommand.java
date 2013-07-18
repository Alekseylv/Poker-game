package commands;

import client.ClientController;
import client.ClientModel;

@SuppressWarnings("serial")
public class ChangeDealersCommand implements Command {

	private int idOld;
	private int idNew;
	
	public ChangeDealersCommand(int idOld, int idNew) {
		this.idNew = idNew;
		this.idOld = idOld;
	}
	
	public void execute(ClientModel model, ClientController controller) {
		model.getPlayer(idOld).toggleDealer();
		model.getPlayer(idNew).toggleDealer();
	}
}
