package com.leonslegion.casino;

import com.leonslegion.casino.CardGamePackage.Card;
import com.leonslegion.casino.CardGamePackage.CardComparator;
import com.leonslegion.casino.CardGamePackage.Deck;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jarrydstamatelos on 5/10/17.
 */

public class WarGameTest {
    @Test
    public void startWarGame()  {
    }

    @Test
    public void createWarPlayer()  {
    }

    @Test
    public void placeBet() {
    }

    @Test
    public void setDealerCard() {
    }

    @Test
    public void setplayerCard() {
    }

    @Test
    public void determineWinner() {
    }

    @Test
    public void testDealerShowCard() {

        Deck dealerDeck = new Deck();
        Card dealerCard = dealerDeck.dealCard();
        Card expected = dealerCard;
        Card actual = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        Assert.assertEquals(actual.toString(),expected.toString() );


    }


    @Test
    public void testPickHigherValue()  {


        CardComparator comp = new CardComparator();

        Card card0 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);


        Assert.assertTrue("Card zero is has greater point value than card one",
                card0.getPointValue() > card1.getPointValue());


    }
    @Test
    public void testDealerAndPlayerShowSameCard() {

        int expected = 0;

        CardComparator comp = new CardComparator();
        Card card0 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        int actual = comp.compare(card0, card1);
        Console.printDouble(actual);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testDealerAndPlayerShowDifferentCard() {

        int expected = 0;

        CardComparator comp = new CardComparator();
        Card card0 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        int actual = comp.compare(card0, card1);

        Assert.assertNotEquals("These cards should not be same",expected, actual);
    }

}

