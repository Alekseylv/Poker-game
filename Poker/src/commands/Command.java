package commands;

import java.io.Serializable;

import client.ClientController;
import client.ClientModel;
/**
 * Interface of the Command, all commands must have an execute
 * method that can be used by the Client
 * @author Aleksey
 *
 */
public interface Command extends Serializable {

	public void execute(ClientModel model, ClientController controller);
}
