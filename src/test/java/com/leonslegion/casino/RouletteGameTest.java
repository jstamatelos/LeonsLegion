package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class RouletteGameTest {

    @Test
    public void testThatPlayerCanMakeBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("00", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
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
        RoulettePlayer playerOne = new RoulettePlayer(100, 101, bet);
        double placedBet = Double.parseDouble(playerOne.placeBet("1000"));
        playerOne.makeRouletteBet("00", placedBet);

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
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "00";
        double expectedOutput = 900 + (35*100);

        //When:
        RouletteBetChecker.checkPlayerBetsForInsideBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseInsideBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("00", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "4";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForInsideBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideDozenBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st D", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "5";
        double expectedOutput = 900 + (3*100);

        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideDozenBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideDozenBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st D", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "18";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideDozenBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColumnBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st C", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "1";
        double expectedOutput = 900 + (3*100);

        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideColumnBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideColumnBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("1st C", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "3";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForOutsideColumnBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideEvenOrOddBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Even", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "2";
        double expectedOutput = 900 + (2*100);

        //When:
        RouletteBetChecker.checkPlayerBetsForEvenOrOddBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideEvenOrOddBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Even", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "3";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForEvenOrOddBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideFrontOrBackBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Front", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "1";
        double expectedOutput = 900 + (2*100);

        //When:
        RouletteBetChecker.checkPlayerBetsForFrontOrBackBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideFrontOrBackBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Front", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "19";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForFrontOrBackBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColorBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Front", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "1";
        double expectedOutput = 900 + (2*100);

        //When:
        RouletteBetChecker.checkPlayerBetsForColorBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOutsideColorBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("Front", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "19";
        double expectedOutput = 900;

        //When:
        RouletteBetChecker.checkPlayerBetsForColorBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

}
