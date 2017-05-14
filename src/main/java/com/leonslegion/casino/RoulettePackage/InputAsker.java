package com.leonslegion.casino.RoulettePackage;

import java.util.*;
import java.io.InputStream;
import java.io.PrintStream;

public class InputAsker {

    private Scanner scanner;
    private PrintStream out;

    public InputAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public String askForInput(String message) {
        out.println(message);
        return scanner.nextLine();
    }

}
