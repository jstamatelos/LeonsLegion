package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public class WarHand extends Hand implements Comparable {

    ArrayList deck = Deck.createNewDeck();

    WarGame war = new WarGame();

    ArrayList<Card> fullDeck = new ArrayList<Card>();
    ArrayList<Card> dealerDeck = (ArrayList<Card>) fullDeck.subList(0, 25);
    ArrayList<Card> playerDeck = (ArrayList<Card>) fullDeck.subList(26, 52);



    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
