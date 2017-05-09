package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sarahweisser on 5/9/17.
 */
public class TestAccount {

    @Test
    public void testIdCounter() {

        //given
        Account acct = new Account();
        Account acct2 = new Account();
        long expectedResult = 2;

        //when
        long actualResult = acct2.getIdCounter();

        //then
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAccountConstructorCountsId() {

        //given
        Account acct = new Account("Leon");
        Account acct2 = new Account("Hunter");
        long expectedResult = 2;

        //when
        long actualResult = acct2.getId();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testAccountToString() {

        //given
        Account acct = new Account("Leon");
        String expectedResult = "Account ID: 1\nAccount Holder: Leon\nAccount Balance: $1000.00";

        //when
        String actualResult = acct.toString();

        //then
        Assert.assertEquals(expectedResult, actualResult);

    }

}
