package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by cameronsima on 5/9/17.
 */
public abstract class Hand {

    private ArrayList<Card> hand = new ArrayList<Card>();

    public Hand() {}

    public Hand(ArrayList<Card> cards) {
        hand = cards;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void addCards(ArrayList<Card> cards) {
        hand.addAll(cards);
    }


}
