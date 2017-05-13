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
    //TODO implement the toStrings to make these nicer
    public enum Rank {
        TWO {
        public String toString(){
            return "2";
            }
        },
        THREE {
            public String toString(){
                return "3";
            }
        }
        , FOUR{
            public String toString(){
                return "4";
            }
        }
        , FIVE{
            public String toString(){
                return "5";
            }
        }
        , SIX{
            public String toString(){
                return "6";
            }
        }
        , SEVEN{
            public String toString(){
                return "7";
            }
        }
        , EIGHT{
            public String toString(){
                return "8";
            }
        }
        , NINE{
            public String toString(){
                return "9";
            }
        }
        , TEN{
            public String toString(){
                return "10";
            }
        }
        , JACK {
            public String toString(){
                return "J";
            }
        }
        , QUEEN{
            public String toString(){
                return "Q";
            }
        }
        , KING{
            public String toString(){
                return "K";
            }
        }
        , ACE{
            public String toString(){
                return "A";
            }
        }
    }

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
        return String.format("|%s " +
                "" +
                "%s|", rank.toString(), suit.toString());
    }

}
