package commands;

import java.io.Serializable;

import client.ClientGame;
/**
 * Interface of the Command, all commands must have an execute
 * method that can be used by the Client
 * @author Aleksey
 *
 */
public interface Command extends Serializable {

	public void execute(ClientGame game);
}
