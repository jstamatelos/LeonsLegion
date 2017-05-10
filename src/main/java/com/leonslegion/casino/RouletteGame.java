package com.leonslegion.casino;

import java.util.*;

import org.apache.commons.lang3.math.NumberUtils;

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
        System.out.println();
    }


    public static void printOutsideBets() {
        System.out.println("Possible Outside Bets:");
        System.out.println("Bet on 1st, 2nd or 3rd Dozen: 2:1 payout");
        System.out.println("Bet on Left, Middle or Right Column: 2:1 payout");
        System.out.println("Bet on Even or Odd: 1:1 payout");
        System.out.println("Bet on Front 18 (1-18) or Back 18 (19-36): 1:1 payout");
        System.out.println("Bet on Red or Black: 1:1 payout");
        System.out.println();
    }




    public static String handleAnyBet() {
        InputHandler input = new InputHandler();
        String bet = input.getStringInput("Pick a bet to make by typing 'inside' or 'outside'");
        if (bet.equalsIgnoreCase("inside")) {
            return handleInsideBet();
        }
        else if (bet.equalsIgnoreCase("outside")) {
            return handleOutsideBet();
        }
        else {
            return handleAnyBet();
        }
    }



    public static String handleInsideBet() {
        InputHandler input = new InputHandler();
        String bet = input.getStringInput("Enter which number you'd like to bet on");
        if (NumberUtils.isParsable(bet)) {
            if (Integer.parseInt(bet) < 0 || Integer.parseInt(bet) > 36) {
                return bet;
            } else {
                return handleInsideBet();
            }
        }
        else {return handleInsideBet();}
    }



    public String spin() {
        ArrayList<String> rouletteWheel = RouletteGame.createRouletteWheel();
        int randomNumber = (int) Math.floor(Math.random()*37);
        return rouletteWheel.get(randomNumber);
    }



    public static String handleOutsideBet() {
        InputHandler input = new InputHandler();
        System.out.println("Which outside bet type would you like to make?");
        String bet = input.getStringInput("Select from 'Column', 'Dozen', 'Even Or Odd', 'Front or Back', or 'Color'.");
        switch (bet) {
            case "Column":
                return handleColumnBet();
            case "Dozen":
                return handleDozenBet();
            case "Even or Odd":
                return handleEvenOrOddBet();
            case "Front or Back":
                return handleFrontOrBackBet();
            case "Color":
                return handleColorBet();
            default:
                return handleOutsideBet();
        }
    }



    public static String handleColumnBet() {
        InputHandler input = new InputHandler();
        System.out.println("Which column bet type would you like to make?");
        String bet = input.getStringInput("Select from '1st C', '2nd C', or '3rd C'.");
        if (!bet.equals("1st C") && !bet.equals("2nd C") && !bet.equals("3rd C")) {
            return handleColumnBet();
        }
        else {return bet;}
    }



    public static String handleDozenBet() {
        InputHandler input = new InputHandler();
        System.out.println("Which dozen bet type would you like to make?");
        String bet = input.getStringInput("Select from '1st R', '2nd R', or '3rd R'.");
        if (!bet.equals("1st R") && !bet.equals("2nd R") && !bet.equals("3rd R")) {
            return handleDozenBet();
        }
        else {return bet;}
    }



    public static String handleEvenOrOddBet() {
        InputHandler input = new InputHandler();
        System.out.println("Which even or odd bet type would you like to make?");
        String bet = input.getStringInput("Select from 'Even', or 'Odd'.");
        if (!bet.equalsIgnoreCase("Even") && !bet.equalsIgnoreCase("Odd")) {
            return handleEvenOrOddBet();
        }
        else {return bet.toLowerCase();}
    }



    public static String handleFrontOrBackBet() {
        InputHandler input = new InputHandler();
        System.out.println("Which front or back bet type would you like to make?");
        String bet = input.getStringInput("Select from 'Front', or 'Back'.");
        if (!bet.equalsIgnoreCase("Front") && !bet.equalsIgnoreCase("Back")) {
            return handleFrontOrBackBet();
        }
        else {return bet.toLowerCase();}
    }



    public static String handleColorBet() {
        InputHandler input = new InputHandler();
        System.out.println("Which color odd bet type would you like to make?");
        String bet = input.getStringInput("Select from 'Red', or 'Black'.");
        if (!bet.equalsIgnoreCase("Red") && !bet.equalsIgnoreCase("Black")) {
            return handleColorBet();
        }
        else {return bet.toLowerCase();}
    }



    public static void initializeGame() {
        printRouletteTable();
        printInsideBets();
        printOutsideBets();
    }






}
