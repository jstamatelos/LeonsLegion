package com.leonslegion.casino;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sarahweisser on 5/10/17.
 */
public class TestAccountFactory {

    @Test
    public void testAccountFactory() {

        //given

        AccountFactory factory = new AccountFactory();
        Account acct = factory.createAccountWithName("Leon");
        Account acct2 = factory.createAccountWithName("Hunter");

        String expectedResult = "Account ID: 1\nAccount Holder: Leon\nAccount Balance: $1000.00";

        //when
        String actualResult = acct.toString();

        //then
        Assert.assertEquals(expectedResult, actualResult);


    }
}
