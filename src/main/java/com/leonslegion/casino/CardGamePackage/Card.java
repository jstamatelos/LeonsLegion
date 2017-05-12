package com.leonslegion.casino.CardGamePackage;

/**
 * Created by markbrown on 5/9/17.
 */
public class Card {



    private Rank rank;
    private Suit suit;



    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }



    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }



    public enum Suit {
        SPADES {
            public String toString() {
                return "\u2660";
            }
        },
        HEARTS{
            public String toString() {
                return "\u2665";
            }
        },
        DIAMONDS{
            public String toString() {
                return "\u2666";
            }
        },
        CLUBS{
            public String toString() {
                return "\u2663";
            }
        }
    }
    public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}

    public int getPointValue() {
        if(rank.ordinal() < 8) {
            return rank.ordinal() + 2;
        } else if(rank.ordinal() < 12) {
            return 10;
        } else {
            return 11;
        }
    }

    public String toString() {
        return String.format("|%s| of |%s|", rank.name(), suit.toString());
    }

}
