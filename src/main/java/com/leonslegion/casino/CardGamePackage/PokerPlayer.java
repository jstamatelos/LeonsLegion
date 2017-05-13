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
        String string = Console.getStringInput("You gonna stick around? (Y/N)\n");
        if(string.equalsIgnoreCase("y")) {
            Console.println("OK, " + getAccount().getAccountHolderName() + ".\nYou can stay, but you should probably slow down on the jack and cokes.");
            return false;
        } else if(string.equalsIgnoreCase("n")){
            Console.println("Good choice.\n");
            return true;
        } else {
            Console.println("I asked for a yes or no. Lets flip a coin, and if it's heads you stay.\n");
            if(Math.random() < 0.5) {
                Console.println("I guess you're destined to lose more money.\n");
                return false;
            } else {
                Console.println("Bye, Felicia.\n");
                return true;
            }
        }
    }

}
