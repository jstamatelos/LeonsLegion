package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.CardGamePackage.BlackjackGame;
import com.leonslegion.casino.CardGamePackage.BlackjackHand;
import com.leonslegion.casino.CardGamePackage.BlackjackPlayer;
import com.leonslegion.casino.CardGamePackage.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cameronsima on 5/10/17.
 */
public class BlackjackGameTests {

    BlackjackPlayer sam;
    BlackjackPlayer bob;
    BlackjackGame newGame;

    @Before
    public void setUp() {
        Account acct = Account.AccountFactory.createAccountWithName("Cameron");
        BlackjackPlayer player = new BlackjackPlayer(acct);

        newGame = new BlackjackGame(player);

    }

    @Test
    public void testNewGame() {

        Assert.assertTrue(newGame.getHasDealer());

        System.out.println(newGame);
    }

    @Test
    public void testPromptString() {

        BlackjackHand hand = new BlackjackHand();
        BlackjackHand dealerHand = new BlackjackHand();
        Card card1 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);
        hand.addCard(card1);
        hand.addCard(card2);
        dealerHand.addCard(card1);
        dealerHand.addCard(card2);

        BlackjackGame game = new BlackjackGame();
        game.initialDeal();

        String actualOutput = game.buildUserPromptString(hand, dealerHand);

        String expectedOutput = "Your hand: TEN of CLUBS; EIGHT of DIAMONDS; \n" +
                "is worth 18 points. Hit or stay?";

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testInitialDeal() {


    }

    @Test
    public void testCheck21() {

    }
}
