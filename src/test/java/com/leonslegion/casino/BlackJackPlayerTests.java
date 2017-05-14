package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.CardGamePackage.BlackjackHand;
import com.leonslegion.casino.CardGamePackage.BlackjackPlayer;
import com.leonslegion.casino.CardGamePackage.Card;
import com.leonslegion.casino.CardGamePackage.Deck;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cameronsima on 5/13/17.
 */
public class BlackJackPlayerTests {

    Account acct = Account.AccountFactory.createAccountWithName("Cameron");
    BlackjackPlayer player = new BlackjackPlayer(acct);


    @Test
    public void splitTest() {
        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);

        BlackjackHand hand = new BlackjackHand();

        hand.addCard(card1);
        hand.addCard(card2);

        player.setHand(hand);
        player.split();

        Card cardInHand1 = player.getSplitHands().get(0).getCards().get(0);
        Card cardInHand2 = player.getSplitHands().get(1).getCards().get(0);

        Assert.assertTrue(player.hasSplitHands());

        // Player has exactly two hands
        Assert.assertEquals(2, player.getSplitHands().size());

        // Cards have equal value
        Assert.assertEquals(cardInHand1.getRank(), cardInHand2.getRank());

        // Cards in respective hands equal hands initially dealt

        Assert.assertEquals(cardInHand1, card1);
        Assert.assertEquals(cardInHand2, card2);

    }

    @Test
    public void hitTest() {
        Deck deck = new Deck();
        BlackjackHand hand = new BlackjackHand();
        player.setHand(hand);
        player.hit(deck);

        Assert.assertEquals(1, player.getHand().getCards().size());

        player.hit(deck);

        Assert.assertEquals(2, player.getHand().getCards().size());

    }

    @Test
    public void hitSplitTest() {

        Card card1 = new Card(Card.Rank.KING, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS);
        Deck deck = new Deck();
        deck.shuffleDeck();
        BlackjackHand hand = new BlackjackHand();
        hand.addCard(card1);
        hand.addCard(card2);
        player.setHand(hand);
        player.split();
        player.hitSplitHand(2, deck);

        Assert.assertTrue(player.hasSplitHands());

        // Player has exactly two hands
        Assert.assertEquals(2, player.getSplitHands().size());

        // Player has 2 cards in hand 2
        Assert.assertEquals(2, player.getSplitHands().get(1).getCards().size());

        }


    @Test
    public void playerBalanceTest() {
        Assert.assertEquals(1000.0, player.getBalance(), 0.001);
    }

    @Test
    public void deductBetTest() {
        int bet = 5;
        player.deductBetFromAccount(bet);
        Assert.assertEquals(995.0, player.getBalance(), 0.001);
    }

    @Test
    public void addBetTest() {
        int bet = 200;
        player.addBetToAccount(bet);
        Assert.assertEquals(1300.0, player.getBalance(), 0.001);
    }
}

