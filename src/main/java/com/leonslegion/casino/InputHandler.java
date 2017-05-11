package com.leonslegion.casino;

import java.util.Scanner;
/**
 * Created by danielprahl on 5/9/17.
 */
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    // return String input
    public static String getStringInput(String promptUserForInput) {
        System.out.println(promptUserForInput);
        String userInput = scanner.nextLine();
        return userInput;
    }

    // return double input
    public static Double getDoubleInput(String promptUserForInput) {
        return Double.parseDouble(getStringInput(promptUserForInput));
    }


    // return int input
    public static int getIntInput(String promptUserForInput) {
        return getDoubleInput(promptUserForInput).intValue();
    }
}