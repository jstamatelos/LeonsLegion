package com.leonslegion.casino;

import com.leonslegion.casino.CardGamePackage.BlackjackHand;
import com.leonslegion.casino.CardGamePackage.Card;
import com.leonslegion.casino.CardGamePackage.Deck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

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
    public void test_IsOver21() {
        Card card1 = new Card(Card.Rank.QUEEN, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
        Card card3 = new Card(Card.Rank.EIGHT, Card.Suit.CLUBS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);


        Assert.assertEquals(true, hand.isOver21());
    }

    @Test
    public void aceEqualsOneTest() {
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
        Card card3 = new Card(Card.Rank.EIGHT, Card.Suit.CLUBS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        int expectedResult = 19;
        int actualResult = hand.getPoints();

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void splitPossibleTest() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);

        Assert.assertEquals(true, hand.splitPossible());
    }

    @Test
    public void splitNotPossibleTooManyCardsTest() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS);
        Card card3 = new Card(Card.Rank.EIGHT, Card.Suit.CLUBS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);

        Assert.assertFalse(hand.splitPossible());
    }

    @Test
    public void splitNotPossibleNotAPair() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);

        Assert.assertFalse(hand.splitPossible());

    }

    @Test
    public void compareHandsUnder21_Test() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS);

        BlackjackHand hand1 = new BlackjackHand();

        hand1.addCard(card1);
        hand1.addCard(card2);

        Card card3 = new Card(Card.Rank.KING, Card.Suit.SPADES);
        Card card4 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);

        BlackjackHand hand2 = new BlackjackHand();

        hand2.addCard(card3);
        hand2.addCard(card4);

        Assert.assertEquals(-1, hand1.compareTo(hand2));
    }

    @Test
    public void compareHandsHand1Over21_Test() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS);
        Card card3 = new Card(Card.Rank.FIVE, Card.Suit.CLUBS);

        BlackjackHand hand1 = new BlackjackHand();

        hand1.addCard(card1);
        hand1.addCard(card2);
        hand1.addCard(card3);

        Card card4 = new Card(Card.Rank.KING, Card.Suit.SPADES);
        Card card5 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);

        BlackjackHand hand2 = new BlackjackHand();

        hand2.addCard(card4);
        hand2.addCard(card5);

        System.out.println(hand1.compareTo(hand2));

        Assert.assertEquals(1, hand1.compareTo(hand2));
    }

    @Test
    public void compareHandsHand2Over21_Test() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS);


        BlackjackHand hand1 = new BlackjackHand();

        hand1.addCard(card1);
        hand1.addCard(card2);

        Card card3 = new Card(Card.Rank.FIVE, Card.Suit.CLUBS);
        Card card4 = new Card(Card.Rank.KING, Card.Suit.SPADES);
        Card card5 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);

        BlackjackHand hand2 = new BlackjackHand();

        hand2.addCard(card3);
        hand2.addCard(card4);
        hand2.addCard(card5);

        Assert.assertEquals(-1, hand1.compareTo(hand2));

    }




}
