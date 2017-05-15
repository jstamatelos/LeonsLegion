package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import com.leonslegion.casino.RoulettePackage.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;

import java.util.*;

public class RouletteGameTest {

    @Test
    public void testGetNumberOfPlayersInputCanBe0() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        //When
        Mockito.when(asker.askForInput("Please enter integer number of players. 2 is max.")).thenReturn("0");
        //Then
        Assert.assertTrue(RouletteInputOutput.getNumberOfPlayers(asker) == 0);
    }

    @Test
    public void testGetNumberOfPlayersInputCanBeInvalidOnce() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        //When
        Mockito.when(asker.askForInput("Please enter integer number of players. 2 is max.")).thenReturn("c");
        Mockito.when(asker.askForInput("Not a valid input!")).thenReturn("1");
        //Then
        Assert.assertTrue(RouletteInputOutput.getNumberOfPlayers(asker) == 1);
    }


    @Test
    public void testGetPlayerIDInput() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        //When
        Mockito.when(asker.askForInput("Please enter your ID. Fractional components will be ignored.")).thenReturn("5");
        //Then
        Assert.assertTrue(RouletteInputOutput.getPlayerID(asker) == 5);
    }

    @Test
    public void testGetPlayerIDCanBeInvalidOnce() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        //When
        Mockito.when(asker.askForInput("Please enter your ID. Fractional components will be ignored.")).thenReturn("c");
        Mockito.when(asker.askForInput("Not a valid input!")).thenReturn("5");
        //Then
        Assert.assertTrue(RouletteInputOutput.getPlayerID(asker) == 5);
    }

    @Test
    public void testGetPlayerIDCanBeNegativeOnce() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        //When
        Mockito.when(asker.askForInput("Please enter your ID. Fractional components will be ignored.")).thenReturn("-4");
        Mockito.when(asker.askForInput("Not a valid input!")).thenReturn("5");
        //Then
        Assert.assertTrue(RouletteInputOutput.getPlayerID(asker) == 5);
    }

    @Test
    public void testPlayerIDAsDouble() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        //When
        Mockito.when(asker.askForInput("Please enter your ID. Fractional components will be ignored.")).thenReturn("2.35");

        //Then
        Assert.assertTrue(RouletteInputOutput.getPlayerID(asker) == 2);
    }


    @Test
    public void testIfPlayerIDThatExistsIsFound() {
        //Given:
        Account.AccountManager.addAccount("leon");
        long ID = 1;
        boolean expectedOutput = true;

        //When
        boolean actualOutput = RouletteInputOutput.checkIfPlayerIDExists(ID);

        //Then
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testIfPlayerIDThatDoesNotExistIsNotFound() {
        //Given:
        Account.AccountManager.addAccount("leon");
        long ID = 999;
        boolean expectedOutput = false;

        //When
        boolean actualOutput = RouletteInputOutput.checkIfPlayerIDExists(ID);

        //Then
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatAccountNotRegisteredIsAdded() {
        //Given:
        Account.AccountManager.addAccount("leon");
        long ID = 1;
        ArrayList<RoulettePlayer> players = new ArrayList<>();
        boolean expectedOutput = false;

        //When
        boolean actualOutput = RouletteInputOutput.checkIfPlayerAlreadyRegistered(players, ID);

        //Then
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatRegisteredAccountIsNotAdded() {
        //Given:
        Account.AccountManager.addAccount("leon");
        long ID = 1;
        Account newAccount = Account.AccountManager.findAccount("leon");
        RoulettePlayer newPlayer = new RoulettePlayer(newAccount, new ArrayList<RouletteBet>());
        ArrayList<RoulettePlayer> players = new ArrayList<>();
        players.add(newPlayer);
        boolean expectedOutput = true;

        //When
        boolean actualOutput = RouletteInputOutput.checkIfPlayerAlreadyRegistered(players, ID);

        //Then
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanExit() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        Account.AccountManager.addAccount("leon");
        long ID = 1;

        //When
        Mockito.when(asker.askForInput("Type 'exit' before the round starts to leave game. Or type any other letter to stay.")).thenReturn("exit");

        //Then
        Assert.assertTrue(RouletteCoreGameplayEngine.exitInput(asker));
    }

    @Test
    public void testThatPlayerCanStay() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);
        Account.AccountManager.addAccount("leon");
        long ID = 1;

        //When
        Mockito.when(asker.askForInput("Type 'exit' before the round starts to leave game. Or type any other letter to stay.")).thenReturn("n");

        //Then
        Assert.assertTrue(!RouletteCoreGameplayEngine.exitInput(asker));
    }


/*
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
        long newBetValueAsDouble = Long.parseLong(newBetValue);
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
        long newBetValueAsDouble = Long.parseLong(newBetValue);
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
        long newBetValueAsDouble = Long.parseLong(newBetValue);
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
        long newBetValueAsDouble = Long.parseLong(newBetValue);
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
        long newBetValueAsDouble = Long.parseLong(newBetValue);
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
        long newBetValueAsDouble = Long.parseLong(newBetValue);
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
*/
}
