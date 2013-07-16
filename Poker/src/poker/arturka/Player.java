package poker.arturka;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 1:33 PM
 */
public class Player {
    private int id;
    private int cash;
    private Card[] hand;
    private boolean dealer;
    int pot;

    public Player(int id){
        this.id=id;
        this.cash=1000;
        hand=new Card[2];
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

    public boolean bet(int ammount){
        if(ammount<cash){
            cash-=ammount;
            pot+=ammount;
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
}
