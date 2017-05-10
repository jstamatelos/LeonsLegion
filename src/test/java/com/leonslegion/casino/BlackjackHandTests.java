package com.leonslegion.casino;

import com.sun.tools.internal.xjc.model.CAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by cameronsima on 5/9/17.
 */
public class BlackjackHandTests {

    @Test
    public void testCompare_blackJackHand() {

        BlackjackHand hand1 = new BlackjackHand();
        BlackjackHand hand2 = new BlackjackHand();

        Card card1 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);

        Card card3 = new Card(Card.Rank.TEN, Card.Suit.HEARTS);
        Card card4 = new Card(Card.Rank.SEVEN, Card.Suit.SPADES);

        hand1.addCard(card1);
        hand1.addCard(card2);
        hand2.addCard(card3);
        hand2.addCard(card4);

        int result = hand1.compareTo(hand2);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void test_Over21() {

        Card card1 = new Card(Card.Rank.QUEEN, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
        Card card3 = new Card(Card.Rank.EIGHT, Card.Suit.CLUBS);

        Card card4 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card5 = new Card(Card.Rank.TEN, Card.Suit.HEARTS);


        BlackjackHand hand1 = new BlackjackHand();
        BlackjackHand hand2 = new BlackjackHand();

        hand1.addCard(card1);
        hand1.addCard(card2);
        hand1.addCard(card3);

        hand2.addCard(card4);
        hand2.addCard(card5);

        int result = hand1.compareTo(hand2);

        Assert.assertEquals(1, result);
    }
}
