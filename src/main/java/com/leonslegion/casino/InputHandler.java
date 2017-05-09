package com.leonslegion.casino;

import java.util.Scanner;
/**
 * Created by danielprahl on 5/9/17.
 */
public class InputHandler {
    private Scanner scanner;

    public InputHandler(){
        scanner = new Scanner(System.in);
    }

    // return String input
    public String getStringInput(String promptUserForInput) {
        System.out.println(promptUserForInput);
        String userInput = scanner.next();
        return userInput;
    }

    // return double input
    public double getDoubleInput(String promptUserForInput) {
        System.out.println(promptUserForInput);
        double userInput = scanner.nextDouble();
        return userInput;
    }

    // return int input
    public int getIntInput(String promptUserForInput) {
        System.out.println(promptUserForInput);
        int userInput = scanner.nextInt();
        return userInput;
    }
}