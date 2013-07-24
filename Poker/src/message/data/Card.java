package message.data;

import java.io.Serializable;
import java.util.ArrayList;

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

    public String toSymbol(){
        String output="";
        switch (rank){
            case TWO:
                output="2";
                break;
            case THREE:
                output="3";
                break;
            case FOUR:
                output="4";
                break;
            case FIVE:
                output="5";
                break;
            case SIX:
                output="6";
                break;
            case SEVEN:
                output="7";
                break;
            case EIGHT:
                output="8";
                break;
            case NINE:
                output="9";
                break;
            case TEN:
                output="10";
                break;
            case JACK:
                output="J";
                break;
            case QUEEN:
                output="Q";
                break;
            case KING:
                output="K";
                break;
            case ACE:
                output="A";
                break;
        }
        switch (suit){
            case DIAMONDS:
                output+="♦";
                break;
            case HEARTS:
                output+="♥";
                break;
            case CLUBS:
                output+="♣";
                break;
            case SPADES:
                output+="♠";
                break;
        }
        return output;
    }

    public String toString(){
        String output="";
        switch (rank){
            case TWO:
                output = "2_of_";
                break;
            case THREE:
                output = "3_of_";
                break;
            case FOUR:
                output = "4_of_";
                break;
            case FIVE:
                output = "5_of_";
                break;
            case SIX:
                output = "6_of_";
                break;
            case SEVEN:
                output = "7_of_";
                break;
            case EIGHT:
                output = "8_of_";
                break;
            case NINE:
                output = "9_of_";
                break;
            case TEN:
                output = "10_of_";
                break;
            case JACK:
                output = "jack_of_";
                break;
            case QUEEN:
                output = "queen_of_";
                break;
            case KING:
                output = "king_of_";
                break;
            case ACE:
                output = "ace_of_";
                break;
        }
        switch (suit){
            case DIAMONDS:
                output+="diamonds";
                break;
            case HEARTS:
                output+="hearts";
                break;
            case CLUBS:
                output+="clubs";
                break;
            case SPADES:
                output+="spades";
                break;
        }
        return output;
    }

}
