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
<<<<<<< HEAD
		Player p1 = new Player(1, "a");
		Player p2 = new Player(2, "b");
		Player p3 = new Player(3, "c");
		Player p4 = new Player(4, "d");
		Player p5 = new Player(5, "e");
		Player p6 = new Player(6, "f");
=======
		Player p1 = new Player(1,"bob");
		Player p2 = new Player(2,"bob");
		Player p3 = new Player(3,"bob");
		Player p4 = new Player(4,"bob");
		Player p5 = new Player(5,"bob");
		Player p6 = new Player(6,"bob");
>>>>>>> refs/remotes/origin/master

		Card c1 = new Card(Suit.HEARTS, Rank.JACK);
		Card c2 = new Card(Suit.SPADES, Rank.JACK);
		Card c3 = new Card(Suit.DIAMONDS, Rank.JACK);
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
		
		Card c1111 = new Card(Suit.HEARTS, Rank.ACE);
		Card c2222 = new Card(Suit.SPADES, Rank.ACE);
		Card c3333 = new Card(Suit.DIAMONDS, Rank.ACE);
		Card c4444 = new Card(Suit.CLUBS, Rank.QUEEN);
		Card c5555 = new Card(Suit.HEARTS, Rank.QUEEN);
		Card c6666 = new Card(Suit.SPADES, Rank.SIX);
		Card c7777 = new Card(Suit.SPADES, Rank.SIX);
		Card[] playerHand4 = { c1111, c2222, c3333, c4444, c5555, c6666, c7777 };
		p4.hand = playerHand4;
		
		Card c11111 = new Card(Suit.HEARTS, Rank.ACE);
		Card c22222 = new Card(Suit.SPADES, Rank.JACK);
		Card c33333 = new Card(Suit.DIAMONDS, Rank.TEN);
		Card c44444 = new Card(Suit.CLUBS, Rank.NINE);
		Card c55555 = new Card(Suit.HEARTS, Rank.SIX);
		Card c66666 = new Card(Suit.SPADES, Rank.SIX);
		Card c77777 = new Card(Suit.CLUBS, Rank.SIX);
		Card[] playerHand5 = { c11111, c22222, c33333, c44444, c55555, c66666, c77777 };
		p5.hand = playerHand5;
		
		Card c111111 = new Card(Suit.HEARTS, Rank.ACE);
		Card c222222 = new Card(Suit.SPADES, Rank.JACK);
		Card c333333 = new Card(Suit.DIAMONDS, Rank.TEN);
		Card c444444 = new Card(Suit.CLUBS, Rank.NINE);
		Card c555555 = new Card(Suit.HEARTS, Rank.SIX);
		Card c666666 = new Card(Suit.SPADES, Rank.SIX);
		Card c777777 = new Card(Suit.CLUBS, Rank.SIX);
		Card[] playerHand6 = { c111111, c222222, c333333, c444444, c555555, c666666, c777777 };
		p6.hand = playerHand6;

		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);
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
