package com.leonslegion.casino.CardGamePackage;

import java.util.*;

public class Deck {

    public ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        deck = new ArrayList<Card>();
        for (Card.Rank rank: Card.Rank.values()) {
            for (Card.Suit suit: Card.Suit.values()) {
                deck.add(new Card(rank, suit));
            }

        }
    }


    public String toString () {
        String deckString = (deck.get(0)).getRank().toString() + " of " + (deck.get(0)).getSuit().toString();
        for (int i = 1; i < deck.size(); i++) {
            deckString += ", " + (deck.get(i)).getRank().toString() + " of " + (deck.get(i)).getSuit().toString();
        }
        return deckString;
    }



    public void shuffleDeck () {
        Collections.shuffle(deck);
    }



    public Card dealCard() {
        return deck.remove(deck.size() - 1);
    }



}
