package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
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
        Account account = new Account();
        players.add(new RoulettePlayer(account, bet));
        int expectedOutput = 1;

        //When:

        int actualOutput = bet.size();

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }


    @Test
    public void testThatPlayerCanWinInsideBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "00";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "00";
        double expectedOutput = 900 + (35*100);

        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForInsideBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideDozenBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "1st D";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (3*100);

        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColumnBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "2nd C";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "20";
        double expectedOutput = 900 + (3*100);

        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideEvenOrOddBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Even";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "2";
        double expectedOutput = 900 + (2*100);

        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);


        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideFrontOrBackBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Front";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (2*100);

        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOutsideColorBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Red";
        String newBetValue = player.placeBet("100");
        double newBetValueAsDouble = Double.parseDouble(newBetValue);
        player.makeRouletteBet(newBetType, newBetValueAsDouble);

        String spin = "1";
        double expectedOutput = 900 + (2*100);

        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForColorBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);

        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

}
