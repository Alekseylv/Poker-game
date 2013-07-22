package poker.arturka;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import poker.arturka.Card.Rank;
import poker.arturka.Card.Suit;
import poker.arturka.Hand;

public class HandEvaluator {

	private static final int CARDS_TO_EVALUATE = 5;
	private static final int FOUR_OF_A_KIND_COUNT = 4;
	private static final int THREE_OF_A_KIND_COUNT = 3;
	private static final int TWO_PAIR_COUNT = 2;
	private static final int ONE_PAIR_COUNT = 1;

	public HandEvaluator(List<Player> playersLeft) {
		playersToEvaluate = playersLeft;
		evaluatedPlayers = new HashMap<Player, PlayerHand>();
	}

	public HashMap<Integer, PlayerHand> getPlayerHandEvaluation() {
		HashMap<Integer, PlayerHand> playerRanking = new HashMap<Integer, PlayerHand>();
		for (Player player : playersToEvaluate) {
			// getPlayerHand(player);
			Hand hand = getPlayerHand(player);
			playerHand = new PlayerHand(hand, scoreCards, player);
			evaluatedPlayers.put(player, playerHand);
		}
		playerRanking = sortEvaluatedPlayers();
		return playerRanking;
	}

	private Card[][] createHandTable(Card[] hand) {
		Card[][] tempHand = new Card[Suit.values().length][Rank.values().length];
		for (Card card : hand) {
			tempHand[card.getSuit().ordinal()][card.getRank().ordinal()] = card;
		}
		return tempHand;
	}

	private HashMap<Integer, PlayerHand> sortEvaluatedPlayers() {
		//swapEntries(prev, entry);
		int incremental = 0;
		HashMap<Integer, PlayerHand> playerPositions = new HashMap<Integer, PlayerHand>();
		for (Entry<Player, PlayerHand> entry : evaluatedPlayers.entrySet()) {
			playerPositions.put(entry.getValue().getHand().ordinal(), entry.getValue());
		}
		return playerPositions;
	}

	private void swapEntries(Entry<Integer, PlayerHand> prev, Entry<Player, PlayerHand> entry) {
		entry.setValue(prev.setValue(entry.getValue()));
	}

	private Hand getPlayerHand(Player player) {
		return getHand(player.getHand());
	}

	public Hand getHand(Card[] hand) {
		// In method calls have to set scoreCards!
		combination = createHandTable(hand);
		if (handIsRoyalFlush(combination))
			return Hand.ROYAL_FLUSH;
		else if (handIsStraightFlush(combination))
			return Hand.STRAIGHT_FLUSH;
		else if (handIsSameKind(combination, FOUR_OF_A_KIND_COUNT))
			return Hand.FOUR_OF_A_KIND;
		else if (handIsFullHouse(combination))
			return Hand.FULL_HOUSE;
		else if (handIsFlush(combination))
			return Hand.FLUSH;
		else if (handIsStraight(combination))
			return Hand.STRAIGHT;
		else if (handIsSameKind(combination, THREE_OF_A_KIND_COUNT))
			return Hand.THREE_OF_A_KIND;
		else if (handIsTwoPair(combination))
			return Hand.TWO_PAIR;
		else if (handIsSameKind(combination, ONE_PAIR_COUNT))
			return Hand.ONE_PAIR;
		else
			return Hand.HIGH_HAND;
	}

	private boolean handIsTwoPair(Card[][] combination2) {
		int skCount = 0;
		for (int i = Rank.values().length - 1; i > -1; i--) {
			for (int j = 0; j < Suit.values().length; j++) {
				if (combination2[Suit.values().length - j - 1][i] != null) {
					skCount++;
				}
				if (skCount == TWO_PAIR_COUNT)
					return true;
			}
			skCount = 0;
		}
		return false;
	}

	private boolean handIsStraight(Card[][] combination2) {
		int sCount = 0;
		boolean continued = false;
		boolean oneFound = false;
		for (int i = Rank.values().length - 1; i > 0; i--) {
			for (int j = 0; j < Suit.values().length; j++) {
				if (combination2[j][i] != null) {
					for (int k = 0; k < Suit.values().length; k++) {
						if (combination2[k][i - 1] != null && !oneFound) {
							sCount++;
							continued = true;
							oneFound = true;
						}
					}
				}
				if (sCount == 4)
					return true;
			}
			oneFound = false;
			if (!continued)
				sCount = 0;
		}
		return false;
	}

	private boolean handIsFlush(Card[][] combination2) {
		int fCount = 0;
		for (int i = 0; i < Suit.values().length; i++) {
			for (int j = 0; j < Rank.values().length - 1; j++) {
				if (combination2[i][Rank.values().length - j - 1] != null) {
					fCount++;
				}
				if (fCount == 4)
					return true;
			}
			fCount = 0;
		}
		return false;
	}

	private boolean handIsFullHouse(Card[][] combination2) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		for (int i = 0; i < Rank.values().length; i++) {
			for (int j = 0; j < Suit.values().length; j++) {
				temp[j][i] = combination2[j][i];
			}
		}
		int tokCount = 0;
		boolean threeOfAKind = false;
		boolean twoOfAKind = false;
		boolean lineCleared = false;
		for (int i = 0; i < Rank.values().length; i++) {
			for (int j = 0; j < Suit.values().length; j++) {
				if (temp[j][i] != null)
					tokCount++;
				if (tokCount == 3 && !lineCleared) {
					for (int j2 = 0; j2 < Suit.values().length; j2++) {
						temp[j2][i] = null;
					}
					lineCleared = true;
					threeOfAKind = true;
				}
			}
			tokCount = 0;
		}
		if (handIsSameKind(temp, 2))
			twoOfAKind = true;
		if (twoOfAKind && threeOfAKind)
			return true;
		return false;
	}

	private boolean handIsSameKind(Card[][] combination2, int count) {
		int skCount = 0;
		for (int i = Rank.values().length - 1; i > -1; i--) {
			for (int j = 0; j < Suit.values().length; j++) {
				if (combination2[j][i] != null) {
					skCount++;
				}
				if (skCount == count)
					return true;
			}
			skCount = 0;
		}
		return false;
	}

	private boolean handIsStraightFlush(Card[][] combination2) {
		int sfCount = 0;
		for (int i = 0; i < Suit.values().length; i++) {
			for (int j = 0; j < Rank.values().length - 1; j++) {
				if (combination2[i][Rank.values().length - j - 1] != null
						&& combination2[i][Rank.values().length - j] != null) {
					sfCount++;
				}
				if (sfCount == 4)
					return true;
			}
			sfCount = 0;
		}
		return false;
	}

	private boolean handIsRoyalFlush(Card[][] combination2) {
		int rfCount = 0;
		for (int i = 0; i < Suit.values().length; i++) {
			for (int j = 0; j < CARDS_TO_EVALUATE; j++) {
				if (combination2[i][Rank.values().length - j - 1] != null) {
					rfCount++;
				}
				if (rfCount == 5)
					return true;
			}
			rfCount = 0;
		}
		return false;
	}

	
	private Card[] sortHand(Card[] hand) {
		for (int i = 1; i < hand.length; i++) {
			if (hand[i - 1].getRank().ordinal() < hand[i].getRank().ordinal()) {
				Card prev = hand[i - 1];
				hand[i - 1] = hand[i];
				hand[i] = prev;
				i--;
			}
		}
		return hand;
	}
	 

	private void evaluateScore(List<Card> cards) {
		int score = 0;
		for (Card card : cards) {
			score += card.getRank().ordinal();
		}
		playerHand.setHandScore(score);
	}

	private List<Player> playersToEvaluate;
	private HashMap<Player, PlayerHand> evaluatedPlayers;
	private Card[][] combination;
	private PlayerHand playerHand;
	private List<Card> scoreCards;
}

/*
switch (entry.getValue().getHand()) {
case ROYAL_FLUSH:

	break;
case STRAIGHT_FLUSH:

	break;
case FOUR_OF_A_KIND:

	break;
case FULL_HOUSE:

	break;
case FLUSH:

	break;
case STRAIGHT:

	break;
case THREE_OF_A_KIND:

	break;
case TWO_PAIR:

	break;
case ONE_PAIR:

	break;
// Default equals to Hand.HIGH_HAND
default:
	break;
}
*/