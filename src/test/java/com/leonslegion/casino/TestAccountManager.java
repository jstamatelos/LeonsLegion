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
        AccountManager mngr = new AccountManager();
        mngr.getAccounts().add(acct);
        mngr.getAccounts().add(acct2);
        String expectedResult = "Account ID: 1\nAccount Holder: Leon\nAccount Balance: $1000.00";

        //when
        System.out.println(mngr.getAccounts().toString());
        //String actualResult = mngr.getAccounts().get(0).toString();

        //then
        //Assert.assertEquals(expectedResult, actualResult);

    }
}
