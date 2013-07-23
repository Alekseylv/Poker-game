package tests;

import static org.junit.Assert.*;
import static tests.IterUtil.iter;

import java.util.ArrayList;
import java.util.List;

import message.data.ClientTurn;

import org.junit.Before;
import org.junit.Test;

import message.data.Card;
import message.data.Player;
import message.data.Card.Rank;
import message.data.Card.Suit;
import client.ClientModel;
import client.ClientSidePlayer;
import client.State;

public class ModelTests {

	
	private ClientModel model;
	private List<ClientSidePlayer> playlist1;
	private List<ClientSidePlayer> playlist2;

	
	@Before
	public void setUp() throws Exception {
		this.model = new ClientModel(null);
		this.playlist1 = new ArrayList<ClientSidePlayer>();
		this.playlist2 = new ArrayList<ClientSidePlayer>();
		
		for(int i: iter(4)) {
			playlist1.add(new ClientSidePlayer(new Player(i,"bob")));
		}
		
		for(int i: iter(5, 12)) {
			playlist2.add(new ClientSidePlayer(new Player(i,"bob")));
		}
		
		playlist1.get(0).toggleDealer();
		playlist2.get(0).toggleDealer();
		
		this.model.setPlayerList(playlist1);
		this.model.setID(1);
	}


	@Test
	public void testGetDealer() {
		this.model.setPlayerList(playlist1);
		assertTrue("Dealer must be 0th player in playlist1",
				model.getDealer() == 0);
		this.model.setPlayerList(playlist2);
		assertTrue("Dealer must be 5th player in playlist2",
				model.getDealer() == 5);
	}
	
	@Test
	public void testSetPlayerList() {
		assertTrue("playlist1 length must be 4", 
				model.getPlayerList().size() == 4);
	}
	
	@Test
	public void testSetPlayerList2() {
		this.model.setPlayerList(playlist2);
		assertTrue("playlist1 length must be 7", 
				model.getPlayerList().size() == 7);
	}
	
	@Test
	public void testGetPlayer() {
		assertTrue("Must be able to find self",
				this.model.getPlayer(model.getID()).getId() == 1);
	}
	
	@Test
	public void testGetPlayerNull() {
		assertNull("Should not be found ", this.model.getPlayer(model.getPlayerList().size()+2));
	}
	
	@Test
	public void testSetGetID() {
		model.setID(234);
		assertTrue("Must be able to set ID", model.getID() == 234);
	}
	
	@Test
	public void testSetGetHand() {
		model.setMyHand(new Card(Suit.CLUBS, Rank.ACE),
				new Card(Suit.DIAMONDS, Rank.ACE));
		assertEquals("Card1 suit must be CLUBS", Suit.CLUBS,  
				model.getMyCards()[0].getSuit());
		assertEquals("Card1 rank must be ACE", Rank.ACE,  
				model.getMyCards()[0].getRank());
	}
	
	@Test
	public void testSetGetCards() {
		model.setCards(2, new Card(Suit.CLUBS, Rank.ACE),
				new Card(Suit.DIAMONDS, Rank.ACE));
		assertEquals("Card1 suit must be CLUBS", Suit.CLUBS,  
				model.getCards(2)[0].getSuit());
		assertEquals("Card1 rank must be ACE", Rank.ACE,  
				model.getCards(2)[0].getRank());
	}
	
	@Test
	public void testSetGetFieldCards() {
		Card[] cards = new Card[] {new Card(Suit.CLUBS, Rank.ACE),
			null, null, null, null};
		model.changeFieldCards(cards);
		assertEquals("Card1 suit must be CLUBS", Suit.CLUBS,  
				model.getFieldCards()[0].getSuit());
		assertEquals("Card1 rank must be ACE", Rank.ACE,  
				model.getFieldCards()[0].getRank());
		assertTrue("Must not alias same object", cards != model.getFieldCards());
	}
	
	@Test
	public void testSetGetState() {
		model.changeState(State.PLAYING);
		assertEquals("State must be PLAYING", State.PLAYING, model.getState());
	}
	
	@Test
	public void testGetMaxBet() {
		assertTrue("Initial max bet must be 0", 0 == model.getMaxBet());
		model.getPlayer(model.getID()).setBetTurnCash(200, 
				ClientTurn.RAISE, 800);
		assertTrue("Max after change should be 200", model.getMaxBet() == 200);
	}
	
	@Test
	public void testGetPot() {
		for(ClientSidePlayer i: model.getPlayerList()) {
			i.setBet(400);
		}
		
		assertTrue("Pot must be a calculable", 
				400 * model.getPlayerList().size() == model.getPot());
	}
	
	@Test
	public void testGetPlayerBet() {
		assertEquals("Must be the same", model.getPlayerBet(model.getID()),
				model.getPlayer(model.getID()).getBet());
	}
	
	@Test
	public void testGetPlayerCash() {
		assertEquals("Must be the same", model.getCash(model.getID()),
				model.getPlayer(model.getID()).getCash());
	}

}
