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

    BlackjackPlayer player;
    BlackjackGame blackjackGame;

    @Before
    public void setUp() {
        Account acct = Account.AccountFactory.createAccountWithName("Cameron");
        player = new BlackjackPlayer(acct);
        blackjackGame = new BlackjackGame(player);
    }
/*
    @Test
    public void testNewGame() {

        BlackjackGame g = new BlackjackGame();

        Assert.assertTrue(blackjackGame.getHasDealer());

        g.startBlackJack();


    }
*/
}
