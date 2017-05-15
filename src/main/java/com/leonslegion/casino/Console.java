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

    public static void printMoney(long val) {
        println(moneyToString(val));
    }

    public static String getStringInput(String prompt, Object... args) {
        println(prompt, args);
        return scanner.nextLine();
    }

    public static long getMoneyInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            double doubleInput = Double.parseDouble(stringInput);
            return Math.round(doubleInput*100);
        } catch (NumberFormatException nfe) {
            println("[ %s ] is an invalid input!", stringInput);
            println("Try inputting a numeric value!");
            return getMoneyInput(prompt, args);
        }
    }

    public static String moneyToString(long amount) {
        long whole = amount / 100;
        long part = amount % 100;
        return ("$" + whole + "." + part);
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

    public static Long getLongInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            long longInput = Long.parseLong(stringInput);
            if (longInput < 0) {
                println("Invalid negative input");
                return (long) -1;
            }
            return longInput;
        } catch (NumberFormatException nfe) {
            println("");
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting an integer value!");
            return (long) -1;
        }
    }

    public static Integer getIntegerInput(String prompt, Object... args) {
        return getLongInput(prompt, args).intValue();
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

}
