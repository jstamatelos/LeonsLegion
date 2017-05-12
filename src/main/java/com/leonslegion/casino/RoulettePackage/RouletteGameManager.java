package com.leonslegion.casino.RoulettePackage;

import com.leonslegion.casino.AccountPackage.Account;

import java.util.*;


public class RouletteGameManager {



    public static void playRoulette() {
        RouletteCoreGameplayEngine core = new RouletteCoreGameplayEngine();
        String numberOfPlayers = RouletteCoreGameplayEngine.rouletteGameEngineSetup();
        ArrayList<RoulettePlayer> roulettePlayers = RouletteCoreGameplayEngine.createRoulettePlayerList(Integer.parseInt(numberOfPlayers));
        boolean currentlyPlayingRound = true;
        while (currentlyPlayingRound) {
            doesPlayerHaveABalance(roulettePlayers);
            doesPlayerWantToExit(roulettePlayers);
            if (roulettePlayers.size() == 0) {
                System.out.println("No more players! Leaving roulette table!");
                break;
            }
            RouletteCoreGameplayEngine.gatherPlayerBets(roulettePlayers);
            String spinResult = core.spin();
            checkPlayerBetsForResults(roulettePlayers, spinResult);
        }
    }



    public static void doesPlayerWantToExit (ArrayList<RoulettePlayer> roulettePlayers) {
        for (int count = 0; count < roulettePlayers.size(); count++) {
            System.out.println("Does Player #" + roulettePlayers.get(count).getAccountId() + " want to exit?");
            if (RouletteCoreGameplayEngine.exitInput()) {
                System.out.println("Player #" + roulettePlayers.get(count).getAccountId() + " has exited.");
                double remainingBalance = roulettePlayers.get(count).getBalance();
                long accountID = roulettePlayers.get(count).getAccountId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        double originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        double balanceDifference = remainingBalance - originalBalance;
                        Account.AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
                    }
                }
                roulettePlayers.remove(roulettePlayers.get(count));
                count --;
            }
        }
    }



    public static void doesPlayerHaveABalance (ArrayList<RoulettePlayer> roulettePlayers) {
        for (int count = 0; count < roulettePlayers.size(); count++) {
            if (roulettePlayers.get(count).getBalance() <= 0) {
                System.out.println("Player #" + roulettePlayers.get(count).getAccountId() + " has no money!");
                System.out.println("Player #" + roulettePlayers.get(count).getAccountId() + " removed!");
                double remainingBalance = 0;
                long accountID = roulettePlayers.get(count).getAccountId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        double originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        double balanceDifference = -originalBalance;
                        Account.AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
                    }
                }
                roulettePlayers.remove(roulettePlayers.get(count));
                count--;
            }
        }
    }



    public static void checkPlayerBetsForResults(ArrayList<RoulettePlayer> roulettePlayers, String spinResult) {
        System.out.println();
        System.out.println("The ball landed in: " + spinResult);
        for (int count = 0; count < roulettePlayers.size(); count++) {
            RoulettePlayer player = roulettePlayers.get(count);
            System.out.println();
            System.out.println("Checking bets for Player #" + player.getAccountId());
            System.out.println();
            ArrayList<RouletteBet> betList = player.getBetList();
            player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForInsideBetWins(betList, spinResult));
            player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(betList, spinResult));
            player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(betList, spinResult));
            player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(betList, spinResult));
            player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForColorBetWins(betList, spinResult));
            System.out.print("Player #" + roulettePlayers.get(count).getAccountId() + " new balance: $");
            System.out.printf("%,.2f", roulettePlayers.get(count).getBalance());
            System.out.println();
            player.resetBetList();

        }


    }

}
