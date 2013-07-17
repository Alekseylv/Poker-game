package poker.arturka;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 1:33 PM
 */
public class Player extends Observable {
    private int id;
    private int cash;
    private Card[] hand;
    private boolean dealer;
    private boolean fold;
    private int bet;

    public Player(int id){
        this.id=id;
        this.cash=1000;
        hand=new Card[2];
        fold=false;
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

    public int getBet(){
        return bet;
    }

    public boolean hasFolded(){
        return fold;
    }
    public boolean bet(int ammount){
        if(ammount<cash){
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
    public void toggleFold() {
        fold=!fold;
    }
}
