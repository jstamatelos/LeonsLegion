package com.leonslegion.casino;

import java.util.*;

public class Deck {

    private Deck() {}

    public static ArrayList<Card> createNewDeck() {
        ArrayList<Card> newDeck = new ArrayList<Card>();
        for (Card.Rank rank: Card.Rank.values()) {
            for (Card.Suit suit: Card.Suit.values()) {
                newDeck.add(new Card(rank, suit));
            }

        }
        return newDeck;
    }


    public static String printDeckToString (ArrayList<Card> cardDeck) {
        String deckString = (cardDeck.get(0)).getRank().toString() + " of " + (cardDeck.get(0)).getSuit().toString();
        for (int i = 1; i < cardDeck.size(); i++) {
            deckString += ", " + (cardDeck.get(i)).getRank().toString() + " of " + (cardDeck.get(i)).getSuit().toString();
        }
        return deckString;
    }


    public static void shuffleDeck (ArrayList<Card> cardDeck) {
        Collections.shuffle(cardDeck);
    }
}
