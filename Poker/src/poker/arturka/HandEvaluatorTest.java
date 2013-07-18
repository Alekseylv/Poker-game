package poker.arturka;

import poker.arturka.Card.Rank;
import poker.arturka.Card.Suit;

public class HandEvaluatorTest {

	public static void main(String[] args) {
		Card first = new Card(Suit.CLUBS, Rank.ACE);
		Card second = new Card(Suit.CLUBS, Rank.KING);
		Card third = new Card(Suit.CLUBS, Rank.QUEEN);
		Card fourth = new Card(Suit.SPADES, Rank.TEN);
		Card fifth = new Card(Suit.SPADES, Rank.TEN);
		Card sixth = new Card(Suit.CLUBS, Rank.TEN);
		Card seventh = new Card(Suit.CLUBS, Rank.TWO);
		Card[] hand = {first, second, third, fourth, fifth, sixth, seventh};
		System.out.println(HandEvaluator.handIsFullHouse(hand, false));

	}

}
