package poker.arturka;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Deck {
    private Card[] deck=new Card[52];
    private int currentCard;
    private Random random;

    public Deck(){
        for(int i=0;i<52;i++){
            deck[i]=new Card(Card.Suit.values()[i % 13], Card.Rank.values()[i % 4]);
            currentCard=0;
            random = new Random();
        }
    }

    public Card getTopCard(){
        return deck[currentCard++];
    }

    public void shuffleDeck(){
        int firstNum;
        int secondNum;
        for(int i=0;i<100;i++){
            firstNum=random.nextInt(52);
            secondNum=random.nextInt(52);
            Card tempCard;
            tempCard=deck[firstNum];
            deck[firstNum]=deck[secondNum];
            deck[secondNum]=tempCard;
        }
        currentCard=0;
    }
}
