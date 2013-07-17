package commands;

import java.io.Serializable;
import client.ClientModel;

public interface Command extends Serializable {

	public void execute(ClientModel model);
}
