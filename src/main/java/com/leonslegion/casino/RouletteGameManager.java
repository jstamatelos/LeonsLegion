package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;
import java.util.*;


public class RouletteGameManager {

    public static void main(String[] args) {
      Account sarah = AccountFactory.createAccountWithName("Brian");
      System.out.println(sarah.getId());
      Account ziggy = AccountFactory.createAccountWithName("Ziggy");
      System.out.println(ziggy.getId());
      Account cameron = AccountFactory.createAccountWithName("Cameron");
      System.out.println(cameron.getId());
      AccountManager.addAccount(sarah);
      AccountManager.addAccount(ziggy);
      AccountManager.addAccount(cameron);
      playRoulette();
    }

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
                roulettePlayers.remove(roulettePlayers.get(count));
            }
        }
    }



    public static void checkPlayerBetsForResults(ArrayList<RoulettePlayer> roulettePlayers, String spinResult) {
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

            for (int i = 0; i < player.getBetList().size(); i++) {
                player.getBetList().remove(i);
            }
        }


    }

}
