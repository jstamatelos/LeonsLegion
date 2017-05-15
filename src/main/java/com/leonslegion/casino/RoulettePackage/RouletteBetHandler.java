package com.leonslegion.casino.RoulettePackage;

import java.util.*;

import com.leonslegion.casino.Console;
import com.leonslegion.casino.InputHandler;
import org.apache.commons.lang3.math.NumberUtils;

//Virtually Fully Tested
public class RouletteBetHandler {


    public static String handleAnyBet(InputAsker asker) {
        String bet = asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.");
        while (NumberUtils.isParsable(bet)) {
            if ((Integer.parseInt(bet) > 0 && Integer.parseInt(bet) < 37)) {
                return bet;
            }
            else if (bet.equalsIgnoreCase("0") || bet.equalsIgnoreCase("00")) {
                return bet;
            }
            else {
                bet = asker.askForInput("You must bet 0, 00, or a number between 1 and 36.");
            }
        }
        while (!NumberUtils.isParsable(bet)) {
            if (bet.equalsIgnoreCase("1st C")) {
                return bet;
            } else if (bet.equalsIgnoreCase("2nd C")) {
                return bet;
            } else if (bet.equalsIgnoreCase("3rd C")) {
                return bet;
            } else if (bet.equalsIgnoreCase("1st D")) {
                return bet;
            } else if (bet.equalsIgnoreCase("2nd D")) {
                return bet;
            } else if (bet.equalsIgnoreCase("3rd D")) {
                return bet;
            } else if (bet.equalsIgnoreCase("Front")) {
                return bet;
            } else if (bet.equalsIgnoreCase("Back")) {
                return bet;
            } else if (bet.equalsIgnoreCase("Odd")) {
                return bet;
            } else if (bet.equalsIgnoreCase("Even")) {
                return bet;
            } else if (bet.equalsIgnoreCase("Red")) {
                return bet;
            } else if (bet.equalsIgnoreCase("Black")) {
                return bet;
            } else {
                bet = asker.askForInput("You must bet from one of the options above.");
            }
        }
        return bet;
    }

    public static long checkPlayerBetsForInsideBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (spinResult.equals(betList.get(count).getBetType())) {
                Console.print("You won a 35:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue() * 34);
                Console.println("");
                return betList.get(count).getBetValue() * 35;
            }
        }
        return 0;
    }



    public static long checkPlayerBetsForOutsideDozenBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 13 && betList.get(count).getBetType().equalsIgnoreCase("1st D")) {
                Console.print("You won a 2:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue()*2);
                Console.println("");
                return betList.get(count).getBetValue() * 3;
            }
            if (Integer.parseInt(spinResult) > 12 && Integer.parseInt(spinResult) < 25 && betList.get(count).getBetType().equalsIgnoreCase("2nd D")) {
                Console.print("You won a 2:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue()*2);
                Console.println("");
                return betList.get(count).getBetValue() * 3;
            }
            if (Integer.parseInt(spinResult) > 24 && Integer.parseInt(spinResult) < 37 && betList.get(count).getBetType().equalsIgnoreCase("3rd D")) {
                Console.print("You won a 2:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue()*2);
                Console.println("");
                return betList.get(count).getBetValue() * 3;
            }
        }
        return 0;
    }



    public static long checkPlayerBetsForOutsideColumnBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            for (int columnStart = 1; columnStart < 35; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && betList.get(count).getBetType().equalsIgnoreCase("1st C")) {
                    Console.print("You won a 2:1 Payout! You won: ");
                    RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue()*2);
                    Console.println("");
                    return betList.get(count).getBetValue() * 3;
                }
            }
            for (int columnStart = 2; columnStart < 36; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && betList.get(count).getBetType().equalsIgnoreCase("2nd C")) {
                    Console.print("You won a 2:1 Payout! You won: ");
                    RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue()*2);
                    Console.println("");
                    return betList.get(count).getBetValue() * 3;
                }
            }
            for (int columnStart = 3; columnStart < 37; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && betList.get(count).getBetType().equalsIgnoreCase("3rd C")) {
                    Console.print("You won a 2:1 Payout! You won: ");
                    RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue()*2);
                    Console.println("");
                    return betList.get(count).getBetValue() * 3;
                }
            }
        }
        return 0;
    }



    public static long checkPlayerBetsForEvenOrOddBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (Integer.parseInt(spinResult) % 2 == 0 && Integer.parseInt(spinResult) != 0 && betList.get(count).getBetType().equalsIgnoreCase("Even")) {
                Console.print("You won a 1:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue());
                Console.println("");
                return betList.get(count).getBetValue() * 2;
            }
            if (Integer.parseInt(spinResult) % 2 == 1 && Integer.parseInt(spinResult) != 0 && betList.get(count).getBetType().equalsIgnoreCase("Odd")) {
                Console.print("You won a 1:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue());
                Console.println("");
                return betList.get(count).getBetValue() * 2;
            }
        }
        return 0;
    }



    public static long checkPlayerBetsForFrontOrBackBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        for (int count = 0; count < betList.size(); count++) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 19 && betList.get(count).getBetType().equalsIgnoreCase("Front")) {
                Console.print("You won a 1:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue());
                Console.println("");
                return betList.get(count).getBetValue() * 2;
            }
            if (Integer.parseInt(spinResult) > 18 && Integer.parseInt(spinResult) < 37 && betList.get(count).getBetType().equalsIgnoreCase("Back")) {
                Console.print("You won a 1:1 Payout! You won: ");
                RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue());
                Console.println("");
                return betList.get(count).getBetValue() * 2;
            }
        }
        return 0;
    }

    public static long checkPlayerBetsForColorBetWins(ArrayList<RouletteBet> betList, String spinResult) {
        int[] redList = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        int[] blackList = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        for (int count = 0; count < betList.size(); count++) {
            if (betList.get(count).getBetType().equalsIgnoreCase("Red")) {
                for (int i = 0; i < redList.length; i++) {
                    if (Integer.parseInt(spinResult) == redList[i]) {
                        Console.print("You won a 1:1 Payout! You won: ");
                        RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue());
                        Console.println("");
                        return betList.get(count).getBetValue() * 2;
                    }
                }
            }
            if (betList.get(count).getBetType().equalsIgnoreCase("Black")) {
                for (int i = 0; i < blackList.length; i++) {
                    if (Integer.parseInt(spinResult) == blackList[i]) {
                        Console.print("You won a 1:1 Payout! You won: ");
                        RoulettePrint.moneyFormatterForPrinting(betList.get(count).getBetValue());
                        Console.println("");
                        return betList.get(count).getBetValue() * 2;
                    }
                }
            }
        }
        return 0;
    }




}
