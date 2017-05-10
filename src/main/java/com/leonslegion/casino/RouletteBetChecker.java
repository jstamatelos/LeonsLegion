package com.leonslegion.casino;

/**
 * Created by markbrown on 5/10/17.
 */
public class RouletteBetChecker {

    public static void checkPlayerBetsForInsideBetWins(RoulettePlayer playerOne, String spinResult) {
        boolean didPlayerWin = false;
        for (RouletteBet bet : playerOne.getBetList()) {
            if (bet.getBetType().equals(spinResult)) {
                System.out.print("You won a 35:1 Payout! You won: $");
                System.out.printf("%,.2f", bet.getBetValue() * 35);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue() + (bet.getBetValue() * 35));
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
                didPlayerWin = true;
                break;
            }
        }
        if (!didPlayerWin) {
            playerOne.setBalance(playerOne.getBalance() - playerOne.get);
            System.out.print("Your new balance: $");
            System.out.printf("%,.2f", playerOne.getBalance());
            System.out.println();
        }
    }



    public static void checkPlayerBetsForOutsideDozenBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 13 && bet.getBetType().equals("1st D")) {
                System.out.print("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue()*3));
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) > 12 && Integer.parseInt(spinResult) < 25 && bet.getBetType().equals("2nd D")) {
                System.out.print("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue()*3));
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) > 24 && Integer.parseInt(spinResult) < 37 && bet.getBetType().equals("3rd D")) {
                System.out.print("You won a 2:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue()*2);
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue()*3));
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
        }
    }



    public static void checkPlayerBetsForOutsideColumnBetWins(RoulettePlayer playerOne, String spinResult) {
        double tempBalance = playerOne.getBalance();
        for (RouletteBet bet : playerOne.getBetList()) {
            for (int columnStart = 1; columnStart < 35; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && bet.getBetType().equals("1st C")) {
                    System.out.print("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", bet.getBetValue() * 2);
                    System.out.println();
                    playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue() * 3));
                    System.out.print("Your new balance: $");
                    System.out.printf("%,.2f", playerOne.getBalance());
                    System.out.println();
                }
            }
            for (int columnStart = 2; columnStart < 36; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && bet.getBetType().equals("2nd C")) {
                    System.out.print("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", bet.getBetValue() * 2);
                    System.out.println();
                    playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue() * 3));
                    System.out.print("Your new balance: ");
                    System.out.printf("%,.2f", playerOne.getBalance());
                    System.out.println();
                }
            }
            for (int columnStart = 3; columnStart < 37; columnStart += 3) {
                if (Integer.parseInt(spinResult) == columnStart && bet.getBetType().equals("3rd C")) {
                    System.out.print("You won a 2:1 Payout! You won: ");
                    System.out.printf("%,.2f", bet.getBetValue() * 2);
                    System.out.println();
                    playerOne.setBalance(playerOne.getBalance() - (bet.getBetValue()) + (bet.getBetValue() * 3));
                    System.out.print("Your new balance: ");
                    System.out.printf("%,.2f", playerOne.getBalance());
                    System.out.println();
                }
            }
            if (tempBalance == playerOne.getBalance()) {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }

        }
    }



    public static void checkPlayerBetsForEvenOrOddBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (Integer.parseInt(spinResult) % 2 == 0 && Integer.parseInt(spinResult) != 0 && bet.getBetType().equals("Even")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.print("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) % 2 == 1 && Integer.parseInt(spinResult) != 0 && bet.getBetType().equals("Odd")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.print("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
        }
    }



    public static void checkPlayerBetsForFrontOrBackBetWins(RoulettePlayer playerOne, String spinResult) {
        for (RouletteBet bet : playerOne.getBetList()) {
            if (Integer.parseInt(spinResult) > 0 && Integer.parseInt(spinResult) < 19 && bet.getBetType().equals("Front")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.print("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else if (Integer.parseInt(spinResult) > 18 && Integer.parseInt(spinResult) < 37 && bet.getBetType().equals("Back")) {
                System.out.print("You won a 1:1 Payout! You won: ");
                System.out.printf("%,.2f", bet.getBetValue());
                System.out.println();
                playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                System.out.print("Your new balance: ");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
            else {
                playerOne.setBalance(playerOne.getBalance() - bet.getBetValue());
                System.out.print("Your new balance: $");
                System.out.printf("%,.2f", playerOne.getBalance());
                System.out.println();
            }
        }
    }

    public static void checkPlayerBetsForColorBetWins(RoulettePlayer playerOne, String spinResult) {
        double tempBalance = playerOne.getBalance();
        int[] redList = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        int[] blackList = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        for (RouletteBet bet : playerOne.getBetList()) {
            if (bet.getBetType().equalsIgnoreCase("Red")) {
                for (int i = 0; i < redList.length; i++) {
                    if (Integer.parseInt(spinResult) == redList[i]) {
                        System.out.print("You won a 1:1 Payout! You won: ");
                        System.out.printf("%,.2f", bet.getBetValue());
                        System.out.println();
                        playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                        System.out.print("Your new balance: ");
                        System.out.printf("%,.2f", playerOne.getBalance());
                        System.out.println();
                        break;
                    }
                }
            }
            if (bet.getBetType().equalsIgnoreCase("Black")) {
                for (int i = 0; i < blackList.length; i++) {
                    if (Integer.parseInt(spinResult) == blackList[i]) {
                        System.out.print("You won a 1:1 Payout! You won: ");
                        System.out.printf("%,.2f", bet.getBetValue());
                        System.out.println();
                        playerOne.setBalance(playerOne.getBalance() + (bet.getBetValue()));
                        System.out.print("Your new balance: ");
                        System.out.printf("%,.2f", playerOne.getBalance());
                        System.out.println();
                        break;
                    }
                }
            }
        }
    }

}
