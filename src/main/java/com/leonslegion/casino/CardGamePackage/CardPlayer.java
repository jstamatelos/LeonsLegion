package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.Abstracts.Player;
import com.leonslegion.casino.AccountPackage.Account;

public abstract class CardPlayer extends Player {

    protected Hand hand;

    CardPlayer(Account account) {
        super(account);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public long placeBet(long bet) throws Exception {
        if(getBalance() - bet < 0) {
            throw new Exception("Bet is too large.");
        }
        getAccount().setAccountBalance(-bet);
        return bet;
    }

}