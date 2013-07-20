package tests;


import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import message.data.ClientTurn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import poker.arturka.Card.Rank;
import poker.arturka.Card.Suit;
import client.ServerListener;
import client.TaskQueue;

public class OnlineClientTests {

	private TaskQueue que;
	private TestClientGame game;
	
	@Before
	public void setUp() throws Exception {
		
		try {
			FakeServer server = new FakeServer();
			Thread t = new Thread(server);
			t.start();
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Socket socket = new Socket(InetAddress.getLocalHost(), 9999);	
			this.que = new TaskQueue();
			
			ServerListener listener = new ServerListener(
					socket.getInputStream() , que);
			this.game  = new TestClientGame(null, que);	
			
			Thread listenerThread = new Thread(listener);
			Thread gameThread = new Thread(game);
			
			listenerThread.start();
			gameThread.start();
			
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		this.game.stop();
	}

	@Test
	public void onlineClientTest() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// checking if commands work right on client side
		
		assertEquals("ID must be 1", 1, game.model.getID());
		assertEquals("Player list must have 2 memebers", 
				2, game.model.getPlayerList().size());
		assertEquals("Cash must be 1200 after GiveCashCommand",
				1200, game.model.getPlayer(2).getCash());
		assertEquals("Card1 suit must be CLUBS", Suit.CLUBS,  
				game.model.getPlayer(1).getHand()[0].getSuit());
		assertEquals("Card1 rank must be ACE", Rank.ACE,  
				game.model.getPlayer(1).getHand()[0].getRank());
		assertEquals("Last turn of 1st player must be FOLD", ClientTurn.FOLD,
				game.model.getPlayer(1).getLastTurn());
		assertEquals("Cash must be 970 after move", 970,
				game.model.getPlayer(1).getCash());
		assertEquals("Bet must be 30 after move", 30,
				game.model.getPlayer(1).getBet());
		assertTrue("Dealer must be 2nd player", 
				game.model.getPlayer(2).isDealer() &&
				(!game.model.getPlayer(1).isDealer()));

		
	}

}

