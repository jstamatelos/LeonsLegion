package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.CardGamePackage.BlackjackDealer;
import com.leonslegion.casino.CardGamePackage.BlackjackHand;
import com.leonslegion.casino.CardGamePackage.Card;
import com.leonslegion.casino.CardGamePackage.Deck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cameronsima on 5/14/17.
 */
public class BlackjackDealerTests {

    BlackjackDealer dealer;
    Deck deck;

    @Before
    public void setUp() {
        Account acct = Account.AccountFactory.createAccountWithName("Dealer");
        dealer = new BlackjackDealer(acct);
        deck = new Deck();
    }

    @Test
    public void dealerTurnStaysTest() {

        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);

        dealer.setHand(hand);

        dealer.takeTurn(deck);

        // Dealer has 17 and should stay.
        Assert.assertEquals(17, dealer.getHand().getPoints());

    }

    @Test
    public void dealerTurnHitsTest() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);

        dealer.setHand(hand);

        dealer.takeTurn(deck);

        // Dealer should hit at least once
        Assert.assertTrue(dealer.getHand().getPoints() > 15);
    }




}
