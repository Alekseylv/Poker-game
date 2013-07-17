package poker.arturka;

import server.Room;

public class Game implements Runnable {

    private Deck deck;
    private Players players;
    private int blind;
    private int pot;
    private boolean smallPots;

    public Game(){
        deck=new Deck();
        players=new Players();
        blind=30;
        pot=0;
    }

    public void run() {
        for(int id: Room.getUsers()){
            players.addPlayer(id);
        }
        players.nextDealer();
        Player nextPlayer=players.getNextPlayer(players.getDealer());
        if (!nextPlayer.bet(blind/2)){
            nextPlayer.bet(nextPlayer.getCash());
        }
        nextPlayer=players.getNextPlayer(nextPlayer);
        if (!nextPlayer.bet(blind)){
            nextPlayer.bet(nextPlayer.getCash());
        }
        deck.shuffleDeck();
        Player currentPlayer=players.getNextPlayer(players.getDealer());
        do{
            currentPlayer.giveCards(deck.getTopCard(),deck.getTopCard());
        }while(players.getDealer()!=currentPlayer);
    }
}
