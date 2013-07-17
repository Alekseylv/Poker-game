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
        playerList.put(id,tempPlayer);
    }

    public void removePlayer(int id){
        playerList.remove(id);
    }

    public Player getRandomPlayer(){
        Random random=new Random();
        return playerList.get(random.nextInt(playerList.size()));
    }

    public Player getNextPlayer(Player player){
        for(int i=0;i<playerList.size();i++){
            if(playerList.get(i).equals(player)){
                return playerList.get((i+1)<playerList.size()?i+1:0);
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
            playerList.get(i).setCash(0);
        }
        return pot;
    }

    public List<Player> playersLeft(){
        List<Player> tempList=new ArrayList<Player>();
        for(int i=0;i<playerList.size();i++){
            if(!playerList.get(i).hasFolded()){
                tempList.add(playerList.get(i));
            }
        }
        return tempList;
    }

    public List<Player> allInPlayers(){
        List<Player> tempList=new ArrayList<Player>();
        for(int i=0;i<playerList.size();i++){
            if(!playerList.get(i).hasFolded()&&playerList.get(i).getCash()==0){
                tempList.add(playerList.get(i));
            }
        }
        return tempList;
    }

}