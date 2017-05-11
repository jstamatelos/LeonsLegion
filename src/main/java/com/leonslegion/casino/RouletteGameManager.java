package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;
import java.util.*;


public class RouletteGameManager {



    public static void playRoulette() {
        RouletteCoreGameplayEngine core = new RouletteCoreGameplayEngine();
        String numberOfPlayers = RouletteCoreGameplayEngine.rouletteGameEngineSetup();
        ArrayList<RoulettePlayer> roulettePlayers = RouletteCoreGameplayEngine.createRoulettePlayerList(Integer.parseInt(numberOfPlayers));
        boolean currentlyPlayingRound = true;
        while (currentlyPlayingRound) {
            if (!RouletteCoreGameplayEngine.exitOpportunity()) {break;}
            RouletteCoreGameplayEngine.gatherPlayerBets(roulettePlayers);
            String spinResult = core.spin();
            checkPlayerBetsForResults(roulettePlayers, spinResult);

        }
    }



    public static void checkPlayerBetsForResults(ArrayList<RoulettePlayer> roulettePlayers, String spinResult) {
        System.out.println("The ball landed in: " + spinResult);
        System.out.println();
        for (RoulettePlayer player : roulettePlayers) {
            RouletteBetHandler.checkPlayerBetsForInsideBetWins(player, spinResult);
            RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(player, spinResult);
            RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(player, spinResult);
            RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(player, spinResult);
            RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(player, spinResult);
            RouletteBetHandler.checkPlayerBetsForColorBetWins(player, spinResult);
        }


    }

}
