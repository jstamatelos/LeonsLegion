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


    public static long getNumberOfPlayers() {
        Console.println("Type '0' to return to lobby.");
        long numberOfPlayers = -1;
        do {
            numberOfPlayers = Console.getLongInput("How many players would like to play?");
        } while (numberOfPlayers == -1);
        return numberOfPlayers;
    }


    public static long getPlayerID() {
        Console.println("");
        return Console.getLongInput("Please enter your ID.");
    }


}