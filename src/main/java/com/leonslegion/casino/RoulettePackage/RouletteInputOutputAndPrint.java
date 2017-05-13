package com.leonslegion.casino.RoulettePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

/**
 * Created by markbrown on 5/13/17.
 */
public class RouletteInputOutputAndPrint {


    public static void printWelcomeMessage() {
        Console.println("");
        Console.printDashes();
        Console.println("Welcome to Roulette!");
        Console.printDashes();
    }


    public static long getNumberOfPlayers(long numberOfAttempts) {
        Console.println("Type '0' to return to lobby.");
        do {
            return Console.getLongInput("How many players would like to play?", numberOfAttempts);
        } while (numberOfAttempts > 0);
    }


    public static long getPlayerID(long numberOfAttempts) {
        do {
            Console.println("");
            return Console.getLongInput("Please enter your ID.", numberOfAttempts);
        } while (numberOfAttempts > 0);
    }


}