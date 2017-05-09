package com.leonslegion.casino;

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

    public enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS}
    public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}

    public String printCardToString(Card card) {
        return card.rank + " of " + card.suit;
    }
}
