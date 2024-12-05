package com.company;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Kostenko on 05/12/2024
 */
@NoArgsConstructor
public class Day05 {
    String filePath;
    List<String> input = new ArrayList<>();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        initiateDataStructures();
        System.out.println("Finish initialization.");
    }

    private void initiateDataStructures() {
        System.out.println("Initiating data structures...");
        try {
            input.addAll(Files.readAllLines(Paths.get(filePath)));
            System.out.println("Finish reading the file. Size: " + input.size());
        } catch (IOException e) {
            System.err.printf("An error occurred while reading the file: %s%n%s%n", e.getMessage(), e);
        }
    }

    public int calculatePart1() {
        return 0;
    }

    public int calculatePart2() {
        return 0;
    }
}
