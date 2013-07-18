package poker.arturka;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 1:48 PM
 */
public class Players {
    private Map<Integer,Player> playerList;
    public Players(){
        playerList=new HashMap<Integer, Player>();
    }

    public void addPlayer(int id){
        Player tempPlayer = new Player(id);
        tempPlayer.toggleInGame();
        playerList.put(id,tempPlayer);
    }

    public void removePlayer(int id){
        playerList.get(id).toggleInGame();
        playerList.remove(id);
    }

    public Player getRandomPlayer(){
        Random random=new Random();
        return playerList.get(random.nextInt(playerList.size()));
    }

    public Player getNextPlayer(Player player){
        for(int i=0;i<playerList.size();i++){
            if(playerList.get(i).equals(player)){
                int j=i+1;
                if (j>=playerList.size()){
                    j-=playerList.size();
                }
                while(!playerList.get(j).isInGame()){
                    if (++j>=playerList.size()){
                        j-=playerList.size();
                    }
                }
                return playerList.get(j);
            }
        }
        return null;
    }

    public Player getDealer(){
        for(int i=0;i<playerList.size();i++){
            if(playerList.get(i).isDealer()){
                return playerList.get(i);
            }
        }
        return null;
    }

    public void nextDealer(){
        if (getDealer()!=null){
            getDealer().toggleDealer();
            getNextPlayer(getDealer()).toggleDealer();
        }else{
            getRandomPlayer().toggleDealer();
        }
    }

    public int getPot(){
        int pot=0;
        for(int i=0;i<playerList.size();i++){
            pot+=playerList.get(i).getBet();
        }
        return pot;
    }

    public List<Player> playersLeft(){
        List<Player> tempList=new ArrayList<Player>();
        for(Player player:getPlayersList()){
            if(!player.hasFolded()&&player.isInGame()){
                tempList.add(player);
            }
        }
        return tempList;
    }

    public List<Player> allInPlayers(){
        List<Player> tempList=new ArrayList<Player>();
        for(Player player:getPlayersList()){
            if(!player.hasFolded()&&player.getCash()==0&&player.isInGame()){
                tempList.add(player);
            }
        }
        return tempList;
    }

    public List<Player> getPlayersList(){
        List<Player> tempList=new ArrayList<Player>();
        tempList.addAll(tempList);
        return tempList;
    }

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

	public List<Player> getBestPlayers() {
		HandEvaluator evaluator = new HandEvaluator(playersLeft());
		List<Player> playerHandRanking =  evaluator.getPlayerHandEvaluation();
		if (!playerHandRanking.isEmpty())
			return playerHandRanking;
		return null;
	}

}