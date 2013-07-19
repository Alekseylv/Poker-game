package poker.arturka;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 10:51 AM
 */

public class Card implements Serializable{
    public enum Suit{
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }
    public enum Rank{
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank){
        this.suit=suit;
        this.rank=rank;
    }
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean equals(Card card){
        return ((card.getRank()==rank)&&(card.getSuit()==suit));
    }

}
