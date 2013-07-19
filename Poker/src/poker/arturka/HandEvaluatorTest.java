package poker.arturka;

import java.util.ArrayList;
import java.util.List;

import poker.arturka.Card.Rank;
import poker.arturka.Card.Suit;

public class HandEvaluatorTest {

	private static List<Player> players;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Card c1 = new Card(Suit.SPADES, Rank.ACE);
		Card c2 = new Card(Suit.SPADES, Rank.KING);
		Card c3 = new Card(Suit.SPADES, Rank.QUEEN);
		Card c4 = new Card(Suit.SPADES, Rank.QUEEN);
		Card c5 = new Card(Suit.SPADES, Rank.QUEEN);
		Card c6 = new Card(Suit.SPADES, Rank.SIX);
		Card c7 = new Card(Suit.SPADES, Rank.SIX);
		Card[] playerHand = {c1, c2, c3, c4, c5, c6, c7};
		HandEvaluator evaluator = new HandEvaluator(players);
		Hand hand = evaluator.getHand(playerHand);
		System.out.println(hand);
	}

}
