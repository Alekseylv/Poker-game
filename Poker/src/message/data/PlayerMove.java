package message.data;

import java.io.Serializable;

/**
 * Used as part of a command to update player state on client side,
 * represents changes in other player to a client
 * @author Aleksey
 *
 */

public class PlayerMove implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2343633951072917236L;
	public final int id;
	public final ClientTurn turn;
	public final int currentBet;
	public final int moneyLeft;
	
	/**
	 * 
	 * @param id the id of changed player
	 * @param turn the last turn taken by this player
	 * @param currentBet the current bet this player has
	 * @param moneyLeft amount of money this player has
	 */
	public PlayerMove(int id, ClientTurn turn, int currentBet, int moneyLeft) {
		this.id = id;
		this.turn = turn;
		this.currentBet = currentBet;
		this.moneyLeft = moneyLeft;
	}
}
