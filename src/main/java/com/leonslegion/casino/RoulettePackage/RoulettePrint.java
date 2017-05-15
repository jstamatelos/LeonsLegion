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

    public static void printAccountInformation(Account account) {
        Console.printDashes();
        Console.println("");
        Console.print("Account Name: ");
        Console.println(Account.AccountManager.getAccountHolderName(account));
        Console.print("Account ID: ");
        Console.println(Long.toString(account.getId()));
        Console.print("Current Balance: ");
        moneyFormatterForPrinting(Account.AccountManager.getBalance(account));
        Console.println("");
        Console.println("");
        Console.printDashes();
    }

    public static void moneyFormatterForPrinting (long money) {
        double dollarsAndCents = money / 100;
        System.out.print("$");
        System.out.printf("%,.2f", dollarsAndCents);
    }


    public static void printTableInformation() {
        printRouletteTable();
        printInsideBets();
        printOutsideBets();
    }

    public static void printRouletteTable() {
        Console.printDashes();
        Console.println("");
        Console.println("          0 (uncolored)           00 (uncolored)        ");
        Console.println("    1 (red)             2 (black)              3 (red)");
        Console.println("    4 (black)           5 (red)                6 (black)");
        Console.println("    7 (red)             8 (black)              9 (red)");
        Console.println("   10 (black)          11 (black)              12 (red)");
        Console.println("   13 (red)            14 (red)                15 (black)");
        Console.println("   16 (red)            17 (black)              18 (red)");
        Console.println("   19 (red)            20 (black)              21 (red)");
        Console.println("   22 (black)          23 (red)                24 (black)");
        Console.println("   25 (red)            26 (black)              27 (red)");
        Console.println("   28 (black)          29 (black)              30 (red)");
        Console.println("   31 (black)          32 (red)                33 (black)");
        Console.println("   34 (red)            35 (black)              36 (red)");
        Console.println("");
    }



    public static void printInsideBets() {
        Console.println("Type the number to make an inside bet. Fractional components are ignored.");
        Console.println("Payout is 35:1");
        Console.println("");
    }



    public static void printOutsideBets() {
        Console.println("Type '1st C', '2nd C', or '3rd C' for Column Bets (2:1 Payout)");
        Console.println("Type '1st D', '2nd D', or '3rd D' for Dozen Bets (2:1 Payout)");
        Console.println("Type 'Even' or 'Odd' for Even/Odd Outside Bet (1:1 Payout)");
        Console.println("Type 'Front' or 'Back' for Front/Back Outside Bet (1:1 payout)");
        Console.println("Type 'Red' or 'Black' for Color Bet (1:1 Payout)");
        Console.println("");
        Console.printDashes();
    }

}
