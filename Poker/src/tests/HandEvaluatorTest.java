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

import poker.arturka.Hand;
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
		
		//Add players to the list
		playersForEvaluation.add(player1);
		playersForEvaluation.add(player2);
		playersForEvaluation.add(player3);
		playersForEvaluation.add(player4);
		
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
		
		// Initialize HandEvaluator
		evaluator = new HandEvaluator(playersForEvaluation, tableCards);
	}

	@After
	public void tearDown() throws Exception {
		playersForEvaluation = null;
		tableCards = null;
	}

	@Test
	public void testHandEvaluator() {
		assertEquals(tableCards, evaluator.getTableCards());
		
	}

	@Test
	public void testGetPlayerHandEvaluation() {
		testDifferentHands();
		testSimilarHands();
	}
	
	private void testSimilarHands() {
		testKickerComparison();
		testHighScoreComparison();
		// When there are two pairs or full house
		testHighScore2Comparison();
		
	}

	private void testHighScore2Comparison() {
		// TODO Auto-generated method stub
		
	}

	private void testHighScoreComparison() {
		// TODO Auto-generated method stub
		
	}

	private void testKickerComparison() {
		// TODO Auto-generated method stub
		
	}

	private void testDifferentHands() {
		testPlayerPositions();
		testPlayerHands();
	}

	private void testPlayerHands() {
		assertEquals(evaluator.getPlayerHandEvaluation().get(0).getHand(), Hand.ROYAL_FLUSH);
		assertEquals(evaluator.getPlayerHandEvaluation().get(1).getHand(), Hand.STRAIGHT);
		assertEquals(evaluator.getPlayerHandEvaluation().get(2).getHand(), Hand.THREE_OF_A_KIND);
		assertEquals(evaluator.getPlayerHandEvaluation().get(3).getHand(), Hand.TWO_PAIR);
	}

	private void testPlayerPositions() {
		assertEquals(evaluator.getPlayerHandEvaluation().get(0).getPlayer().getId(), 4);
		assertEquals(evaluator.getPlayerHandEvaluation().get(1).getPlayer().getId(), 2);
		assertEquals(evaluator.getPlayerHandEvaluation().get(2).getPlayer().getId(), 1);
		assertEquals(evaluator.getPlayerHandEvaluation().get(3).getPlayer().getId(), 3);
	}

	List<Player> playersForEvaluation;
	List<Card> tableCards;
	HandEvaluator evaluator;
}
