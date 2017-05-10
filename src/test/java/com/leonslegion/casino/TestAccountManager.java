package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sarahweisser on 5/9/17.
 */
public class TestAccountManager {

    @Test
    public void testAccountManagerShowBalance() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.getAccounts().add(acct);
        AccountManager.getAccounts().add(acct2);
        String expectedResult = "Account ID: 1\nAccount Holder: Leon\nAccount Balance: $1000.00";

        //when
        String actualResult = AccountManager.getAccounts().get(0).toString();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testAddAccountToManager() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        Account expectedResult = acct2;

        //when
        Account actualResult = AccountManager.getAccounts().get(1);

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testRemoveAccountFromManager() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        AccountManager.removeAccount(acct);
        Account expectedResult = acct2;

        //when
        Account actualResult = AccountManager.getAccounts().get(0);

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testAccountManagerGetAccountBalance() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        double expectedResult = 1000;

        //when
        double actualResult = AccountManager.getBalance(acct);

        //then
        Assert.assertEquals(expectedResult, actualResult, 0);

    }

    @Test
    public void testAccountManagerGetAccountHolderName() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        String expectedResult = "Leon";

        //when
        String actualResult = AccountManager.getAccountHolderName(acct);

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testAdjustAccountBalanceThroughManager() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        AccountManager.adjustAccountBalance(acct, 2000);
        double expectedResult = 3000.00;

        //when
        double actualResult = AccountManager.getBalance(acct);

        //then
        Assert.assertEquals(expectedResult, actualResult, 0);

    }

    @Test
    public void testFindAccountByName() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        Account expectedResult = acct2;

        //when
        Account actualResult = AccountManager.findAccount("Hunter");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testFindAccountById() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        Account expectedResult = acct;

        //when
        Account actualResult = AccountManager.findAccount(1);

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testFindIndexByName() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        int expectedResult = 1;

        //when
        int actualResult = AccountManager.findAccountIndex("Hunter");

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testFindIndexById() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        AccountManager.addAccount(acct);
        AccountManager.addAccount(acct2);
        int expectedResult = 1;

        //when
        int actualResult = AccountManager.findAccountIndex(2);

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }
}
