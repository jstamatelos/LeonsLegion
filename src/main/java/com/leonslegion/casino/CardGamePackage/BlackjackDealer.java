package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

/**
 * Created by cameronsima on 5/13/17.
 */
public class BlackjackDealer extends BlackjackPlayer {

    public BlackjackDealer(Account account) {
        super(account);
    }

    @Override
    public double placeBet(double d) {
        // do nothing
        return -1;
    }

    @Override
    public void showHand() {
        Console.println("Dealer has: ");
        Console.println(getHand().toString());
    }

    public void cardsShowing() {
        int len = getHand().getCards().size();
        System.out.println(getHand().getCards().size());
        Console.println("Dealer is showing: ");


        for (int i=1; i<len; i++) {
            Console.print(getHand().getCards().get(i).toString());
        }
        Console.print("\n");
        for (int i=1; i<len; i++) {
            Console.print(getHand().getCards().get(i).toStringReverse());
        }
        Console.println("\n");
    }
}

