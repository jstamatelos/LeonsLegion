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

    @Test
    public void testThatPlayerCanMakeInsideBet() {
        //Given:
        ArrayList<RouletteBet> bet = new ArrayList<RouletteBet>();
        ArrayList<RoulettePlayer> players = new ArrayList<RoulettePlayer>();
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("00");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("00"));
    }

    @Test
    public void testThatPlayerCanMakeInsideBetAfterInvalidNumberChoice() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("40");
        Mockito.when(asker.askForInput("You must bet 0, 00, or a number between 1 and 36.")).thenReturn("00");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("00"));
    }

    @Test
    public void testThatPlayerCanMakeInsideBetAfterInvalidZeroChoice() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("000");
        Mockito.when(asker.askForInput("You must bet 0, 00, or a number between 1 and 36.")).thenReturn("00");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("00"));
    }

    @Test
    public void testThatPlayerCanMakeAnOutsideBetAfterInvalidNumberChoice() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("40");
        Mockito.when(asker.askForInput("You must bet 0, 00, or a number between 1 and 36.")).thenReturn("1st C");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("1st C"));
    }

    @Test
    public void testThatPlayerCanMakeAnInsideBetAfterInvalidInput() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("cc");
        Mockito.when(asker.askForInput("You must bet from one of the options above.")).thenReturn("00");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("00"));
    }

    @Test
    public void testThatPlayerCanMakeAn1stCBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("1st C");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("1st C"));
    }

    @Test
    public void testThatPlayerCanMakeAn2ndCBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("2nd C");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("2nd C"));
    }

    @Test
    public void testThatPlayerCanMakeAn3rdCBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("3rd C");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("3rd C"));
    }

    @Test
    public void testThatPlayerCanMakeAn1stDBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("1st D");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("1st D"));
    }

    @Test
    public void testThatPlayerCanMakeAn2ndDBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("2nd D");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("2nd D"));
    }

    @Test
    public void testThatPlayerCanMakeAn3rdDBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("3rd D");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("3rd D"));
    }

    @Test
    public void testThatPlayerCanMakeAnFrontBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("Front");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("Front"));
    }

    @Test
    public void testThatPlayerCanMakeBackBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("Back");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("Back"));
    }

    @Test
    public void testThatPlayerCanMakeAnOddBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("Odd");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("Odd"));
    }

    @Test
    public void testThatPlayerCanMakeEvenBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("Even");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("Even"));
    }

    public void testThatPlayerCanMakeRedBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("Red");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("Red"));
    }

    @Test
    public void testThatPlayerCanMakeBlackBet() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("Black");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("Black"));
    }

    @Test
    public void testThatPlayerCanMakeAnOutsideBetAfterInvalidInput() {
        //Given:
        InputAsker asker = Mockito.mock(InputAsker.class);

        //When:
        Mockito.when(asker.askForInput("Place a bet by using the options above. Fractional part of input will be ignored.")).thenReturn("cc");
        Mockito.when(asker.askForInput("You must bet from one of the options above.")).thenReturn("1st C");

        //Then:
        Assert.assertTrue(RouletteBetHandler.handleAnyBet(asker).equals("1st C"));
    }

    @Test
    public void testThatPlayerCanWinInsideBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "00";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "00";
        double expectedOutput = 90000 + (35*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForInsideBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseInsideBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "00";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "1";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForInsideBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }


    @Test
    public void testThatPlayerCanWinFirstDozenBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "1st D";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "1";
        double expectedOutput = 90000 + (3*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinSecondDozenBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "2nd D";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "13";
        double expectedOutput = 90000 + (3*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinThirdDozenBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "3rd D";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "27";
        double expectedOutput = 90000 + (3*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseDozenBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "2nd D";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "1";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideDozenBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinFirstColumnBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "1st C";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "1";
        double expectedOutput = 90000 + (3*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinSecondColumnBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "2nd C";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "2";
        double expectedOutput = 90000 + (3*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinThirdColumnBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "3rd C";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "3";
        double expectedOutput = 90000 + (3*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinLoseColumnBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "3rd C";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "1";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForOutsideColumnBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinFrontBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Front";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "3";
        double expectedOutput = 90000 + (2*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseFrontBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Front";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "36";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinEvenBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Even";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "36";
        double expectedOutput = 90000 + (2*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseEvenBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Even";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "3";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinOddBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Odd";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "3";
        double expectedOutput = 90000 + (2*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseOddBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Odd";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "36";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForEvenOrOddBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinBackBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Back";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "36";
        double expectedOutput = 90000 + (2*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseBackBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Back";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "3";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForFrontOrBackBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinRedBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Red";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "1";
        double expectedOutput = 90000 + (2*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForColorBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseRedBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Red";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "2";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForColorBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanWinBlackBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Black";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "2";
        double expectedOutput = 90000 + (2*10000);
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForColorBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

    @Test
    public void testThatPlayerCanLoseBlackBet() {
        //Given:
        Account account = new Account();
        RoulettePlayer player = new RoulettePlayer(account, new ArrayList<RouletteBet>());
        String newBetType = "Black";
        String newBetValue = player.placeBet("100");
        float newBetValueAsFloat = Float.parseFloat(newBetValue);
        newBetValueAsFloat *= 100;
        long newBetValueAsLong = (long) newBetValueAsFloat;
        player.makeRouletteBet(newBetType, newBetValueAsLong);
        String spin = "1";
        double expectedOutput = 90000;
        //When:
        player.getAccount().setAccountBalance(RouletteBetHandler.checkPlayerBetsForColorBetWins(player.getBetList(), spin));
        double actualOutput = player.getBalance();
        Console.printDouble(actualOutput);
        //Then:
        Assert.assertTrue(expectedOutput == actualOutput);
    }

}
