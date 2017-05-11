package com.leonslegion.casino;

public class PokerPlayer extends CardPlayer {

    PokerPlayer(Account account) {
        super(account.getAccountBalance(), account.getId());
        hand = new PokerHand();
    }

    @Override
    public PokerHand getHand() {
        return (PokerHand) super.getHand();
    }

}
