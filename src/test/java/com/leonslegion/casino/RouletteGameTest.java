package com.leonslegion.casino;

import com.leonslegion.casino.RoulettePackage.RouletteBet;
import com.leonslegion.casino.RoulettePackage.RouletteBetHandler;
import com.leonslegion.casino.RoulettePackage.RouletteCoreGameplayEngine;
import com.leonslegion.casino.RoulettePackage.RoulettePlayer;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class RouletteGameTest {

    @Test
    public void testThatPlayerCanMakeBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RouletteBet rouletteBet = new RouletteBet("00", 100);
        bet.add(rouletteBet);
        players.add(new RoulettePlayer(1000, 101, bet));
        int expectedOutput = 1;

        //When:

        int actualOutput = bet.size();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }


    @Test
    public void testThatPlayerCanWinInsideBet() {
        //Given:
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        String newBetType = "00";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "00";
        double expectedOutput = 900 + (35*100);

        //When:
        player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForInsideBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideDozenBet() {
        //Given:
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        String newBetType = "1st D";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (3*100);

        //When:
        player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColumnBet() {
        //Given:
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        String newBetType = "2nd C";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "20";
        double expectedOutput = 900 + (3*100);

        //When:
        player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideEvenOrOddBet() {
        //Given:
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        String newBetType = "Even";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "2";
        double expectedOutput = 900 + (2*100);

        //When:
        player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);


        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideFrontOrBackBet() {
        //Given:
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        String newBetType = "Front";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (2*100);

        //When:
        player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColorBet() {
        //Given:
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        String newBetType = "Red";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (2*100);

        //When:
        player.setBalance(player.getBalance() + RouletteBetHandler.checkPlayerBetsForColorBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

}
