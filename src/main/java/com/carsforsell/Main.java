package com.carsforsell;

import com.carsforsell.UI.UserInterface;

import java.io.IOException;
import java.nio.file.Path;

public class Main { //will be similar to the ledger
    public static void main(String[] args) {
        Path dataDir = args.length > 0 ? Path.of(args[0]) : Path.of("data");
        try {
            new UserInterface(dataDir).display();
        } catch (IOException ex) {
            System.err.println("Could not start dealership: " + ex.getMessage());
        }
    }
}
