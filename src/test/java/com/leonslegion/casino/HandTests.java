package com.leonslegion.casino;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by cameronsima on 5/9/17.
 */
public class HandTests {

    @Test
    public void testCompare_blackJackHand() {

        ArrayList deck = Deck.createNewDeck();

        BlackjackHand hand1 = new BlackjackHand();
        BlackjackHand hand2 = new BlackjackHand();

        for (int i = 0; i < 4; i++) {
            Card card1 = Deck.dealCard(deck);
            Card card2 = Deck.dealCard(deck);
            hand1.addCard(card1);
            hand2.addCard(card2);

        }

        hand1.compareTo(hand2);



    }
}
