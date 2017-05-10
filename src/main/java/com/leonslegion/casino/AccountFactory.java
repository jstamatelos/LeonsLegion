package com.leonslegion.casino;

/**
 * Created by sarahweisser on 5/10/17.
 */
public class AccountFactory {

    public static Account createAccountWithName(String accountHolderName) {
        return new Account(accountHolderName);
    }

}
