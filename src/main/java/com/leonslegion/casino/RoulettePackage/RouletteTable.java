package com.leonslegion.casino.RoulettePackage;

import com.leonslegion.casino.Console;

import java.util.ArrayList;

public class RouletteTable {



    public static ArrayList<String> createRouletteWheel() {
        ArrayList<String> possibleRouletteOutcomes = new ArrayList<String>();
        possibleRouletteOutcomes.add(0, "0");
        possibleRouletteOutcomes.add(1, "00");
        for (int i = 2; i < 38; i++) {
            possibleRouletteOutcomes.add(i, Integer.toString(i - 1));
        }
        return possibleRouletteOutcomes;
    }



    public static void printRouletteTable() {
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
        Console.printDashes();
    }



    public static void printInsideBets() {
        Console.println("Possible Inside Bets:");
        Console.println("Bet on a single number: 35:1 payout");
        Console.printDashes();
    }



    public static void printOutsideBets() {
        Console.println("Possible Outside Bets:");
        Console.println("Bet on 1st, 2nd or 3rd Dozen: 2:1 payout");
        Console.println("Bet on Left, Middle or Right Column: 2:1 payout");
        Console.println("Bet on Even or Odd: 1:1 payout");
        Console.println("Bet on Front 18 (1-18) or Back 18 (19-36): 1:1 payout");
        Console.println("Bet on Red or Black: 1:1 payout");
        Console.printDashes();
    }



}
