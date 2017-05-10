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
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("00", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "00";
        double expectedOutput = 900 + (35*100);

        //When:
        RouletteBetChecker.checkPlayerBetsForInsideBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseInsideBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("00", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "1";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForInsideBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideDozenBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st D", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "1";
        double expectedOutput = 900 + (rouletteBet.getBetValue()*3);


        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideDozenBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideDozenBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st D", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "19";
        double expectedOutput = 900;


        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideDozenBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColumnBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st C", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "1";
        double expectedOutput = 900 + (rouletteBet.getBetValue()*3);

        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideColumnBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideColumnBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st C", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "2";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideColumnBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideEvenOrOddBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Even", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "2";
        double expectedOutput = 900 + (rouletteBet.getBetValue()*2);

        //When:
        RouletteBetChecker.checkPlayerBetsForEvenOrOddBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideEvenOrOddBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Even", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "1";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForEvenOrOddBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideFrontOrBackBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Front", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "1";
        double expectedOutput = 900 + (rouletteBet.getBetValue()*2);

        //When:
        RouletteBetChecker.checkPlayerBetsForFrontOrBackBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideFrontOrBackBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Front", 100);
        bet.add(rouletteBet);
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        players.add(new RoulettePlayer(1000, 101, bet));
        String spin = "19";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForFrontOrBackBetWins(players.get(0), spin);
        double actualOutput = players.get(0).getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }
*/
}
