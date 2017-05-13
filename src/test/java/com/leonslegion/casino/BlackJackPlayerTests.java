package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.CardGamePackage.BlackjackHand;
import com.leonslegion.casino.CardGamePackage.BlackjackPlayer;
import com.leonslegion.casino.CardGamePackage.Card;
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

        Card cardInHand1 = player.getSplitHands().get(0).getHand().get(0);
        Card cardInHand2 = player.getSplitHands().get(1).getHand().get(0);

        Assert.assertTrue(player.hasSplitHands());

        // Player has exactly two hands
        Assert.assertEquals(2, player.getSplitHands().size());

        // Cards have equal value
        Assert.assertEquals(cardInHand1.getRank(), cardInHand2.getRank());

        // Cards in respective hands equal hands initially dealt

        Assert.assertEquals(cardInHand1, card1);
        Assert.assertEquals(cardInHand2, card2);

    }
}
