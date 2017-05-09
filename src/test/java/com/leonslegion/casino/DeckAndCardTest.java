package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;


public class DeckAndCardTest {

    @Test
    public void testMakeDeck() {
        //Given:
        int expectedOutput = 52;
        ArrayList<Card> newDeck = Deck.createNewDeck();

        //When:
        int actualOutput = newDeck.size();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);

    }

    @Test
    public void testAllCardsInDeck() {
        //Given:
        String expectedOutput = "TWO of SPADES, TWO of HEARTS, TWO of DIAMONDS, TWO of CLUBS, THREE of SPADES, THREE of HEARTS, THREE of DIAMONDS, THREE of CLUBS, FOUR of SPADES, FOUR of HEARTS, FOUR of DIAMONDS, FOUR of CLUBS, FIVE of SPADES, FIVE of HEARTS, FIVE of DIAMONDS, FIVE of CLUBS, SIX of SPADES, SIX of HEARTS, SIX of DIAMONDS, SIX of CLUBS, SEVEN of SPADES, SEVEN of HEARTS, SEVEN of DIAMONDS, SEVEN of CLUBS, EIGHT of SPADES, EIGHT of HEARTS, EIGHT of DIAMONDS, EIGHT of CLUBS, NINE of SPADES, NINE of HEARTS, NINE of DIAMONDS, NINE of CLUBS, TEN of SPADES, TEN of HEARTS, TEN of DIAMONDS, TEN of CLUBS, JACK of SPADES, JACK of HEARTS, JACK of DIAMONDS, JACK of CLUBS, QUEEN of SPADES, QUEEN of HEARTS, QUEEN of DIAMONDS, QUEEN of CLUBS, KING of SPADES, KING of HEARTS, KING of DIAMONDS, KING of CLUBS, ACE of SPADES, ACE of HEARTS, ACE of DIAMONDS, ACE of CLUBS";
        ArrayList<Card> newDeck = Deck.createNewDeck();

        //When:
        String actualOutput = Deck.printDeckToString(newDeck);

        //Then:
        Assert.assertTrue(expectedOutput.equals(actualOutput));
    }

    @Test
    public void testShuffleDeck() {
        //Given:
        String expectedOutputIfNotShuffled = "TWO of SPADES, TWO of HEARTS, TWO of DIAMONDS, TWO of CLUBS, THREE of SPADES, THREE of HEARTS, THREE of DIAMONDS, THREE of CLUBS, FOUR of SPADES, FOUR of HEARTS, FOUR of DIAMONDS, FOUR of CLUBS, FIVE of SPADES, FIVE of HEARTS, FIVE of DIAMONDS, FIVE of CLUBS, SIX of SPADES, SIX of HEARTS, SIX of DIAMONDS, SIX of CLUBS, SEVEN of SPADES, SEVEN of HEARTS, SEVEN of DIAMONDS, SEVEN of CLUBS, EIGHT of SPADES, EIGHT of HEARTS, EIGHT of DIAMONDS, EIGHT of CLUBS, NINE of SPADES, NINE of HEARTS, NINE of DIAMONDS, NINE of CLUBS, TEN of SPADES, TEN of HEARTS, TEN of DIAMONDS, TEN of CLUBS, JACK of SPADES, JACK of HEARTS, JACK of DIAMONDS, JACK of CLUBS, QUEEN of SPADES, QUEEN of HEARTS, QUEEN of DIAMONDS, QUEEN of CLUBS, KING of SPADES, KING of HEARTS, KING of DIAMONDS, KING of CLUBS, ACE of SPADES, ACE of HEARTS, ACE of DIAMONDS, ACE of CLUBS";
        System.out.println(expectedOutputIfNotShuffled);
        ArrayList<Card> newDeck = Deck.createNewDeck();
        Deck.shuffleDeck(newDeck);

        //When:
        String actualOutput = Deck.printDeckToString(newDeck);
        System.out.println(actualOutput);

        //Then:
        Assert.assertFalse(expectedOutputIfNotShuffled.equals(actualOutput));

    }

    @Test
    public void testDealCard() {
        //Given:
        ArrayList<Card> newDeck = Deck.createNewDeck();

        //When:
        Deck.dealCard(newDeck);

        //Then:
        Assert.assertTrue(newDeck.size() == 51);

    }


}
