package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Assert.*;

/**
 * Created by jarrydstamatelos on 5/9/17.
 */
//public class WarHandTest {
//
//    @Test
//    public void compareDealerCardNotPlayerCard() {
//
//        ArrayList deck = Deck.createNewDeck();
//
//        WarGame war = new WarGame();
//
//        ArrayList<Card> dealerDeck = new ArrayList<Card>();
//        ArrayList<Card> playerDeck = new ArrayList<Card>();
//
//        WarHand dealerHand = new WarHand();
//        WarHand playerHand = new WarHand();
//
//        Card card1 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
//        Card card2 = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS);
//
//
//        dealerHand.addCard(card1);
//        playerHand.addCard(card2);
//
//
//        int result = dealerHand.compareTo(playerHand);
//        Assert.assertEquals(-1, result);
//}
//
//
//    @Test
//    public void dealerCardMatchesPlayerCard() {
//
//        WarGame war = new WarGame();
//
//        ArrayList<Card> dealerDeck = new ArrayList<Card>();
//        ArrayList<Card> playerDeck = new ArrayList<Card>();
//
//
//        WarHand dealerHand = new WarHand();
//        WarHand playerHand = new WarHand();
//
//        Card card1 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
//        Card card2 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
//
//
//        dealerHand.addCard(card1);
//        playerHand.addCard(card2);
//
//
//        int result = dealerHand.compareTo(playerHand);
//        Assert.assertEquals(0, result);
//        Assert.assertEquals(card1,card2);
//
//    }
//
//}
