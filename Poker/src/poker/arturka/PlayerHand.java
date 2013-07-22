package poker.arturka;

import java.util.ArrayList;
import java.util.List;

public class PlayerHand {

	private List<Card> playerHand;
	private int handScore;
	private Hand hand;
	private Card highCard;
	private Card kicker;
	
	public PlayerHand(Hand hand, List<Card> scoreCards) {
		this.setHand(hand);
		this.playerHand = scoreCards;
	}
	
	public int getHandScore() {
		return Integer.valueOf(handScore);
	}

	public void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	public List<Card> getPlayerHand() {
		List<Card> tempHand = new ArrayList<Card>();
		tempHand.addAll(playerHand);
		return tempHand;
	}

	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}

	public Card getHighCard() {
		Card tempHighCard = new Card(highCard.getSuit(), highCard.getRank());
		return tempHighCard;
	}

	public void setHighCard(Card highCard) {
		this.highCard = highCard;
	}

	public Card getKicker() {
		Card temp = new Card(kicker.getSuit(), kicker.getRank());
		return temp;
	}

	public void setKicker(Card kicker) {
		this.kicker = kicker;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
}
