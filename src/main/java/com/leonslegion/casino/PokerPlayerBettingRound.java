package com.leonslegion.casino;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
public class PokerPlayerBettingRound {
    PokerPlayer player;
    double amountIn;

    PokerPlayerBettingRound(PokerPlayer player) {
        this.player = player;
        amountIn = 0;
    }

    String showHand() {
        return player.getHand().toString();
    }
}
