package message.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 1:33 PM
 */
public class Player extends Observable implements Serializable {
    private int id;
    private int cash;
    public Card[] hand;
    private boolean dealer;
    private boolean fold;
    private int bet;
    private boolean inGame;
    private String nick;

    public Player(int id,String nick){
        this.id=id;
        this.cash=1000;
        this.nick=nick;
        hand=new Card[2];
        fold=false;
    }

    public Player(Player player){
        this.id=player.getId();
        this.cash=player.getCash();
        this.hand=player.getHand();
        this.fold=player.hasFolded();
        this.nick=player.getNick();
    }

    public void giveCash(int cash){
        this.cash+=cash;
    }

    public void giveCards(Card card1, Card card2){
        hand= new Card[]{card1, card2};
    }

    public int getId() {
        return id;
    }

    public int getCash() {
        return cash;
    }
    public void setCash(int cash) {
        this.cash=cash;
    }

    public int getBet(){
        return bet;
    }

    public void reduceBet(int ammount){
        bet-=ammount;
    }

    public boolean hasFolded(){
        return fold;
    }
    public boolean bet(int ammount){
        if(ammount<=cash+bet){
            cash-=ammount;
            bet+=ammount;
            return true;
        }
        return false;
    }

    public boolean isDealer(){
        return dealer;
    }

    public void toggleDealer() {
        dealer=!dealer;
    }

    public void Fold() {
        fold=true;
    }

    public void unFold(){
        fold=false;
    }

    public void setBet(int bet){
        this.bet = bet;
    }

    public Card[] getHand() {
    	return hand;
    }


    public boolean isInGame() {
        return inGame;
    }

    public void toggleInGame() {
        inGame=!inGame;
    }

    public boolean equals(Player player){
        return player.getId()==id;
    }

    public String getNick() {
        return nick;
    }

    public String handToString(){
        String output="";
        for(Card card:hand){
            output+=" ";
            if(card == null){
                output+= "NULL";
                continue;
            }
            output+=card.toSymbol();
         }
        return output;
    }
}
