package com.leonslegion.casino.RoulettePackage;

import java.util.*;

import com.leonslegion.casino.InputHandler;
import org.apache.commons.lang3.math.NumberUtils;

public class RouletteBetHandler {



    public static String handleAnyBet() {
        String bet = InputHandler.getStringInput("Pick a bet to make by typing 'inside' or 'outside'.");
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



    private static String handleInsideBet() {
        String bet = InputHandler.getStringInput("Enter which number you'd like to bet on. Only the integer portions of fractional inputs are taken.");
        if (NumberUtils.isParsable(bet)) {
            if (Double.parseDouble(bet) < 0 || Double.parseDouble(bet) > 36) {
                System.out.println("Bet not accepted.");
                System.out.println();
                return handleInsideBet();
            }
            else {
                System.out.println("Bet accepted.");
                System.out.println();
                int integerBet = (int) Double.parseDouble(bet);
                return Integer.toString(integerBet);
            }
        }
        else {
            System.out.println("Bet must be a number!");
            return handleInsideBet();}
    }




    private static String handleOutsideBet() {
        System.out.println("Which outside bet type would you like to make?");
        String bet = InputHandler.getStringInput("Select from 'Column', 'Dozen', 'Even Or Odd', 'Front or Back', or 'Color'.");
        if (bet.equalsIgnoreCase("Column")) {
            return handleColumnBet();
        }
        else if (bet.equalsIgnoreCase("Dozen")) {
            return handleDozenBet();
        }
        else if (bet.equalsIgnoreCase("Even or Odd")) {
            return handleEvenOrOddBet();
        }
        else if (bet.equalsIgnoreCase("Front or Back")) {
            return handleFrontOrBackBet();
        }
        else if (bet.equalsIgnoreCase("Color")) {
            return handleColorBet();
        }
        else {
            return handleOutsideBet();
        }
    }



    private static String handleColumnBet() {
        System.out.println("Which column bet type would you like to make?");
        String bet = InputHandler.getStringInput("Select from '1st C', '2nd C', or '3rd C'.");
        if (bet.equalsIgnoreCase("1st C") || bet.equalsIgnoreCase("2nd C") || bet.equalsIgnoreCase("3rd C")) {
            return bet;
        }
        else {return handleColumnBet();}
    }



    private static String handleDozenBet() {
        System.out.println("Which dozen bet type would you like to make?");
        String bet = InputHandler.getStringInput("Select from '1st D', '2nd D', or '3rd D'.");
        if (!bet.equalsIgnoreCase("1st D") && !bet.equalsIgnoreCase("2nd D") && !bet.equalsIgnoreCase("3rd D")) {
            return handleDozenBet();
        }
        else {return bet;}
    }



    private static String handleEvenOrOddBet() {
        System.out.println("Which even or odd bet type would you like to make?");
        String bet = InputHandler.getStringInput("Select from 'Even', or 'Odd'.");
        if (!bet.equalsIgnoreCase("Even") && !bet.equalsIgnoreCase("Odd")) {
            return handleEvenOrOddBet();
        }
        else {return bet.toLowerCase();}
    }



    private static String handleFrontOrBackBet() {
        System.out.println("Which front or back bet type would you like to make?");
        String bet = InputHandler.getStringInput("Select from 'Front', or 'Back'.");
        if (!bet.equalsIgnoreCase("Front") && !bet.equalsIgnoreCase("Back")) {
            return handleFrontOrBackBet();
        }
        else {return bet.toLowerCase();}
    }



    private static String handleColorBet() {
        System.out.println("Which color odd bet type would you like to make?");
        String bet = InputHandler.getStringInput("Select from 'Red', or 'Black'.");
        if (!bet.equalsIgnoreCase("Red") && !bet.equalsIgnoreCase("Black")) {
            return handleColorBet();
        }
        else {return bet.toLowerCase();}
    }



    public static double checkPlayerBetsForInsideBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (spinResult.equals(betList.get(count).getBetType())) {
                System.out.print("You won a 35:1 Payout! You won: $");
                System.out.printf("%,.2f", betList.get(count).getBetValue() * 34);
                System.out.println();
                return betList.get(count).getBetValue() * 35;
            }
        }
        return 0;
    }



    public static double checkPlayerBetsForOutsideDozenBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 13 && betList.get(count).getBetType().equalsIgnoreCase("1st D")) {
                System.out.print("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", betList.get(count).getBetValue()*2);
                System.out.println();
                return betList.get(count).getBetValue() * 3;
            }
            if (Integer.parseInt(spinResult) > 12 && Integer.parseInt(spinResult) < 25 && betList.get(count).getBetType().equalsIgnoreCase("2nd D")) {
                System.out.print("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", betList.get(count).getBetValue()*2);
                System.out.println();
                return betList.get(count).getBetValue() * 3;
            }
            if (Integer.parseInt(spinResult) > 24 && Integer.parseInt(spinResult) < 37 && betList.get(count).getBetType().equalsIgnoreCase("3rd D")) {
                System.out.print("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", betList.get(count).getBetValue()*2);
                System.out.println();
                return betList.get(count).getBetValue() * 3;
            }
        }
        return 0;
    }



    public static double checkPlayerBetsForOutsideColumnBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            for (int columnStart = 1; columnStart < 35; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && betList.get(count).getBetType().equalsIgnoreCase("1st C")) {
                    System.out.print("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", betList.get(count).getBetValue()*2);
                    System.out.println();
                    return betList.get(count).getBetValue() * 3;
                }
            }
            for (int columnStart = 2; columnStart < 36; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && betList.get(count).getBetType().equalsIgnoreCase("2nd C")) {
                    System.out.print("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", betList.get(count).getBetValue()*2);
                    System.out.println();
                    return betList.get(count).getBetValue() * 3;
                }
            }
            for (int columnStart = 3; columnStart < 37; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && betList.get(count).getBetType().equalsIgnoreCase("3rd C")) {
                    System.out.print("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", betList.get(count).getBetValue()*2);
                    System.out.println();
                    return betList.get(count).getBetValue() * 3;
                }
            }
        }
        return 0;
    }



    public static double checkPlayerBetsForEvenOrOddBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (Integer.parseInt(spinResult) % 2 == 0 && Integer.parseInt(spinResult) != 0 && betList.get(count).getBetType().equalsIgnoreCase("Even")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", betList.get(count).getBetValue());
                System.out.println();
                return betList.get(count).getBetValue() * 2;
            }
            if (Integer.parseInt(spinResult) % 2 == 1 && Integer.parseInt(spinResult) != 0 && betList.get(count).getBetType().equalsIgnoreCase("Odd")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", betList.get(count).getBetValue());
                System.out.println();
                return betList.get(count).getBetValue() * 2;
            }
        }
        return 0;
    }



    public static double checkPlayerBetsForFrontOrBackBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 19 && betList.get(count).getBetType().equalsIgnoreCase("Front")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", betList.get(count).getBetValue());
                System.out.println();
                return betList.get(count).getBetValue() * 2;
            }
            if (Integer.parseInt(spinResult) > 18 && Integer.parseInt(spinResult) < 37 && betList.get(count).getBetType().equalsIgnoreCase("Back")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", betList.get(count).getBetValue());
                System.out.println();
                return betList.get(count).getBetValue() * 2;
            }
        }
        return 0;
    }

    public static double checkPlayerBetsForColorBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        int[] redList = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        int[] blackList = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        for (int count = 0; count < betList.size(); count++) {
            if (betList.get(count).getBetType().equalsIgnoreCase("Red")) {
                for (int i = 0; i < redList.length; i++) {
                    if (Integer.parseInt(spinResult) == redList[i]) {
                        System.out.print("You won a 1:1 Payout! You won: ");
                        System.out.printf("%,.2f", betList.get(count).getBetValue());
                        System.out.println();
                        return betList.get(count).getBetValue() * 2;
                    }
                }
            }
            if (betList.get(count).getBetType().equalsIgnoreCase("Black")) {
                for (int i = 0; i < blackList.length; i++) {
                    if (Integer.parseInt(spinResult) == blackList[i]) {
                        System.out.print("You won a 1:1 Payout! You won: ");
                        System.out.printf("%,.2f", betList.get(count).getBetValue());
                        System.out.println();
                        return betList.get(count).getBetValue() * 2;
                    }
                }
            }
        }
        return 0;
    }




}
