package com.leonslegion.casino.RoulettePackage;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;
import java.util.*;



public class RouletteGameManager {



    public static void playRoulette() {
        RouletteInputOutputAndPrint.printWelcomeMessage();
        RouletteCoreGameplayEngine coreEngine = new RouletteCoreGameplayEngine();
        long numberOfPlayers = RouletteInputOutputAndPrint.getNumberOfPlayers();
        ArrayList<RoulettePlayer> players = coreEngine.createRoulettePlayerList(numberOfPlayers);
        boolean currentlyPlayingRound = true;
        while (currentlyPlayingRound) {
            doesPlayerHaveABalance(players);
            doesPlayerWantToExit(players);
            if (players.size() == 0) {
                Console.println("No more players! Leaving roulette table!");
                break;
            }
            RouletteCoreGameplayEngine.gatherPlayerBets(players);
            String spinResult = coreEngine.spin();
            checkPlayerBetsForResults(players, spinResult);
        }
    }



    public static void doesPlayerWantToExit (ArrayList<RoulettePlayer> roulettePlayers) {
        for (int count = 0; count < roulettePlayers.size(); count++) {
            Console.println("Does Player #" + roulettePlayers.get(count).getAccount().getId() + " want to exit?");
            if (RouletteCoreGameplayEngine.exitInput()) {
                Console.println("Player #" + roulettePlayers.get(count).getAccount().getId() + " has exited.");
                long remainingBalance = roulettePlayers.get(count).getBalance();
                long accountID = roulettePlayers.get(count).getAccount().getId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        long originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        long balanceDifference = remainingBalance - originalBalance;
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
                Console.println("Player #" + roulettePlayers.get(count).getAccount().getId() + " has no money!");
                Console.println("Player #" + roulettePlayers.get(count).getAccount().getId() + " removed!");
                long remainingBalance = 0;
                long accountID = roulettePlayers.get(count).getAccount().getId();
                for (int account = 0; account < Account.AccountManager.getAccounts().size(); account++) {
                    if (Account.AccountManager.getAccounts().get(account).getId() == accountID) {
                        long originalBalance = Account.AccountManager.getAccounts().get(account).getAccountBalance();
                        long balanceDifference = -originalBalance;
                        Account.AccountManager.getAccounts().get(account).setAccountBalance(balanceDifference);
                    }
                }
                roulettePlayers.remove(roulettePlayers.get(count));
                count--;
            }
        }
    }



    public static void checkPlayerBetsForResults(ArrayList<RoulettePlayer> roulettePlayers, String spinResult) {
        Console.printDashes();
        Console.println("The ball landed in: " + spinResult);
        for (int count = 0; count < roulettePlayers.size(); count++) {
            RoulettePlayer player = roulettePlayers.get(count);
            Console.printDashes();
            Console.println("Checking bets for Player #" + player.getAccount().getId());
            Console.printDashes();
            ArrayList<RouletteBet> betList = player.getBetList();
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForInsideBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(betList, spinResult));
            player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForColorBetWins(betList, spinResult));
            Console.print("Player #" + roulettePlayers.get(count).getAccount().getId() + " new balance: ");
            Console.printMoney(roulettePlayers.get(count).getBalance());
            Console.printDashes();
            player.resetBetList();
        }
    }
}