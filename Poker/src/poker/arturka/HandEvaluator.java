package poker.arturka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		int incremental = 1;
		HashMap<Integer, PlayerHand> playerPositions = new HashMap<Integer, PlayerHand>();
		// Adds hands to a position list.
		for (Entry<Player, PlayerHand> entry : evaluatedPlayers.entrySet()) {
			playerPositions.put(entry.getValue().getHand().ordinal() + 1,
					entry.getValue());
		}
		// Sorts position list hands if they are the same
		Map.Entry<Integer, PlayerHand> prev = null;
		for (Entry<Integer, PlayerHand> entry : playerPositions.entrySet()) {
			if (prev != null) {
				if (prev.getKey() == entry.getKey()) {
					if (entry.getValue().getHand() == Hand.HIGH_HAND) {
						if (prev.getValue().getHighCard().getRank().ordinal() < entry
								.getValue().getHighCard().getRank().ordinal())
							swapEntries(prev, entry);
					} else if (entry.getValue().getHand() == Hand.STRAIGHT_FLUSH
							|| entry.getValue().getHand() == Hand.FULL_HOUSE
							|| entry.getValue().getHand() == Hand.FLUSH
							|| entry.getValue().getHand() == Hand.STRAIGHT) {
						if (prev.getValue().getHandScore() < entry.getValue()
								.getHandScore()) {
							swapEntries(prev, entry);
							playerPositions.put(entry.getKey() + incremental++,
									entry.getValue());
						}
					} else if (entry.getValue().getHand() == Hand.ROYAL_FLUSH) {
						// do nothing, because it is the strongest hand
						// empty so that shouldn't write many evaluations in
						// next statement :)
					} else {
						if (prev.getValue().getKicker().getRank().ordinal() < entry
								.getValue().getKicker().getRank().ordinal()) {
							swapEntries(prev, entry);
							playerPositions.put(entry.getKey() + incremental++,
									entry.getValue());
						}
					}
				}
			} else
				prev = entry;
		}
		return playerPositions;
	}

	private void swapEntries(Entry<Integer, PlayerHand> prev,
			Entry<Integer, PlayerHand> entry) {
		entry.setValue(prev.setValue(entry.getValue()));
	}

	private Hand getPlayerHand(Player player) {
		return getHand(player.getHand());
	}

	public Hand getHand(Card[] hand) {
		// In method calls have to set scoreCards!
		currentHand = sortHand(hand);
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
		else {
			playerHand.setHighCard(currentHand[0]);
			return Hand.HIGH_HAND;
		}
	}

	private boolean handIsTwoPair(Card[][] combination2) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		temp = cloneTable(temp, combination2);
		int skCount = 0;
		for (int i = Rank.values().length - 1; i > -1; i--) {
			scoreCards = new ArrayList<Card>();
			for (int j = 0; j < Suit.values().length; j++) {
				if (temp[Suit.values().length - j - 1][i] != null) {
					if (skCount < 2)
						scoreCards.add(temp[Suit.values().length - j - 1][i]);
					skCount++;
				}
				if (skCount == TWO_PAIR_COUNT) {
					evaluateScore(scoreCards);
					setPlayerHand();
					return true;
				}
			}
			scoreCards = null;
			skCount = 0;
		}
		return false;
	}

	private boolean handIsStraight(Card[][] combination2) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		temp = cloneTable(temp, combination2);
		int sCount = 0;
		boolean continued = false;
		boolean oneFound = false;
		for (int i = Rank.values().length - 1; i > 0; i--) {
			scoreCards = new ArrayList<Card>();
			for (int j = 0; j < Suit.values().length; j++) {
				if (temp[j][i] != null) {
					for (int k = 0; k < Suit.values().length; k++) {
						if (temp[k][i - 1] != null && !oneFound) {
							scoreCards.add(temp[k][i - 1]);
							sCount++;
							continued = true;
							oneFound = true;
						}
					}
				}
				if (sCount == 4) {
					evaluateScore(scoreCards);
					setPlayerHand();
					return true;
				}
			}
			oneFound = false;
			if (!continued) {
				scoreCards = null;
				sCount = 0;
			}
		}
		return false;
	}

	private boolean handIsFlush(Card[][] combination2) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		temp = cloneTable(temp, combination2);
		int fCount = 0;
		for (int i = 0; i < Suit.values().length; i++) {
			scoreCards = new ArrayList<Card>();
			for (int j = 0; j < Rank.values().length - 1; j++) {
				if (temp[i][Rank.values().length - j - 1] != null) {
					scoreCards.add(temp[Suit.values().length - j - 1][i]);
					fCount++;
				}
				if (fCount == 4) {
					evaluateScore(scoreCards);
					setPlayerHand();
					return true;
				}
			}
			scoreCards = null;
			fCount = 0;
		}
		return false;
	}

	private boolean handIsFullHouse(Card[][] combination2) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		temp = cloneTable(temp, combination2);
		int tokCount = 0;
		boolean threeOfAKind = false;
		boolean twoOfAKind = false;
		boolean lineCleared = false;
		for (int i = 0; i < Rank.values().length; i++) {
			scoreCards = new ArrayList<Card>();
			for (int j = 0; j < Suit.values().length; j++) {
				if (temp[j][i] != null) {
					scoreCards.add(temp[j][i]);
					tokCount++;
				}
				if (tokCount == 3 && !lineCleared) {
					for (int j2 = 0; j2 < Suit.values().length; j2++) {
						temp[j2][i] = null;
					}
					lineCleared = true;
					threeOfAKind = true;
				}
			}
			scoreCards = null;
			tokCount = 0;
		}
		if (handIsSameKind(temp, 2))
			twoOfAKind = true;
		if (twoOfAKind && threeOfAKind) {
			evaluateScore(scoreCards);
			setPlayerHand();
			return true;
		}
		return false;
	}

	private Card[][] cloneTable(Card[][] temp, Card[][] combination2) {
		for (int i = 0; i < Rank.values().length; i++) {
			for (int j = 0; j < Suit.values().length; j++) {
				temp[j][i] = combination2[j][i];
			}
		}
		return temp;
	}

	private boolean handIsSameKind(Card[][] combination2, int count) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		temp = cloneTable(temp, combination2);
		int skCount = 0;
		for (int i = Rank.values().length - 1; i > -1; i--) {
			scoreCards = new ArrayList<Card>();
			for (int j = 0; j < Suit.values().length; j++) {
				if (temp[j][i] != null) {
					scoreCards.add(temp[j][i]);
					skCount++;
				}
				if (skCount == count) {
					evaluateScore(scoreCards);
					setPlayerHand();
					return true;
				}
			}
			scoreCards = null;
			skCount = 0;
		}
		return false;
	}

	private boolean handIsStraightFlush(Card[][] combination2) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		temp = cloneTable(temp, combination2);
		int sfCount = 0;
		for (int i = 0; i < Suit.values().length; i++) {
			scoreCards = new ArrayList<Card>();
			for (int j = 0; j < Rank.values().length - 1; j++) {
				if (temp[i][Rank.values().length - j - 1] != null
						&& temp[i][Rank.values().length - j] != null) {
					scoreCards.add(temp[i][Rank.values().length - j]);
					sfCount++;
				}
				if (sfCount == 4) {
					evaluateScore(scoreCards);
					setPlayerHand();
					return true;
				}
			}
			scoreCards = null;
			sfCount = 0;
		}
		return false;
	}

	private boolean handIsRoyalFlush(Card[][] combination2) {
		Card[][] temp = new Card[Suit.values().length][Rank.values().length];
		temp = cloneTable(temp, combination2);
		int rfCount = 0;
		for (int i = 0; i < Suit.values().length; i++) {
			scoreCards = new ArrayList<Card>();
			for (int j = 0; j < CARDS_TO_EVALUATE; j++) {
				if (temp[i][Rank.values().length - j - 1] != null) {
					scoreCards.add(temp[i][Rank.values().length - j - 1]);
					rfCount++;
				}
				if (rfCount == 5) {
					evaluateScore(scoreCards);
					setPlayerHand();
					return true;
				}
			}
			scoreCards = null;
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

	private void setPlayerHand() {
		Card[] temp = new Card[CARDS_TO_EVALUATE];
		for (int i = 0; i < CARDS_TO_EVALUATE; i++) {
			if (i > scoreCards.size() - 1) {
				if (i == scoreCards.size())
					playerHand.setKicker(temp[i]);
				temp[i] = currentHand[i - scoreCards.size()];
			} else
				temp[i] = scoreCards.get(i);
		}
		temp = sortHand(temp);
	}

	private List<Player> playersToEvaluate;
	private HashMap<Player, PlayerHand> evaluatedPlayers;
	private Card[][] combination;
	private Card[] currentHand;
	private PlayerHand playerHand;
	private List<Card> scoreCards;
}