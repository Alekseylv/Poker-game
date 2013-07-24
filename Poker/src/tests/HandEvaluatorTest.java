package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import message.data.Card;
import message.data.Player;
import message.data.Card.Rank;
import message.data.Card.Suit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import poker.arturka.HandEvaluator;

public class HandEvaluatorTest {

	@Before
	public void setUp() throws Exception {
		playersForEvaluation = new ArrayList<Player>();
		tableCards = new ArrayList<Card>();
		
		// Player 1
		Player player1 = new Player(1, "Maksims");
		Card p1c1 = new Card(Suit.CLUBS, Rank.SIX);
		Card p1c2 = new Card(Suit.HEARTS, Rank.SIX);
		player1.hand = new Card[] { p1c1, p1c2 };
		
		// Player 2
		Player player2 = new Player(2, "Arturs");
		Card p2c1 = new Card(Suit.CLUBS, Rank.KING);
		Card p2c2 = new Card(Suit.HEARTS, Rank.NINE);
		player2.hand = new Card[] { p2c1, p2c2 };
		
		// Player 3
		Player player3 = new Player(3, "Aleksejs");
		Card p3c1 = new Card(Suit.DIAMONDS, Rank.QUEEN);
		Card p3c2 = new Card(Suit.HEARTS, Rank.JACK);
		player3.hand = new Card[] { p3c1, p3c2 };
		
		//Player 4
		Player player4 = new Player(4, "Janis");
		Card p4c1 = new Card(Suit.CLUBS, Rank.ACE);
		Card p4c2 = new Card(Suit.CLUBS, Rank.KING);
		player4.hand = new Card[] { p4c1, p4c2 }; 
		
		// Initializing tableCards
		Card tableCard1 = new Card(Suit.CLUBS, Rank.QUEEN);
		Card tableCard2 = new Card(Suit.CLUBS, Rank.JACK);
		Card tableCard3 = new Card(Suit.CLUBS, Rank.TEN);
		Card tableCard4 = new Card(Suit.DIAMONDS, Rank.SIX);
		Card tableCard5 = new Card(Suit.HEARTS, Rank.TWO);
		// Add these cards to list
		tableCards.add(tableCard1);
		tableCards.add(tableCard2);
		tableCards.add(tableCard3);
		tableCards.add(tableCard4);
		tableCards.add(tableCard5);
	}

	@After
	public void tearDown() throws Exception {
		playersForEvaluation = null;
		tableCards = null;
	}

	@Test
	public void testHandEvaluator() {
		HandEvaluator evaluator = new HandEvaluator(playersForEvaluation, tableCards);
		assertEquals(tableCards, evaluator.getTableCards());
		
	}

	@Test
	public void testGetPlayerHandEvaluation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHand() {
		
	}

	List<Player> playersForEvaluation;
	List<Card> tableCards;
}
