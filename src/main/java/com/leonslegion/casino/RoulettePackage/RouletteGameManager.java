package com.leonslegion.casino.RoulettePackage;
import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.Console;

import java.util.*;



public class RouletteGameManager {



    public static void playRoulette() {
        RouletteCoreGameplayEngine coreEngine = new RouletteCoreGameplayEngine();
        RoulettePrint.printWelcomeMessage();
        long numberOfPlayers = RouletteInputOutput.getNumberOfPlayers(new InputAsker(System.in, System.out));
        ArrayList<RoulettePlayer> players = RouletteInputOutput.createRoulettePlayerList(numberOfPlayers);
        boolean currentlyPlayingRound = true;
        while (currentlyPlayingRound) {
            RouletteCoreGameplayEngine.doesPlayerHaveABalance(players);
            RouletteCoreGameplayEngine.doesPlayerWantToExit(players, new InputAsker(System.in, System.out));
            if (players.size() == 0) {
                Console.println("No more players! Leaving roulette table!");
                break;
            }
            RouletteCoreGameplayEngine.gatherPlayerBets(players);
            String spinResult = coreEngine.spin();
            RouletteCoreGameplayEngine.checkPlayerBetsForResults(players, spinResult);
        }
    }
}