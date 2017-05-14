package com.leonslegion.casino.RoulettePackage;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

/**
 * Created by markbrown on 5/14/17.
 */
public class RoulettePrint {

    public static void printWelcomeMessage() {
        Console.println("");
        Console.printDashes();
        Console.println("Welcome to Roulette!");
        Console.printDashes();
    }

    public static void printNumberOfAttemptsRemaining(long numberOfAttempts) {
        Console.println("Number of Input Attempts Remaining: " + numberOfAttempts);
    }

    public static void printAccountNotFoundMessage() {
        Console.println("");
        Console.println("Account Not Found!");
    }

    public static void printAccountAlreadyLoaded() {
        Console.println("");
        Console.println("Account Already Loaded!");
    }

    public static void printAttemptsExceeded() {
        Console.println("");
        Console.println("You've surpassed the two attempts limit.");
    }

    public static void printAccountAccepted() {
        Console.println("");
        Console.println("ID Accepted! Loading Account Info...");
    }

    public void printAccountInformation(Account account) {
        Console.println("");
    }

    public static void printTableInformation() {
        RouletteTable.printRouletteTable();
        RouletteTable.printInsideBets();
        RouletteTable.printOutsideBets();
    }

}
