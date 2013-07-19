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
        List<SendWinnerListCommand.Tuple> winners =new ArrayList<SendWinnerListCommand.Tuple>();
        if (players.playersLeft().size()>1){
            List<Player> bestPlayers=players.getBestPlayers();
            int i=0;
            Player currentWinner;
            while(players.getPot()>0){
                currentWinner=bestPlayers.get(i++);
                int betsForWinner=players.fetchBets(currentWinner.getBet());
                currentWinner.giveCash(betsForWinner);
                winners.add(new SendWinnerListCommand.Tuple(currentWinner.getId(),betsForWinner));
            }
        }else{
            players.playersLeft().get(0).giveCash(players.getPot());
            winners.add(new SendWinnerListCommand.Tuple(players.playersLeft().get(0).getId(), players.getPot()));
        }
        room.Broadcast(new SendWinnerListCommand(winners));
        for(Player currentPlayer: players.getPlayersList()){
            if(currentPlayer.isInGame()&&currentPlayer.getCash()==0){
                currentPlayer.toggleInGame();
            }
            room.Broadcast(new ChangeCashCommand(currentPlayer.getId(),currentPlayer.getCash()));
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("we are online");
//        for(int id: Room.getUsers()){
//            players.addPlayer(id);
//        }
        players.getDealer();
        System.out.println("we know dealer");
        for(Player player:players.getPlayersList()){
            //room.sendToUser(player.getId(), new SetIDCommand(player.getId()));
            System.out.println("id sent");

            room.sendToUser(player.getId(), new SendPlayerListCommand(players.getSafeList(player)));
            System.out.println("ulist sent");
        }
        while(players.playersLeft().size()>1){
            Player oldDealer=players.nextDealer();
            room.Broadcast(new ChangeDealersCommand(oldDealer.getId(),players.getDealer().getId()));
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
                room.sendToUser(currentPlayer.getId(),new SendCardsCommand(currentPlayer.getId(),currentPlayer.getHand()[0],currentPlayer.getHand()[1]));
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
                        if (move==null){
                            move=new ClientResponse(ClientTurn.EXIT,1);
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
                                better.toggleInGame();
                                room.Broadcast(new PlayerMoveCommand(new PlayerMove(better.getId(),ClientTurn.EXIT,better.getBet(),better.getCash())));
                                //players.removePlayer(better.getId());
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
