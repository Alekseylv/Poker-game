package poker.arturka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import poker.arturka.Card.Rank;

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

	private Hand getHand(Card[] hand) {
		Card[] sortedHand = sortHand(hand);
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
	}
	
	static boolean handIsTwoPair(Card[] sortedHand) {
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
			if (failCount > 3)
				return false;
		}
		return true;
	}

	private boolean handIsStraight(Card[] sortedHand) {
		int failCount = 0;
		for (int i = 0; i < sortedHand.length; i++) {
			if (!(i > 0
					&& sortedHand[i - 1].getRank().ordinal() == sortedHand[i]
							.getRank().ordinal() + 1))
				failCount++;
			if (failCount > 2)
				return false;
		}
		return true;
	}

	private boolean handIsFlush(Card[] sortedHand) {
		int failCount = 0;
		for (int i = 0; i < sortedHand.length; i++) {
			if (!(i > 0
					&& sortedHand[i - 1].getSuit().equals(
							sortedHand[i].getSuit())))
				failCount++;
			if (failCount > 2)
				return false;
		}
		return true;
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
			if (failCount > count)
				return false;
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
		int failCount = 0;
		for (int i = 1; i < sortedHand.length; i++) {
			if (!(sortedHand[i-1].getRank().equals(sortedHand[i].getRank())))
				failCount++;
			if (failCount > sortedHand.length - count)
				return false;
		}
		return true;
	}

	private boolean handIsStraightFlush(Card[] sortedHand) {
		int failCount = 0;
		for (int i = 0; i < sortedHand.length; i++) {
			if (!(i > 0
					&& sortedHand[i - 1].getSuit().equals(
							sortedHand[i].getSuit())
					&& sortedHand[i - 1].getRank().ordinal() == sortedHand[i]
							.getRank().ordinal() + 1))
				failCount++;
			if (failCount > 2)
				return false;
		}
		return true;
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
		for (int i = 0; i < hand.length; i++) {
			if (hand[i-1].getRank().ordinal() < hand[i].getRank().ordinal() && i > 0) {
				Card prev = hand[i-1];
				hand[i-1] = hand[i];
				hand[i] = prev;
				i--;
			}
		}
		return null;
	}

	private List<Player> playersToEvaluate;
	private HashMap<Player, Hand> evaluatedPlayers;
}
