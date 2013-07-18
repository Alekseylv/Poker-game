package poker.arturka;

import commands.*;
import message.data.ClientResponse;
import message.data.ClientTurn;
import message.data.PlayerMove;
import poker.server.Room;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {

    private Deck deck;
    private Players players;
    private int blind;
    private List<Card> table;
    private int maxBet;
    private int state;
    private Room room;

    public Game(Room room){
        deck=new Deck();
        players=new Players();
        blind=30;
        maxBet=0;
        table=new ArrayList<Card>();
        state=0;
        this.room=room;
        for(int id: room.getUsers()){
            players.addPlayer(id);
        }
    }

    private void endGame(){
        if (players.playersLeft().size()>1){
            Player bestPlayer = players.getBestPlayer();
            if(bestPlayer.getBet()==maxBet){
                bestPlayer.giveCash(players.getPot());
            }else{
                int miniPot=bestPlayer.getBet()*(players.playersLeft().size()-1);
                int fullPot=players.getPot();
                bestPlayer.giveCash(miniPot);
                List<Player> getMoneyBack=players.getPlayersList();
                getMoneyBack.remove(bestPlayer);
                for(Player player:getMoneyBack){
                    player.giveCash((fullPot-miniPot)/getMoneyBack.size());
                }
            }
        }else{
            players.playersLeft().get(0).giveCash(players.getPot());
        }
        for(Player currentPlayer: players.getPlayersList()){
            if(currentPlayer.isInGame()&&currentPlayer.getCash()==0){
                currentPlayer.toggleInGame();
            }
        }
    }

    private boolean raiseBet(int playerPot){
        if (playerPot>maxBet){
            maxBet=playerPot;
            return true;
        }else{
            return false;
        }
    }

    public void run() {
//        for(int id: Room.getUsers()){
//            players.addPlayer(id);
//        }
        for(Player player:players.getPlayersList()){
            room.sendToUser(player.getId(), new SetIDCommand(player.getId()));
            room.sendToUser(player.getId(), new SendPlayerListCommand(players.getSafeList(player)));
        }
        while(players.playersLeft().size()>1){
            players.nextDealer();
            //todo send next dealer
            Player nextPlayer=players.getNextPlayer(players.getDealer());
            if (!nextPlayer.bet(blind/2)){
                nextPlayer.bet(nextPlayer.getCash());
            }
            raiseBet(nextPlayer.getBet());
            room.Broadcast(new PlayerMoveCommand(new PlayerMove(nextPlayer.getId(),ClientTurn.BLIND,nextPlayer.getBet(),nextPlayer.getCash())));
            nextPlayer=players.getNextPlayer(nextPlayer);
            if (!nextPlayer.bet(blind)){
                nextPlayer.bet(nextPlayer.getCash());
            }
            raiseBet(nextPlayer.getBet());
            room.Broadcast(new PlayerMoveCommand(new PlayerMove(nextPlayer.getId(),ClientTurn.BLIND,nextPlayer.getBet(),nextPlayer.getCash())));
            deck.shuffleDeck();
            for(Player currentPlayer: players.getPlayersList()){
                currentPlayer.unFold();
                currentPlayer.giveCards(deck.getTopCard(),deck.getTopCard());
            }
            Player firstBetter=players.getNextPlayer(nextPlayer);
            Player better=firstBetter;
            ClientResponse move;
            while(state<4){
                do{
                    if(!better.hasFolded()&&better.getCash()>0){
                        if(better.getBet()==maxBet){
                            move = room.sendToUser(better.getId(),new FRCheckCommand());
                        }else{
                            move = room.sendToUser(better.getId(),new FRCallCommand());
                        }
                        switch(move.turn){
                            case FOLD:
                                better.Fold();
                                room.Broadcast(new PlayerMoveCommand(new PlayerMove(better.getId(),ClientTurn.FOLD,better.getBet(),better.getCash())));
                                continue;
                            case CHECK:
                                room.Broadcast(new PlayerMoveCommand(new PlayerMove(better.getId(),ClientTurn.CHECK,better.getBet(),better.getCash())));
                                continue;
                            case CALL:
                                better.bet(maxBet-better.getBet());
                                room.Broadcast(new PlayerMoveCommand(new PlayerMove(better.getId(),ClientTurn.CALL,better.getBet(),better.getCash())));
                                continue;
                            case RAISE:
                                better.bet(move.getBet());
                                raiseBet(better.getBet());
                                firstBetter=better;
                                room.Broadcast(new PlayerMoveCommand(new PlayerMove(better.getId(),ClientTurn.RAISE,better.getBet(),better.getCash())));
                                continue;
                            case EXIT:
                                better.Fold();
                                room.Broadcast(new PlayerMoveCommand(new PlayerMove(better.getId(),ClientTurn.EXIT,better.getBet(),better.getCash())));
                                players.removePlayer(better.getId());
                        }
                        if(players.playersLeft().size()<2){
                            endGame();
                        }
                    }
                    better=players.getNextPlayer(better);
                }while (better!=firstBetter);
                switch (state){
                    case 0:
                        table.add(deck.getTopCard());
                        table.add(deck.getTopCard());
                        table.add(deck.getTopCard());
                        room.Broadcast(new FlopCommand(table.get(0),table.get(1),table.get(2)));
                        state++;
                        break;
                    case 1:
                        deck.getTopCard();
                        table.add(deck.getTopCard());
                        room.Broadcast(new TurnRiverCommand(table.get(3), TurnRiverCommand.RorT.TURN));
                        state++;
                        break;
                    case 2:
                        deck.getTopCard();
                        table.add(deck.getTopCard());
                        room.Broadcast(new TurnRiverCommand(table.get(4), TurnRiverCommand.RorT.RIVER));
                        state++;
                        break;
                    default:
                        state++;
                }
            }
        endGame();
        }
    }
}
