package com.leonslegion.casino;

import com.leonslegion.casino.AccountPackage.Account;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sarahweisser on 5/9/17.
 */
public class TestAccount {

    @Test
    public void accountConstructorWithUserNameTest() {

        //given
        Account account1 = new Account("Leon");
        String expected = "Leon";

        //when
        String actual = account1.getAccountHolderName();

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void accountConstructorSetsBalanceTest() {

        //given
        Account account1 = new Account("Leon");
        long expected = 100000;

        //when
        long actual = account1.getAccountBalance();

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void setAccountIdTest() {

        //given
        Account account1 = new Account("Leon");
        account1.setId(50);
        long expected = 50;

        //when
        long actual = account1.getId();

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void adjustAccountBalanceUp() {

        //given
        Account account1 = new Account("Leon");
        account1.setAccountBalance(1000);
        long expected = 101000;

        //when
        long actual = account1.getAccountBalance();

        //then
        Assert.assertEquals(expected, actual);

    }
    @Test
    public void adjustAccountBalanceDown() {

        //given
        Account account1 = new Account("Leon");
        account1.setAccountBalance(-1000);
        long expected = 99000;

        //when
        long actual = account1.getAccountBalance();

        //then
        Assert.assertEquals(expected, actual);

    }
/*
    @Test
    public void testAccountToString() {

        //given
        Account acct = new Account("Leon");
        String expectedResult = "Account ID: 0\nAccount Holder: Leon\nAccount Balance: $1000.00";

        //when
        String actualResult = acct.toString();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }*/
}
