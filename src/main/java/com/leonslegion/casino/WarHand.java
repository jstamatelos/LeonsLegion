package com.leonslegion.casino;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by cameronsima on 5/9/17.
 */
public class WarHand extends Hand implements Comparable {

    WarGame war = new WarGame();



    public ArrayList<Card> dealerDeck = new ArrayList<>();
    public ArrayList<Card> playerDeck = new ArrayList<>();


    // Needed for implementation
    @Override
    public int compareTo(Object otherHand) {
        return 0;
    }



}
