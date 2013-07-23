package poker.arturka;

import java.util.ArrayList;
import java.util.List;

import message.data.Card;
import message.data.Player;
import message.data.Card.Rank;
import message.data.Card.Suit;

public class HandEvaluatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player p1 = new Player(1);
		Player p2 = new Player(2);
		Player p3 = new Player(3);

		Card c1 = new Card(Suit.HEARTS, Rank.ACE);
		Card c2 = new Card(Suit.SPADES, Rank.ACE);
		Card c3 = new Card(Suit.DIAMONDS, Rank.ACE);
		Card c4 = new Card(Suit.CLUBS, Rank.NINE);
		Card c5 = new Card(Suit.HEARTS, Rank.SEVEN);
		Card c6 = new Card(Suit.SPADES, Rank.TWO);
		Card c7 = new Card(Suit.HEARTS, Rank.TWO);
		Card[] playerHand = { c1, c2, c3, c4, c5, c6, c7 };
		p1.hand = playerHand;

		Card c11 = new Card(Suit.HEARTS, Rank.KING);
		Card c22 = new Card(Suit.SPADES, Rank.KING);
		Card c33 = new Card(Suit.DIAMONDS, Rank.KING);
		Card c44 = new Card(Suit.CLUBS, Rank.QUEEN);
		Card c55 = new Card(Suit.HEARTS, Rank.QUEEN);
		Card c66 = new Card(Suit.SPADES, Rank.SIX);
		Card c77 = new Card(Suit.SPADES, Rank.SIX);
		Card[] playerHand2 = { c11, c22, c33, c44, c55, c66, c77 };
		p2.hand = playerHand2;

		Card c111 = new Card(Suit.HEARTS, Rank.ACE);
		Card c222 = new Card(Suit.HEARTS, Rank.KING);
		Card c333 = new Card(Suit.HEARTS, Rank.QUEEN);
		Card c444 = new Card(Suit.HEARTS, Rank.JACK);
		Card c555 = new Card(Suit.HEARTS, Rank.TEN);
		Card c666 = new Card(Suit.SPADES, Rank.SIX);
		Card c777 = new Card(Suit.SPADES, Rank.FIVE);
		Card[] playerHand3 = { c111, c222, c333, c444, c555, c666, c777 };
		p3.hand = playerHand3;

		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		HandEvaluator evaluator = new HandEvaluator(players);
		List<PlayerHand> playerPositions = evaluator
				.getPlayerHandEvaluation();
		for (PlayerHand entry : playerPositions) {
			System.out.println(entry.getPosition() + "position - PlayerID:"
					+ entry.getPlayer().getId() + " with "
					+ entry.getHand() + "| SCORE: " + entry.getHandScore());
		}
	}

}
