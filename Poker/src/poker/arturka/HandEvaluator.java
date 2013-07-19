package poker.arturka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import poker.arturka.Card.Rank;
import poker.arturka.Card.Suit;

public class HandEvaluator {

	private static final int CARDS_TO_EVALUATE = 5;
	
	public HandEvaluator(List<Player> playersLeft) {
		playersToEvaluate = playersLeft;
		evaluatedPlayers = new HashMap<Player, Hand>();
	}

	public List<Player> getPlayerHandEvaluation() {
		List<Player> playerRanking = new ArrayList<Player>();
		for (Player player: playersToEvaluate) {
			evaluatedPlayers.put(player, getPlayerHand(player));
		}
		sortEvaluatedPlayers();
		playerRanking.addAll(evaluatedPlayers.keySet());
		return playerRanking;
	}

	private Boolean[][] createHandTable(Card[] hand) {
		Boolean[][] tempHand = new Boolean[Suit.values().length][Rank.values().length];
		for (Card card: hand) {
			tempHand[card.getSuit().ordinal()][card.getRank().ordinal()] = true;
		}
		int c= 0;
		for (Boolean[] val: tempHand) {
			for (Boolean val2: val) {
				if (val2 != null) {
					c++;
				}
			}
		}
		System.out.println(c);
		return tempHand;
	}
	
	private void sortEvaluatedPlayers() {
		Map.Entry<Player, Hand> prev = null;
		for (Entry<Player, Hand> entry: evaluatedPlayers.entrySet()) {
			if (prev.getValue().ordinal() > entry.getValue().ordinal() && prev != null) {
				swapEntries(prev, entry);
			}
			prev = entry;
		}
	}

	private void swapEntries(Entry<Player, Hand> prev, Entry<Player, Hand> entry) {
		entry.setValue(prev.setValue(entry.getValue()));
	}

	private Hand getPlayerHand(Player player) {
		return getHand(player.getHand());
	}

	public Hand getHand(Card[] hand) {
		combination = createHandTable(hand);
		//Card[] sortedHand = sortHand(hand);
		//
		if (handIsRoyalFlush(combination))
			return Hand.ROYAL_FLUSH;
		else if (handIsStraightFlush(combination))
			return Hand.STRAIGHT_FLUSH;
		else if (handIsSameKind(combination,4))
			return Hand.FOUR_OF_A_KIND;
		else if (handIsFullHouse(combination))
			return Hand.FULL_HOUSE;
		/*
		if (handIsRoyalFlush(sortedHand))
			return Hand.ROYAL_FLUSH;
		else if (handIsStraightFlush(sortedHand))
			return Hand.STRAIGHT_FLUSH;
		else if (handHasSimilarRanks(sortedHand, 4))
			return Hand.FOUR_OF_A_KIND;
		else if (handIsFullHouse(sortedHand, true) || handIsFullHouse(sortedHand, false))
			return Hand.FULL_HOUSE;
		else if (handIsFlush(sortedHand))
			return Hand.FLUSH;
		else if (handIsStraight(sortedHand))
			return Hand.STRAIGHT;
		else if (handHasSimilarRanks(sortedHand, 3))
			return Hand.THREE_OF_A_KIND;
		else if (handIsTwoPair(sortedHand))
			return Hand.TWO_PAIR;
		else if (handHasSimilarRanks(sortedHand, 2))
			return Hand.ONE_PAIR;
		else
			return Hand.HIGH_HAND;
			*/
		
		return null;
	}
	
	private boolean handIsFullHouse(Boolean[][] combination2) {
		int tokCount = 0;
		boolean threeOfAKind = false;
		boolean twoOfAKind = false;
		boolean lineCleared = false;
		for (int i = 0; i < Rank.values().length; i++) {
			for (int j = 0; j < Suit.values().length - 1; j++) {
				if(combination2[j][i] != null && combination2[j+1][i] != null)
					tokCount++;
				if (tokCount == 2 && !lineCleared) {
					for (int j2 = 0; j2 < Suit.values().length; j2++) {
						combination2[j2][i] = null;
					}
					lineCleared = true;
					threeOfAKind = true;
				}
			}
			tokCount = 0;
		}
		if (handIsSameKind(combination2, 2))
			twoOfAKind = true;
		if (twoOfAKind && threeOfAKind)
			return true;
		System.out.println(twoOfAKind +" "+ threeOfAKind);
		return false;
	}

	private boolean handIsSameKind(Boolean[][] combination2, int count) {
		int skCount = 0;
		for (int i = Rank.values().length - 1; i < -1; i--) {
			for (int j = 0; j < Suit.values().length - 1; j++) {
				if(combination2[j][i] != null && combination2[j+1][i] != null)
					skCount++;
				if (skCount == count - 1)
					return true;
			}
			skCount = 0;
		}
		return false;
	}

	private boolean handIsStraightFlush(Boolean[][] combination2) {
		int sfCount = 0;
		for (int i = 0; i < Suit.values().length; i++) {
			for (int j = 0; j < Rank.values().length - 1; j++) {
				if (combination2[i][Rank.values().length - j - 1] != null &&
						combination2[i][Rank.values().length - (j+1) - 1] != null) {
					sfCount++;
				}
				if (sfCount == 4)
					return true;
			}
			sfCount = 0;
		}
		return false;
	}

	private boolean handIsRoyalFlush(Boolean[][] combination2) {
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

	private boolean handIsTwoPair(Card[] sortedHand) {
		int failCount = 0;
		int tempID = 0;
		int onePairFound = 0;
		boolean pairAtBeggining = false;
		Card[] temp = new Card[sortedHand.length - 2];
		// Finding first pair
		for (int i = 0; i < sortedHand.length - 1; i++) {
			if (!(sortedHand[i].getRank().equals(sortedHand[i + 1].getRank()))) {
				failCount++;
				if (onePairFound != 1)
					temp[tempID++] = sortedHand[i];
				if (pairAtBeggining && i == 2) 
					temp[tempID++] = sortedHand[2];
				else if (i + 1 == sortedHand.length - 1)
					temp[tempID++] = sortedHand[i + 1];
			}
			else {
				if (i==0) {
					pairAtBeggining = true;
				}
				if (onePairFound > 0) {
					temp[tempID++] = sortedHand[i];
					if (i + 1 == sortedHand.length - 1) {
						temp[tempID++] = sortedHand[i + 1];
					}
				}
				onePairFound++;
			}
			if (failCount > 5)
				return false;
		}
		// Finding second pair
		failCount = 0;
		for (int i = 0; i < temp.length - 1; i++) {
			if (!(temp[i].getRank().equals(temp[i + 1].getRank()))) {
				failCount++;
			}
			if (failCount > 3)
				return false;
		}
		return true;
	}

	private boolean handIsStraight(Card[] sortedHand) {
		int failCount = 0;
		for (int i = 0; i < sortedHand.length - 1; i++) {
			if (!(sortedHand[i].getRank().ordinal() == sortedHand[i + 1]
							.getRank().ordinal() + 1))
				failCount++;
			if (failCount > 2)
				return false;
		}
		return true;
	}

	private boolean handIsFlush(Card[] sortedHand) {
		int spadesCount = 0;
		int diamondsCount = 0;
		int heartsCount = 0;
		int clubsCount = 0;
		for (int i = 0; i < sortedHand.length; i++) {
			switch (sortedHand[i].getSuit()) {
			case CLUBS:
				clubsCount++;
				break;
			case HEARTS:
				heartsCount++;
				break;
			case SPADES:
				spadesCount++;
				break;
			case DIAMONDS:
				diamondsCount++;
				break;
			default:
				break;
			}
		}
		if (spadesCount > 4 || heartsCount > 4 || diamondsCount > 4 || clubsCount > 4)
			return true;
		else
			return false;
	}

	private boolean handIsFullHouse(Card[] sortedHand, boolean pairFirst) {
		int failCount = 0;
		int tempID = 0;
		int onePairFound = 0;
		int count = 0;
		if (pairFirst)
			count = 5;
		else
			count = 4;
		boolean pairAtBeggining = false;
		Card[] temp = new Card[sortedHand.length - 2];
		// Finding first pair
		for (int i = 0; i < sortedHand.length - 1; i++) {
			if (!(sortedHand[i].getRank().equals(sortedHand[i + 1].getRank()))) {
				failCount++;
				if (onePairFound != 1)
					temp[tempID++] = sortedHand[i];
				if (pairAtBeggining && i == 2) 
					temp[tempID++] = sortedHand[2];
				else if (i + 1 == sortedHand.length - 1)
					temp[tempID++] = sortedHand[i + 1];
				
			}
			else {
				if (i==0) {
					pairAtBeggining = true;
				}
				if (onePairFound > 0) {
					temp[tempID++] = sortedHand[i];
					if (i + 1 == sortedHand.length - 1) {
						temp[tempID++] = sortedHand[i + 1];
					}
				}
				onePairFound++;
			}
			if (failCount > count - 1)
				return false;
			if(temp[i] != null)
				System.out.println(temp[i].getRank() + " of " + temp[i].getSuit());
		}
		//
		System.out.println(temp[0].getRank() + " "
				+ temp[1].getRank() + " "
				+ temp[2].getRank() + " "
				+ temp[3].getRank() + " "
				+ temp[4].getRank());
		// Finding second pair
		failCount = 0;
		for (int i = 0; i < temp.length - 1; i++) {
			if (!(temp[i].getRank().equals(temp[i + 1].getRank()))) {
				failCount++;
			}
			if (failCount > sortedHand.length - count + 2)
				return false;
		}
		return true;
	}

	private boolean handHasSimilarRanks(Card[] sortedHand, int count) {
		int successCount = 0;
		for (int i = 0; i < sortedHand.length - 1; i++) {
			if (sortedHand[i].getRank().equals(sortedHand[i + 1].getRank()))
				successCount++;
			else if (successCount > 0)
				successCount--;
			if (successCount >= sortedHand.length - count)
				return true;
		}
		return false;
	}

	private boolean handIsStraightFlush(Card[] sortedHand) {
		int successCount = 0;
		for (int i = 0; i < sortedHand.length - 1; i++) {
			if (sortedHand[i].getSuit().equals(
							sortedHand[i + 1].getSuit())
					&& sortedHand[i].getRank().ordinal() == sortedHand[i + 1]
							.getRank().ordinal() + 1)
				successCount++;
			else {
				if (successCount == 2)
					return false;
			}
		}
		if (successCount > 3)
			return true;
		return false;
	}

	private boolean handIsRoyalFlush(Card[] sortedHand) {
		Card master = sortedHand[0];
		if (master.getRank() == Rank.ACE) {
			for (int i = 1; i < CARDS_TO_EVALUATE; i++) {
				if (sortedHand[i].getSuit() != master.getSuit())
					return false;
			}
		}
		else
			return false;
		if (sortedHand[CARDS_TO_EVALUATE - 1].getRank() != Rank.TEN)
			return false;
		return true;
	}

	private Card[] sortHand(Card[] hand) {
		for (int i = 1; i < hand.length; i++) {
			if (hand[i-1].getRank().ordinal() < hand[i].getRank().ordinal()) {
				Card prev = hand[i-1];
				hand[i-1] = hand[i];
				hand[i] = prev;
				i--;
			}
		}
		return hand;
	}

	private List<Player> playersToEvaluate;
	private HashMap<Player, Hand> evaluatedPlayers;
	private Boolean[][] combination;
}
