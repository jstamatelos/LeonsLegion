package com.leonslegion.casino;

public class PokerPlayer extends CardPlayer {

    PokerPlayer(Account account) {
        super(account.getAccountBalance(), account.getId());
    }

    @Override
    public PokerHand getHand() {
        return (PokerHand) super.getHand();
    }


    void check() {
        //like stay in BlackJackPlayer, doesn't imply action and may be unnecessary.
    }

    void raise(double base, double raise) throws Exception {
        placeBet(base+raise);
    }

}
