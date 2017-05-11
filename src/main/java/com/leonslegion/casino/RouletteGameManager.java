package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;
import java.util.*;


public class RouletteGameManager {


    public static void playRoulette() {
        String numberOfPlayers = RouletteCoreGameplayEngine.rouletteGameEngineSetup();
        ArrayList<RoulettePlayer> roulettePlayers = RouletteCoreGameplayEngine.createRoulettePlayerList(Integer.parseInt(numberOfPlayers));
        boolean currentlyPlayingRound = true;
        while (currentlyPlayingRound) {
            RouletteCoreGameplayEngine.exitOpportunity();
            RouletteCoreGameplayEngine.gatherPlayerBets(roulettePlayers);
        }
    }




    public static void checkPlayerBetsForResults(RoulettePlayer playerOne, String spinResult) {
        System.out.println("The ball landed in: " + spinResult);
        System.out.println();
        RouletteBetChecker.checkPlayerBetsForInsideBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForOutsideDozenBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForOutsideColumnBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForEvenOrOddBetWins(playerOne, spinResult);
        RouletteBetChecker.checkPlayerBetsForFrontOrBackBetWins(playerOne, spinResult);

    }



}
