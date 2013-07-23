package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import message.data.Card;
import message.data.Player;
import poker.arturka.Players;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/19/13
 * Time: 3:05 PM
 */
public class PlayersTest {
    private Player player1;
    private int id1;
    private int id2;
    private int idm1;
    int cash1;
    public PlayersTest() {

    }

    @Before
    public void setUp() throws Exception {
        id1=1;
        id2=2;
        idm1=-1;
        cash1=1234;
        player1 = new Player(333,"bob");
        player1.setCash(cash1);
        player1.toggleDealer();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddPlayer() throws Exception {
        Players players=new Players();
        Player check = players.addPlayer(idm1,"bob");
        Assert.assertNull(check);

        check = players.addPlayer(id1,"bob");
        Assert.assertNotNull(check);
        Assert.assertEquals(players.getPlayerById(id1), check);
        Assert.assertNull(players.getPlayerById(id2));

        Player check2 = players.addPlayer(id2,"bob");
        Assert.assertNotNull(players.getPlayerById(id2));
        Assert.assertEquals(players.getPlayerById(id2), check2);

        players.addPlayer(id2,"bob");
        Assert.assertEquals(players.getPlayersList().size(), 2);
    }

    @Test
    public void testRemovePlayer() throws Exception {
        Players players=new Players();
        Player check = players.removePlayer(id1);
        Assert.assertNull(check);

        players.addPlayer(id1,"bob");
        Assert.assertNotNull(players.getPlayerById(id1));

        players.removePlayer(id1);
        Assert.assertNull(players.getPlayerById(id1));

        check = players.addPlayer(id1,"bob");
        Player check1 = players.removePlayer(id1);
        Assert.assertEquals(check,check1);

        players.addPlayer(id1,"bob");
        players.addPlayer(id2,"bob");
        players.removePlayer(id1);
        Assert.assertNull(players.getPlayerById(id1));
        Assert.assertNotNull(players.getPlayerById(id2));
    }

    @Test
    public void testGetRandomPlayer() throws Exception {
        Players players=new Players();
        Assert.assertNull(players.getRandomPlayer());

        Player check = players.addPlayer(id1,"bob");
        Assert.assertNotNull(players.getRandomPlayer());
        Assert.assertEquals(players.getRandomPlayer(), check);

        players.addPlayer(id2,"bob");
        Assert.assertNotNull(players.getRandomPlayer());

        Player check2=players.getRandomPlayer();
        Assert.assertTrue(check.equals(check) || check.equals(check2));
    }

    @Test
    public void testGetNextPlayer() throws Exception {
        Players players=new Players();
        Assert.assertNull(players.getNextPlayer(player1));

        Player check = players.addPlayer(id1,"bob");
        Assert.assertNotNull(players.getNextPlayer(check));
        Assert.assertEquals(players.getNextPlayer(check), check);

        Player check2 = players.addPlayer(id2,"bob");
        Assert.assertNotNull(players.getNextPlayer(check));
        Assert.assertEquals(players.getNextPlayer(check), check2);
        Assert.assertEquals(players.getNextPlayer(check2), check);

        players.removePlayer(id1);
        Assert.assertEquals(players.getNextPlayer(check2), check2);
    }

    @Test
    public void testGetDealer() throws Exception {
        Players players=new Players();
        Player check=players.getDealer();
        Assert.assertNull(check);

        check=players.addPlayer(id1,"bob");
        Assert.assertNotNull(players.getDealer());
        Assert.assertEquals(players.getDealer(), check);

        Player check1 = players.addPlayer(id2,"bob");
        Assert.assertEquals(players.getDealer(),check);

        check.toggleDealer();
        check1.toggleDealer();
        Assert.assertNotSame(players.getDealer(),check);
        Assert.assertEquals(players.getDealer(),check1);
    }

    @Test
    public void testNextDealer() throws Exception {
        Players players=new Players();
        Player check=players.nextDealer();
        Assert.assertNull(check);

        players.addPlayer(id1,"bob");
        check=players.nextDealer();
        Assert.assertNotNull(check);
        Assert.assertNotNull(players.getDealer());
        Assert.assertTrue(check.isDealer());
        Assert.assertEquals(check,players.getDealer());

        Player check1=players.addPlayer(id2,"bob");
        Assert.assertFalse(check1.isDealer());

        Player check2=players.nextDealer();
        Assert.assertNotNull(check2);
        Assert.assertFalse(check.isDealer());
        Assert.assertTrue(check1.isDealer());

        check2=players.nextDealer();
        Assert.assertNotNull(check2);
        Assert.assertFalse(check1.isDealer());
        Assert.assertTrue(check.isDealer());
    }

    @Test
    public void testGetPot() throws Exception {
        Players players=new Players();
        Assert.assertEquals(players.getPot(),0);

        Player check=players.addPlayer(id1,"bob");
        Player check1=players.addPlayer(id2,"bob");
        check.bet(101);
        check1.bet(10);
        Assert.assertEquals(players.getPot(),111);
    }

    @Test
    public void testPlayersLeft() throws Exception {
        Players players=new Players();
        Assert.assertEquals(players.playersLeft().size(),0);

        Player check=players.addPlayer(id1,"bob");
        Assert.assertEquals(players.playersLeft().size(),1);
        ArrayList<Player> checkList=new ArrayList<Player>();
        checkList.add(check);
        Assert.assertEquals(players.playersLeft(),checkList);

        Player check1=players.addPlayer(id2,"bob");
        Assert.assertEquals(players.playersLeft().size(),2);

        checkList.add(check1);
        Assert.assertEquals(players.playersLeft(),checkList);

        check.Fold();
        checkList.remove(check);
        Assert.assertEquals(players.playersLeft(),checkList);

    }

    @Test
    public void testGetPlayersList() throws Exception {
        Players players=new Players();
        Assert.assertEquals(players.getPlayersList().size(),0);

        Player check=players.addPlayer(id1,"bob");
        Assert.assertEquals(players.getPlayersList().size(),1);
        ArrayList<Player> checkList=new ArrayList<Player>();
        checkList.add(check);
        Assert.assertEquals(players.getPlayersList(),checkList);

        Player check1=players.addPlayer(id2,"bob");
        Assert.assertEquals(players.getPlayersList().size(),2);

        checkList.add(check1);
        Assert.assertEquals(players.getPlayersList(),checkList);

        check.Fold();
        Assert.assertEquals(players.getPlayersList(), checkList);

    }

    @Test
    public void testGetSafeList() throws Exception {
        Players players=new Players();
        Assert.assertEquals(players.getSafeList(player1).size(),0);

        Player check=players.addPlayer(id1,"bob");
        Assert.assertEquals(players.getSafeList(check).size(),1);
        Assert.assertTrue(players.getSafeList(player1).get(0).equals(check));

        Player check1=players.addPlayer(id2,"bob");
        Assert.assertEquals(players.getSafeList(player1).size(),2);
        Assert.assertTrue(players.getSafeList(player1).get(0).equals(check));
        Assert.assertTrue(players.getSafeList(player1).get(1).equals(check1));

        check.Fold();
        Assert.assertTrue(players.getSafeList(player1).get(0).equals(check));
        Assert.assertTrue(players.getSafeList(player1).get(1).equals(check1));

        Card card=new Card(Card.Suit.CLUBS, Card.Rank.ACE);
        Card card1=new Card(Card.Suit.DIAMONDS, Card.Rank.JACK);
        check.giveCards(card,card1);
        Assert.assertNull(players.getSafeList(player1).get(0).getHand()[0]);
        Assert.assertNull(players.getSafeList(player1).get(0).getHand()[1]);
        Assert.assertNotNull(players.getSafeList(check).get(0).getHand()[0]);
        Assert.assertNotNull(players.getSafeList(check).get(0).getHand()[1]);
        Assert.assertNull(players.getSafeList(check1).get(0).getHand()[0]);
        Assert.assertNull(players.getSafeList(check1).get(0).getHand()[1]);
    }

    @Test
    public void testGetBestPlayers() throws Exception {

    }

    @Test
    public void testFetchBets() throws Exception {
        Players players=new Players();
        int limit1=9999;
        int limit2=15;
        Assert.assertEquals(players.fetchBets(limit1),0);

        Player check = players.addPlayer(id1,"bob");
        Player check1 = players.addPlayer(id2,"bob");
        check.setBet(0);
        check1.setBet(20);
        Assert.assertEquals(players.fetchBets(limit2),15);
        Assert.assertEquals(check.getBet(),0);
        Assert.assertEquals(check1.getBet(),5);

        check.setBet(10);
        check1.setBet(30);
        Assert.assertEquals(players.fetchBets(limit2),25);
        Assert.assertEquals(check.getBet(),0);
        Assert.assertEquals(check1.getBet(),15);

        check.setBet(15);
        check1.setBet(15);
        Assert.assertEquals(players.fetchBets(limit2),30);
        Assert.assertEquals(check.getBet(),0);
        Assert.assertEquals(check1.getBet(),0);

    }
    @Test
    public void testGetPlayerById() throws Exception {
        Players players=new Players();
        Assert.assertNull(players.getPlayerById(id1));

        Player check=players.addPlayer(id1,"bob");
        Assert.assertNotNull(players.getPlayerById(id1));
        Assert.assertEquals(players.getPlayerById(id1),check);

        Player check1=players.addPlayer(id2,"bob");
        Assert.assertNotNull(players.getPlayerById(id2));
        Assert.assertNotNull(players.getPlayerById(id1));
        Assert.assertEquals(players.getPlayerById(id2),check1);
        Assert.assertNotSame(players.getPlayerById(id2),check);
    }
}