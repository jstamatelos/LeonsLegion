package com.leonslegion.casino;

import java.util.ArrayList;

/**
 * Created by sarahweisser on 5/11/17.
 */
public class SlotPlayer extends Player {

    private double bet = 5;

    public SlotPlayer (Account account) {
        super(account.getAccountBalance(),account.getId());
    }

    public double getBet() {
        return bet;
    }

    public boolean placeBet() {
        if (super.getBalance() < 5) {
            System.out.println("It is $5.00 to play this machine.");
            System.out.println("You do not have enough in your account to play.");
            return false;
        }
        else {
            this.setBalance(this.getBalance() - 5);
            return true;
        }
    }

    public double placeBet(double bet) {return 0;}


}
