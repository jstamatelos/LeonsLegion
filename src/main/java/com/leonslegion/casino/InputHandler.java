package com.leonslegion.casino;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;
/**
 * Created by danielprahl on 5/9/17.
 */
public final class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    @Deprecated // do not instantiate, all methods are static
    private InputHandler(){}

    // return String from input string
    public static String getStringInput(String promptUserForInput) {
        Console.println(promptUserForInput);
        String userInput = scanner.nextLine();
        return userInput;
    }

    // return double from input string
    public static Double getDoubleInput(String promptUserForInput) {
        try {
            return Double.parseDouble(getStringInput(promptUserForInput));
        }catch (Exception e){
            return getDoubleInput("Please enter a numerical value.");
        }
    }

    // return long from input string
    public static Long getLongInput(String promptUserForInput) {
        try {
            return Long.parseLong(getStringInput(promptUserForInput));
        }catch (Exception e){
            return getLongInput("Please enter a numerical value.");
        }
    }

    // return int from input string
    public static int getIntInput(String promptUserForInput) {
        try {
            return getDoubleInput(promptUserForInput).intValue();
        }catch (Exception e){
            return getIntInput("Please enter a numerical value.");
        }
    }
}