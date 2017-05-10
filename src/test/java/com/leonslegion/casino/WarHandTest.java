package com.leonslegion.casino;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
public class WarHandTest extends CardComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Card c1 = (Card) o1;
        Card c2 = (Card) o2;
        return c1.getRank().ordinal() - c2.getRank().ordinal();
    }

    @Test
    public void compareDealerCardNotPlayerCard() {

        WarGame war = new WarGame();
        ArrayList<Card> fullDeck = new ArrayList<Card>();
        ArrayList<Card> dealerDeck = (ArrayList<Card>) fullDeck.subList(0, 25);
        ArrayList<Card> playerDeck = (ArrayList<Card>) fullDeck.subList(26, 52);

        // When - a card is pulled from each deck and compared





    // Then - dealer card is different than player card, card is returned

}


    @Test
    public void dealerCardMatchesPlayerCard() {

        WarGame war = new WarGame();
        ArrayList<Card> fullDeck = new ArrayList<Card>();
        ArrayList<Card> dealerDeck = (ArrayList<Card>) fullDeck.subList(0,25);
        ArrayList<Card> playerDeck = (ArrayList<Card>) fullDeck.subList(26,52);

        // When - a card is pulled from each deck and compared



        // Then - dealer card is the same than player card, card is returned
    }

}