package poker.arturka;

/**
 * Created with IntelliJ IDEA.
 * User: mordavolt
 * Date: 7/16/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */

public class Card {
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

}
