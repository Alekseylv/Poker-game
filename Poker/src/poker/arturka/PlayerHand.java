package poker.arturka;

import message.data.Card;
import message.data.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerHand {

	/**
	 * Creates an instance of a PlayerHand that will hold attributes for player
	 * hand of current moot.
	 * 
	 * @param player
	 *            Player class object who is connected to the room and whose
	 *            hand needs to be checked.
	 */
	public PlayerHand(Player player) {
		/* Constructor initialized instance variables. */
		this.player = player;
		playerHand = new ArrayList<Card>();
		kicker = null;
		highCard = null;
		handScore = 0;
		handScore2 = 0;
		setPosition(0);
	}

	public int getHandScore2() {
		return Integer.valueOf(handScore2);
	}

	public void setHandScore2(int handScore2) {
		this.handScore2 = handScore2;
	}

	/**
	 * Returns player object, that holds this hand.
	 * 
	 * @return Returns hand holder.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Returns score for further hand comparison.
	 * 
	 * @return Returns score of current combination.
	 */
	public int getHandScore() {
		return Integer.valueOf(handScore);
	}

	/**
	 * Assigns evaluated hand score to class object variable which is used for
	 * one hand type comparison.
	 * 
	 * @param handScore
	 *            Evaluated score of the hand.
	 */
	public void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	/**
	 * Gets the current player hand as a list of cards. If the player hand has
	 * not been initialized, null value is returned.
	 * 
	 * @return Returns copy of the player hand list.
	 */
	public List<Card> getPlayerHand() {
		if (playerHand != null) {
			List<Card> tempHand = new ArrayList<Card>();
			tempHand.addAll(playerHand);
			return tempHand;
		}
		return null;
	}

	/**
	 * Assigns new value to the global variable List<Card> playerHand.
	 * 
	 * @param playerHand
	 *            Card array that holds player cards.
	 */
	public void setPlayerHand(Card[] playerHand) {
		for (Card card : playerHand) {
			this.playerHand.add(card);
		}
	}

	/**
	 * Assigns new value to the global variable List<Card> playerHand.
	 * 
	 * @param playerHand
	 *            Card list that holds player cards.
	 */
	public void setPlayerHand(List<Card> playerHand) {
		if (playerHand != null)
			this.playerHand.addAll(playerHand);
	}

	/**
	 * Gets copy of highCard that is the highest ranked Card of player's hand
	 * which is not included in any combination.
	 * 
	 * @return Returns the highest ranked card of player hand.
	 */
	public Card getHighCard() {
		if (highCard != null) {
			Card tempHighCard = new Card(highCard.getSuit(), highCard.getRank());
			return tempHighCard;
		}
		return null;
	}

	/**
	 * Sets highCard value.
	 * 
	 * @param highCard
	 *            Card that needs to be assigned to highCard variable.
	 */
	public void setHighCard(Card highCard) {
		this.highCard = highCard;
	}

	/**
	 * Returns a Card which is highest rank leftover of the hand.
	 * 
	 * @return Returns kicker, Card needed to compare similar hands.
	 */
	public Card getKicker() {
		if (kicker != null) {
			Card temp = new Card(kicker.getSuit(), kicker.getRank());
			return temp;
		}
		return null;
	}

	/**
	 * Sets the kicker value, Card that is the highest rank leftover in the
	 * hand.
	 * 
	 * @param kicker
	 *            The new kicker value.
	 */
	public void setKicker(Card kicker) {
		this.kicker = kicker;
	}

	/**
	 * Returns Hand enumerator as a player hand type.
	 * 
	 * @return Returns player combination hand.
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * Assigns and returns the new player hand value.
	 * 
	 * @param hand
	 *            The new enumerator type hand value.
	 * @return Returns hand value.
	 */
	public Hand setHand(Hand hand) {
		return this.hand = hand;
	}

	/**
	 * Gets current hand ranking of current moot.
	 * 
	 * @return Returns hand (and player) position of current moot.
	 */
	public int getPosition() {
		return Integer.valueOf(position);
	}

	/**
	 * Sets and returns value of new current hand position in moot context.
	 * 
	 * @param position
	 *            The new hand position value.
	 * @return Newly assigned position value.
	 */
	public int setPosition(int position) {
		return this.position = position;
	}

	/* Private instance variables */
	private List<Card> playerHand;
	private int handScore;
	private int handScore2;
	private Hand hand;
	private Card highCard;
	private Card kicker;
	private Player player;
	private int position;
}