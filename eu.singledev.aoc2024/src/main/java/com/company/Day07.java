package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Mike Kostenko on 07/12/2024
 */
public class Day07 {
    private String filePath;
    List<String> input = new ArrayList<>();
    Map<Integer, List<Integer>> toCheck = new HashMap<>();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        initiateDataStructures(filePath);
    }

    private void initiateDataStructures(String filePath) {
        System.out.println("Initiating data structures...");
        try {
            input.addAll(Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.printf("An error occurred while reading the file: %s%n%s%n", e.getMessage(), e);
        }
        for (String line: input) {
            int test = Integer.parseInt(line.split(":")[0]);
            List<Integer> operands = Arrays.stream(line.split(":")[1].split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            toCheck.put(test, operands);

        }
        for (Map.Entry entry : toCheck.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public int calculatePart1() {
        return 0;
    }

    public int calculatePart2() {
        return 0;
    }
}
