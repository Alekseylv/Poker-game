package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import message.data.ClientTurn;
import message.data.PlayerMove;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import message.data.Card;
import message.data.Card.Rank;
import message.data.Card.Suit;
import message.data.Player;
import commands.ChangeCashCommand;
import commands.ChangeDealersCommand;
import commands.PlayerMoveCommand;
import commands.SendCardsCommand;
import commands.SetIDCommand;
import commands.SendPlayerListCommand;
import client.TaskQueue;

public class OfflineClientTests {

	private TaskQueue que;
	private FakeClientGame game;
	
	@Before
	public void setUp() throws Exception {
		
		this.que = new TaskQueue();
		this.game = new FakeClientGame(null, que);	
		
		Thread gameThread = new Thread(game);
		gameThread.start();
		
		List <Player>someList = new ArrayList<Player>();
		Player player = new Player(1);
		player.toggleDealer();
		
		someList.add(player);
		someList.add(new Player(2));
		
		this.que.addTask(new SetIDCommand(1));
		this.que.addTask(new SendPlayerListCommand(someList));
		this.que.addTask(new ChangeCashCommand(2, 1200));
		this.que.addTask(new SendCardsCommand(1, 
								new Card(Suit.CLUBS, Rank.ACE),
								new Card(Suit.DIAMONDS, Rank.ACE)));
		this.que.addTask(new PlayerMoveCommand( 
				new PlayerMove(1, ClientTurn.FOLD, 30, 970)));
		this.que.addTask(new ChangeDealersCommand(1,2));
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		this.game.stop();
	}

	@Test
	public void offlineClientTest() {
		
		// checking if commands work right on client side
		
		assertEquals("ID must be 1", 1, game.model.getID());
		assertEquals("Player list must have 2 memebers", 
				2, game.model.getPlayerList().size());
		assertEquals("Cash must be 1200", 1200, game.model.getPlayer(2).getCash());
		assertEquals("Card1 suit must be CLUBS", Suit.CLUBS,  
				game.model.getMyCards()[0].getSuit());
		assertEquals("Card1 rank must be ACE", Rank.ACE,  
				game.model.getMyCards()[0].getRank());
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
