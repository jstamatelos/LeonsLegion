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
    public void testThatPlayerCanWinInsideBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        RouletteBet rouletteBet = new RouletteBet("00", 100);
        bet.add(rouletteBet);
        RoulettePlayer playerOne = new RoulettePlayer(1000, 101, bet);
        String spin = "00";
        double expectedOutput = 900 + (35*100);

        //When:
        RouletteGameManager.checkPlayerBetsForInsideBetWins(playerOne, spin);
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
        RouletteGameManager.checkPlayerBetsForOutsideDozenBetWins(playerOne, spin);
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
        RouletteGameManager.checkPlayerBetsForOutsideColumnBetWins(playerOne, spin);
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
        RouletteGameManager.checkPlayerBetsForEvenOrOddBetWins(playerOne, spin);
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
        RouletteGameManager.checkPlayerBetsForFrontOrBackBetWins(playerOne, spin);
        double actualOutput = playerOne.getBalance();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }
}
