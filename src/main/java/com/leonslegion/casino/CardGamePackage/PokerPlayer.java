package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;

public class PokerPlayer extends CardPlayer {

    PokerPlayer(Account account) {
        super(account);
        hand = new PokerHand();
    }

    @Override
    public PokerHand getHand() {
        return (PokerHand) super.getHand();
    }

}
