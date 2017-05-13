package com.leonslegion.casino.CardGamePackage;

/**
 * Created by danzygmund-felt on 5/11/17.
 */
public class PokerPlayerBettingRound {
    PokerPlayer player;
    long amountIn;
    boolean folded;

    PokerPlayerBettingRound(PokerPlayer player) {
        this.player = player;
        amountIn = 0;
        folded = false;
    }

    String showHand() {
        return player.getHand().toString();
    }

    void folds() {
        folded = true;
    }
}
