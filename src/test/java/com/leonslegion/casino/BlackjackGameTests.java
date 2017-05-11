package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by cameronsima on 5/10/17.
 */
public class BlackjackGameTests {

    BlackjackPlayer sam;
    BlackjackPlayer bob;
    BlackjackGame newGame;

    @Before
    public void setUp() {
        sam = new BlackjackPlayer(100, 1);
        bob = new BlackjackPlayer(100, 2);

        newGame = new BlackjackGame();
        newGame.setPlayer(bob);
        newGame.setPlayer(sam);
    }

    @Test
    public void testNewGame() {

        Assert.assertTrue(newGame instanceof BlackjackGame);
    }

    @Test
    public void testPromptString() {

        BlackjackHand hand = new BlackjackHand();
        Card card1 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS);
        hand.addCard(card1);
        hand.addCard(card2);

        BlackjackGame game = new BlackjackGame();

        String actualOutput = game.buildPromptString(hand);

        String expectedOutput = "Your hand: TEN of CLUBS; EIGHT of DIAMONDS; \n" +
                "is worth 18 points. Hit or stay?";

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testInitialDeal() {

        newGame.initialDeal();
        ArrayList<BlackjackPlayer> players = newGame.getPlayers();

        for (Player player : players) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            System.out.println(p.getHand());
        }
        System.out.println();


    }
}
