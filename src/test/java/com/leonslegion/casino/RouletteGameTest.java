package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class RouletteGameTest {
/*
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
    public void testThatPlayerCannotOverdraw() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(100, 101, bet));
        String betValue = "1000";
        double betValueAsDouble = Double.parseDouble(players.get(0).placeBet(betValue));
        players.get(0).makeRouletteBet("00", betValueAsDouble);

        //When:

        //Then:
        //Tests for printed output
    }


    @Test
    public void testThatPlayerCanWinInsideBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "00";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "00";
        double expectedOutput = 900 + (35*100);

        //When:
        RouletteBetHandler.checkPlayerBetsForInsideBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseInsideBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "5";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "00";
        double expectedOutput = 900;

        //When:
        RouletteBetHandler.checkPlayerBetsForInsideBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideDozenBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "1st D";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (3*100);

        //When:
        RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideDozenBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "1st D";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "31";
        double expectedOutput = 900;

        //When:
        RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColumnBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "1st C";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (3*100);

        //When:
        RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideColumnBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "1st C";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "2";
        double expectedOutput = 900;

        //When:
        RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideEvenOrOddBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "Even";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "2";
        double expectedOutput = 900 + (2*100);

        //When:
        RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideEvenOrOddBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "Even";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900;

        //When:
        RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideFrontOrBackBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "Front";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "2";
        double expectedOutput = 900 + (2*100);

        //When:
        RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideFrontOrBackBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "Front";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "29";
        double expectedOutput = 900;

        //When:
        RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColorBet() {
        //Given:
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "Red";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (2*100);

        //When:
        RouletteBetHandler.checkPlayerBetsForColorBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideColorBet() {
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        RoulettePlayer player = new RoulettePlayer(1000, 101, RouletteCoreGameplayEngine.returnEmptyRouletteBetList());
        players.add(player);
        String newBetType = "Red";
        String newBetValue = players.get(0).placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        players.get(0).makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "2";
        double expectedOutput = 900;

        //When:
        RouletteBetHandler.checkPlayerBetsForColorBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }
*/
}
