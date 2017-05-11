package com.leonslegion.casino;

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
        System.out.println(promptUserForInput);
        String userInput = scanner.nextLine();
        return userInput;
    }

    // return double from input string
    public static Double getDoubleInput(String promptUserForInput) {
        return Double.parseDouble(getStringInput(promptUserForInput));
    }

    // return long from input string
    public static Long getLongInput(String promptUserForInput) {
        return Long.parseLong(getStringInput(promptUserForInput));
    }

    // return int from input string
    public static int getIntInput(String promptUserForInput) {
        return getDoubleInput(promptUserForInput).intValue();
    }
}