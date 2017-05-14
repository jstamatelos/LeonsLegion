package com.leonslegion.casino.AccountPackage;

/**
 * Created by sarahweisser on 5/14/17.
 */
public class AccountFactory {

        public static Account createAccountWithName(String accountHolderName) {
            return new Account(accountHolderName);
        }


}
