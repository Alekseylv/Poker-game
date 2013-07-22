package poker.arturka;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 1:48 PM
 */
public class Players {
    private Map<Integer,Player> playerMap;
    public Players(){
        playerMap =new HashMap<Integer, Player>();
    }

    public Player addPlayer(int id){
        if(id<0){
            return null;
        }
        Player tempPlayer = new Player(id);
        tempPlayer.toggleInGame();
        playerMap.put(id, tempPlayer);
        return tempPlayer;
    }

    public Player removePlayer(int id){
        Player player=playerMap.get(id);
        if (player==null){
            return null;
        }
        player.toggleInGame();
        playerMap.remove(id);
        return player;
    }

    /**
     * Returns random player from list.
     * @return Random player.
     */
    public Player getRandomPlayer(){
        Random random=new Random();
        if (playerMap.size()<1){
            return null;
        }
        return getPlayersList().get(random.nextInt(playerMap.size()));
    }

    /**
     * Returns a player that is next in table.
     * @param player Player next to whom is the return player.
     * @return Next player to player param.
     */
    public Player getNextPlayer(Player player){
        if (player==null){
            return null;
        }
        List<Player> playerList=getPlayersList();
        for(int i=0;i< playerList.size();i++){
            if(playerList.get(i).equals(player)){
                int j=i+1;
                if (j>= playerList.size()){
                    j-= playerList.size();
                }
                while(!playerList.get(j).isInGame()){
                    if (++j>= playerList.size()){
                        j-= playerList.size();
                    }
                }
                return playerList.get(j);
            }
        }
        return null;
    }

    /**
     * Returns te current dealer.
     * @return Current dealer.
     */
    public Player getDealer(){
        for(Player player:getPlayersList()){
            if(player.isDealer()){
                return player;
            }
        }
        Player newDealer=getRandomPlayer();
        if (newDealer==null){
            return null;
        }
        newDealer.toggleDealer();
        return newDealer;
    }

    /**
     * Returns next dealer from the list.
     * If there was no previous dealer, returns random player.
     * @return Next player that has to be a dealer.
     */
    public Player nextDealer(){
        Player oldDealer;
        oldDealer=getDealer();
        if (oldDealer==null){
            return oldDealer;
        }
        oldDealer.toggleDealer();
        getNextPlayer(oldDealer).toggleDealer();
        return oldDealer;
    }

    /**
     * Gets sum of bets from all players.
     * @return Sum of all bets
     */
    public int getPot(){
        int pot=0;
        for(Player player:getPlayersList()){
            pot+=player.getBet();
        }
        return pot;
    }

    /**
     * Returns a list of players with cards on hands.
     * Returns all players that still haven't folded and
     * are still in game.
     * @return List of players with cards on hand.
     */
    public List<Player> playersLeft(){
        List<Player> tempList=new ArrayList<Player>();
        for(Player player:getPlayersList()){
            if(!player.hasFolded()&&player.isInGame()){
                tempList.add(player);
            }
        }
        return tempList;
    }

    /**
     * Returns all players in a List form.
     * @return List of all players.
     */
    public List<Player> getPlayersList(){
        return new ArrayList<Player>(playerMap.values());
    }

    /**
     * Returns List of players valid to send to client.
     * Returns List of players where all players except for safePlayer
     * have their hand replaced by {null,null}.
     * @param safePlayer The user whose cards are not wiped.
     * @return List of players, safe to ship to end user.
     */
    public List<Player> getSafeList(Player safePlayer){
        List<Player> tempList=new ArrayList<Player>();
        Player tempPlayer;
        for(Player player:getPlayersList()){
            tempPlayer=new Player(player);
            if(!tempPlayer.equals(safePlayer)){
                tempPlayer.giveCards(null,null);
            }
            tempList.add(tempPlayer);
        }
        return tempList;
    }

    /**
     * Outputs a List of players by the strength of their hand.
     * @return List of players, null if none.
     */
	public HashMap<Integer, PlayerHand> getBestPlayers() {
		HandEvaluator evaluator = new HandEvaluator(playersLeft());
		HashMap<Integer, PlayerHand> playerHandRanking =  evaluator.getPlayerHandEvaluation();
		if (!playerHandRanking.entrySet().isEmpty())
			return playerHandRanking;
		return null;
	}

    /**
     * Subtracts money from player bets, that ar smaller than given.
     * Checks all the players, if their bet is smaller than bet parameter takes it all,
     * if it is bigger than bet subtracts bet parameter from it.
     * @param bet Sets the limit.
     * @return All the money collected from players.
     */
    public int fetchBets(int bet) {
        int cash=0;
        for(Player player:getPlayersList()){
            if (player.getBet()<bet){
                cash+=player.getBet();
                player.setBet(0);
            }else{
                cash+=bet;
                player.reduceBet(bet);
            }
        }
        return cash;
    }
    public Player getPlayerById(int id){
        for(Player player:getPlayersList()){
            if (player.getId()==id){
                return player;
            }
        }
        return null;
    }
}