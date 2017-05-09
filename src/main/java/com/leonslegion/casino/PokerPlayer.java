package com.leonslegion.casino;

public class PokerPlayer extends CardPlayer {

    PokerPlayer(double balance, long accountId) {
        super(balance, accountId);
    }

    void check() {
        //like stay in BlackJackPlayer, doesn't imply action and may be unnecessary.
    }

    void raise(double base, double raise) throws Exception {
        placeBet(base+raise);
    }

}
