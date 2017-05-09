package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public abstract class Hand {

    //private ArrayList<Card> cards;


    public Hand() {}

    public Hand(ArrayList<Card> someCards) {
        cards = someCards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }


}
