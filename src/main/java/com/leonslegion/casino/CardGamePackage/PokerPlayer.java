package com.leonslegion.casino.CardGamePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

public class PokerPlayer extends CardPlayer {

    PokerPlayer(Account account) {
        super(account);
        hand = new PokerHand();
    }

    @Override
    public PokerHand getHand() {
        return (PokerHand) super.getHand();
    }

    boolean leaveGame() {
        Console.printDashes();
        String string = Console.getStringInput("You gonna stick around, " + getAccount().getAccountHolderName() +  "?\n" +
                "Your current balance is " + Console.moneyToString(getBalance()) + ".\n" +
                "(Y/N)?\n");
        if(string.equalsIgnoreCase("y") || string.equalsIgnoreCase("yes")) {
            Console.println("OK, " + getAccount().getAccountHolderName() + ". You can stay, but you should probably slow down on the jack and cokes.\n");
            return false;
        } else if(string.equalsIgnoreCase("n") || string.equalsIgnoreCase("no")){
            Console.println("Everyone else at the table is sad to see you go.\n");
            return true;
        } else {
            Console.println("I asked for a yes or no. Lets flip a coin, and if it's heads you stay.\n");
            if(Math.random() < 0.5) {
                Console.println("Well, " + getAccount().getAccountHolderName() + ", I guess you're destined to stick around and lose more money.");
                return false;
            } else {
                Console.println("Make like MJ and beat it.\n");
                return true;
            }
        }
    }

}
