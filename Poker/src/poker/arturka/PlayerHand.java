package poker.arturka;

import message.data.Card;
import message.data.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerHand {

	public PlayerHand(Player player) {
		this.player = player;
		kicker = null;
		highCard = null;
		handScore = 0;
		setPosition(0);
	}

	public Player getPlayer() {
		return player;
	}

	public int getHandScore() {
		return Integer.valueOf(handScore);
	}

	public void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	public List<Card> getPlayerHand() {
		if (playerHand != null) {
			List<Card> tempHand = new ArrayList<Card>();
			tempHand.addAll(playerHand);
			return tempHand;
		}
		return null;
	}

	public void setPlayerHand(Card[] playerHand) {
		for (Card card : playerHand) {
			this.playerHand.add(card);
		}
	}
	
	public void setPlayerHand(List<Card> playerHand) {
		if (playerHand != null)
			this.playerHand.addAll(playerHand);
	}

	public Card getHighCard() {
		if (highCard != null) {
			Card tempHighCard = new Card(highCard.getSuit(), highCard.getRank());
			return tempHighCard;
		}
		return null;
	}

	public void setHighCard(Card highCard) {
		this.highCard = highCard;
	}

	public Card getKicker() {
		if (kicker != null) {
			Card temp = new Card(kicker.getSuit(), kicker.getRank());
			return temp;
		}
		return null;
	}

	public void setKicker(Card kicker) {
		this.kicker = kicker;
	}

	public Hand getHand() {
		return hand;
	}

	public Hand setHand(Hand hand) {
		return this.hand = hand;
	}
	
	public int getPosition() {
		return Integer.valueOf(position);
	}

	public int setPosition(int position) {
		return this.position = position;
	}

	private List<Card> playerHand;
	private int handScore;
	private Hand hand;
	private Card highCard;
	private Card kicker;
	private Player player;
	private int position;
}
