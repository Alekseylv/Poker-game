package commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import client.ClientController;
import client.ClientGame;
import client.ClientModel;
import client.State;

/**
 * Sends information about winners and the amount of cash the have won
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class SendWinnerListCommand implements Command {

	private final List<Tuple> winnerList;
	/**
	 * Creates Command
	 * @param list - info about winners (id's and cash)
	 */
	public SendWinnerListCommand(List<Tuple> list) {
		this.winnerList = list;
	}
	/**
	 * Used to represent relevant winner information
	 * @author Aleksey
	 *
	 */
	
	public static class Tuple implements Serializable{
		public final int id;
		public final int cash;
		
		public Tuple(int id, int cash) {
			this.id = id;
			this.cash = cash;
		}
	}

	
	public void execute(ClientGame game) {	
		game.view.broadcastWinner((ArrayList<Tuple>)winnerList);
	}
}
