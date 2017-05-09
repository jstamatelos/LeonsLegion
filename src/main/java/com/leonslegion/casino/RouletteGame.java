package com.leonslegion.casino;

import java.util.*;

public class RouletteGame implements Spin {


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
        System.out.println("          0 (uncolored)           00 (uncolored)        ");
        System.out.println("    1 (red)             2 (black)              3 (red)");
        System.out.println("    4 (black)           5 (red)                6 (black)");
        System.out.println("    7 (red)             8 (black)              9 (red)");
        System.out.println("   10 (black)          11 (black)              12 (red)");
        System.out.println("   13 (red)            14 (red)                15 (black)");
        System.out.println("   16 (red)            17 (black)              18 (red)");
        System.out.println("   19 (red)            20 (black)              21 (red)");
        System.out.println("   22 (black)          23 (red)                24 (black)");
        System.out.println("   25 (red)            26 (black)              27 (red)");
        System.out.println("   28 (black)          29 (black)              30 (red)");
        System.out.println("   31 (black)          32 (red)                33 (black)");
        System.out.println("   34 (red)            35 (black)              36 (red)");
        System.out.println();
    }



    public static void printInsideBets() {
        System.out.println("Possible Inside Bets:");
        System.out.println("Bet on a single number: 35:1 payout");
        System.out.println("Bet on a combination of two numbers: 17:1 payout");
        System.out.println("Bet on a combination of three numbers: 11:1 payout");
        System.out.println("Bet on a combination of four numbers: 8:1 payout");
        System.out.println();



    }
    public static void printSideBets() {
        System.out.println("Possible Outside Bets:");
        System.out.println("Bet on 1st, 2nd or 3rd Dozen: 2:1 payout");
        System.out.println("Bet on Left, Middle or Right Column: 2:1 payout");
        System.out.println("Bet on Even or Odd: 1:1 payout");
        System.out.println("Bet on Front 18 (1-18) or Back 18 (19-36): 1:1 payout");
        System.out.println("Bet on Red or Black: 1:1 payout");
        System.out.println();
    }



    public String spin() {
        ArrayList<String> rouletteWheel = RouletteGame.createRouletteWheel();
        int randomNumber = (int) Math.floor(Math.random()*37);
        return rouletteWheel.get(randomNumber);
    }
}
