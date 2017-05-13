package com.leonslegion.casino;


import com.leonslegion.casino.AccountPackage.Account;

import java.util.Scanner;

/**
 * Created by jarrydstamatelos on 5/12/17.
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String val, Object... args) {
        System.out.format(val, args);
    }

    public static void println(String val, Object... vals) {
        print(val + "\n", vals);
    }

    public static void printDouble(double val, Object... args){println(Double.toString(val), args);}

    public static String getStringInput(String prompt, Object... args) {
        println(prompt, args);
        return scanner.nextLine();
    }

    public static Double getDoubleInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Double doubleInput = Double.parseDouble(stringInput);
            return doubleInput;
        } catch (NumberFormatException nfe) {
            println("[ %s ] is an invalid input!", stringInput);
            println("Try inputting a numeric value!");
            return getDoubleInput(prompt, args);
        }
    }

    public static Long getLongInput(String prompt, long numberOfAttempts, Object... args) {
        if (numberOfAttempts == 0) {
            return (long) 0;
        }
        println("Number of Input Attempts Remaining: " + numberOfAttempts);
        String stringInput = getStringInput(prompt, args);
        try {
            long longInput = Long.parseLong(stringInput);
            if (longInput < 0) {
                println("Invalid negative input");
                return getLongInput(prompt, numberOfAttempts - 1, args);
            }
            return longInput;
        } catch (NumberFormatException nfe) {
            println("");
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting an integer value!");
            return getLongInput(prompt, numberOfAttempts - 1, args);
        }
    }

    public static Integer getIntegerInput(String prompt, long numberOfAttempts, Object... args) {
        return getLongInput(prompt, numberOfAttempts, args).intValue();
    }

    public static void printDashes() {
        repeatPrint(80, "-");
        println("");
    }

    public static void repeatPrint(int numberOfRepeats, String val) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfRepeats; i++) {
            sb.append(val);
        }
        print(sb.toString());
    }

    public static void printAccountNotFoundMessage() {
        println("");
        println("Account Not Found!");
    }

    public static void printAccountAlreadyLoaded() {
        println("");
        println("Account Already Loaded!");
    }

    public static void printAccountAccepted() {
        println("");
        println("ID Accepted! Loading Account Info...");
    }

    public static void printAccountInformation(Account account) {
        println("");

    }

}
