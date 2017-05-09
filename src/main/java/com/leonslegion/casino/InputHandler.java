package com.leonslegion.casino;

import java.util.Scanner;
/**
 * Created by danielprahl on 5/9/17.
 */
public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    // return String input
    public String getStringInput(String promptUserForInput) {
        System.out.println(promptUserForInput);
        String userInput = scanner.next();
        return userInput;
    }

    // return double input
    public Double getDoubleInput(String promptUserForInput) {
        return Double.parseDouble(getStringInput(promptUserForInput));
    }

    // return int input
    public int getIntInput(String promptUserForInput) {
        return getDoubleInput(promptUserForInput).intValue();
    }
}