package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

import java.util.concurrent.TimeUnit;

/**
 * Created by cameronsima on 5/13/17.
 */
public class BlackjackDealer extends BlackjackPlayer {

    public BlackjackDealer(Account account) {
        super(account);
    }


    @Override
    public void showHand() {
        Console.println("Dealer hand: \n");
        Console.println(getHand().toString());
        Console.println("\n" + getHand().getPoints() + " points.");
    }

    public void cardsShowing() {
        int len = getHand().getCards().size();
        Console.println("Dealer is showing: \n");

        for (int i=1; i<len; i++) {
            Console.print(getHand().getCards().get(i).toString());
        }
        Console.print("\n");
        for (int i=1; i<len; i++) {
            Console.print(getHand().getCards().get(i).toStringReverse());
        }
    }

    public void takeTurn(Deck deck) {
        wait(1);
        while (getHand().getPoints() < 17) {
            Console.println(" \n Dealer hits...\n");
            hit(deck);
        }
        wait(1);
        Console.println("Dealer stays.");
    }

    private void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Console.println(e.toString());
        }
    }
}

