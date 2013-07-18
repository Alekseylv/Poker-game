package message.data;

import java.io.Serializable;

/**
 * Represents a client response holding all necessary 
 * information, meant to be used by sending over a network
 * in a serialized way.
 * @author Aleksey
 *
 */

@SuppressWarnings("serial")
public class ClientResponse implements Serializable {
	
	/**
	 * turn: the turn taken by Client
	 * optional: the bet (optional)
	 */
	public final ClientTurn turn;
	private int optional;
	
	/**
	 * Constructs a client response
	 * @param turn
	 *  Turn taken by player
	 * @param optional
	 *  optionally the bet
	 */
	
	public ClientResponse(ClientTurn turn, int optional) {
		this.turn = turn;
		this.optional = optional;
	}
	
	/**
	 * Get's the bet field ( -1 if error)
	 * @return bet
	 */
	
	public int getBet() {
		return optional;
	}
}
