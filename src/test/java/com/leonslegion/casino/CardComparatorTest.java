package com.leonslegion.casino;

import com.leonslegion.casino.CardGamePackage.Card;
import com.leonslegion.casino.CardGamePackage.CardComparator;
import com.leonslegion.casino.CardGamePackage.Deck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by danzygmund-felt on 5/9/17.
 */
public class CardComparatorTest {

    @Before
    public void initialize() {
        Card card0 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card3 = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS);

        Deck deck = new Deck();
    }

    /*
    This is a deliberately failing test so that it's easy to verify
    that a sorted deck will print 2s, then 3s, and so on.
     */
    @Test
    public void sortDeckTest() {
        Deck deck = new Deck();
        Collections.sort(deck.deck, new CardComparator());
        String expected = "TWO of ♠, TWO of ♥, TWO of ♦, TWO of ♣, THREE of ♠, THREE of ♥, THREE of ♦, THREE of ♣, FOUR of ♠, FOUR of ♥, FOUR of ♦, FOUR of ♣, FIVE of ♠, FIVE of ♥, FIVE of ♦, FIVE of ♣, SIX of ♠, SIX of ♥, SIX of ♦, SIX of ♣, SEVEN of ♠, SEVEN of ♥, SEVEN of ♦, SEVEN of ♣, EIGHT of ♠, EIGHT of ♥, EIGHT of ♦, EIGHT of ♣, NINE of ♠, NINE of ♥, NINE of ♦, NINE of ♣, TEN of ♠, TEN of ♥, TEN of ♦, TEN of ♣, JACK of ♠, JACK of ♥, JACK of ♦, JACK of ♣, QUEEN of ♠, QUEEN of ♥, QUEEN of ♦, QUEEN of ♣, KING of ♠, KING of ♥, KING of ♦, KING of ♣, ACE of ♠, ACE of ♥, ACE of ♦, ACE of ♣";

        String actual = deck.toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void comparatorTestOne() {
        int expected = 0;
        CardComparator comp = new CardComparator();
        Card card0 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS);

        int actual = comp.compare(card0, card1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void comparatorTestTwo() {
        int expected = 1;
        CardComparator comp = new CardComparator();
        Card card0 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card1 = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS);

        int actual = comp.compare(card0, card1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void comparatorTestThree() {
        int expected = -1;
        CardComparator comp = new CardComparator();
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card0 = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS);

        int actual = comp.compare(card0, card1);

        Assert.assertEquals(expected, actual);
    }

}
