package client;

import message.data.ClientTurn;
import message.data.Card;
import message.data.Player;

/**
 * Extends the server side player and adds additional functionality
 * @author Aleksey
 * @see Player
 */

public class ClientSidePlayer extends Player {

	/**
	 * the last turn performed by this player
	 */
	private ClientTurn lastTurn;
	
	/**
	 * Constructs a client side player from a server side player
	 * @param player
	 *  server side player to construct from
	 */
	public ClientSidePlayer(Player player) {
		super(player.getId(),player.getNick());
		super.setBet(player.getBet());
		super.setCash(player.getCash());
		Card[] hand = player.getHand();
		super.giveCards(hand[0], hand[1]);
		if(player.isDealer()) super.toggleDealer();
		if(!player.isInGame()) super.toggleInGame();
		if(player.hasFolded()) super.Fold();
		super.setBet(player.getBet());		
		this.lastTurn = null;
	}
	
	/**
	 * Notifies any observers about changes on this object
	 * @param o
	 *  The parameter that changed
	 */
	
	private void notifyObs(Object o) {
		setChanged();
        notifyObservers(o);
	}
	
	/**
	 * Set's the last turn of this player and notifies observer
	 * @param turn
	 *  The new turn to be set
	 */
	
	public void setLastTurn(ClientTurn turn) {
		this.lastTurn = turn;
		
		notifyObs(turn);
	}
	
	/**
	 * Get's the last turn
	 * @return
	 *  the last client turn
	 */
	
	public ClientTurn getLastTurn() {
		return this.lastTurn;
	}
	
	
	/**
	 * Adds more cash to the player and notifies observers
	 * @param cash
	 *  the amount to add
	 */
	
	public void giveCash(int cash){
        super.giveCash(cash);
        
        notifyObs(cash);
    }
	
	/**
	 * Set's the hand held cards of this player
	 * @param card1
	 *  first card
	 * @param card2
	 *  second card
	 */
	public void giveCards(Card card1, Card card2){
       super.giveCards(card1, card2);
       
       notifyObs(card1);
    }
	
	/**
	 * Sets the cash of this player to a new value
	 * and notifies Observers
	 * @param cash
	 *  the new amount of cash the player has
	 */
	
	public void setCash(int cash) {
        super.setCash(cash);
        
        notifyObs(cash);
    }
	
	/**
	 * toggles the dealer state in the object
	 * and notifies Observers
	 */
	
	public void toggleDealer() {
        super.toggleDealer();
        
        notifyObs(true);
    }
	
	/**
	 * Turns this player into a folded state
	 * and notifies Observers
	 */

	public void Fold() {
		super.Fold();
	    notifyObs(true);
	}
	
	/**
	 * Turns this player into a normal state
	 * and notifies Observers
	 */
	
	public void unFold() {
		super.unFold();
	    notifyObs(true);
	}
	
	/**
	 * Set's the Bet of this player
	 * and notifies Observers
	 * @param bet
	 *  the new bet
	 */

    public void setBet(int bet){
        super.setBet(bet);
        
        notifyObs(bet);
    }
    
    /**
     * Sets bet, last turn and cash of this player and notifies observers 
     * @param currentBet
     *  the new bet
     * @param turn
     *  the new last turn
     * @param moneyLeft
     *  the new cash amount this player has
     */

	public void setBetTurnCash(int currentBet, ClientTurn turn, int moneyLeft) {
		super.setBet(currentBet);
		super.setCash(moneyLeft);
		this.setLastTurn(turn);	
	}
	
}
