package commands;

import client.ClientModel;

public interface Command {

	public void execute(ClientModel model);
}
