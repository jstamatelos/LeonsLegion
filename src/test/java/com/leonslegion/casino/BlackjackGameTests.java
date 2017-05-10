package com.leonslegion.casino;

import org.junit.Test;

/**
 * Created by cameronsima on 5/10/17.
 */
public class BlackjackGameTests {

    @Test
    public void testNewGame() {

        BlackjackPlayer sam = new BlackjackPlayer(100, 1);
        BlackjackPlayer bob = new BlackjackPlayer(100, 2);

        BlackjackGame newGame = new BlackjackGame();
        newGame.setPlayer(bob);
        newGame.setPlayer(sam);


    }
}
