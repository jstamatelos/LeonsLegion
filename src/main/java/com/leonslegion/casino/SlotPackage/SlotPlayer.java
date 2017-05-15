package com.leonslegion.casino.SlotPackage;

import com.leonslegion.casino.Abstracts.Player;
import com.leonslegion.casino.Console;
import com.leonslegion.casino.AccountPackage.Account;

/**
 * Created by sarahweisser on 5/11/17.
 */
public class SlotPlayer extends Player {

    private long bet = 500;

    public SlotPlayer (Account account) {
        super(account);
    }

    public long getBet() {
        return bet;
    }

    public boolean placeBet() {
        if (super.getBalance() < bet) {
            Console.println("It is $5.00 to play this machine.");
            Console.println("You do not have enough in your account to play.");
            return false;
        }
        else {
            this.getAccount().setAccountBalance(getBalance() - 500);
            return true;
        }
    }

    public long placeBet(long bet) {return 0;}


}
