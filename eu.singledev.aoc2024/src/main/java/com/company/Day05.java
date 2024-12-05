package com.company;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mike Kostenko on 05/12/2024
 */
@NoArgsConstructor
public class Day05 {
    String filePath;
    List<String> input = new ArrayList<>();
    Map<Integer, List<Integer>> rules = new HashMap<>();
    List<List<Integer>> sequence = new ArrayList<>();
    List<List<Integer>> incorrect = new ArrayList<>();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        initiateDataStructures();
        System.out.println("Finish initialization.");
    }

    private void initiateDataStructures() {
        System.out.println("Initiating data structures...");
        try {
            input.addAll(Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.printf("An error occurred while reading the file: %s%n%s%n", e.getMessage(), e);
        }
        for (String line: input) {
            boolean isRule = line.contains("|");
            if (isRule) {
                String[] parts = line.split("\\|");
                int ruleNumber = Integer.parseInt(parts[0]);
                List<Integer> rule = rules.getOrDefault(ruleNumber, new ArrayList<>());
                rule.add(Integer.parseInt(parts[1]));
                rules.put(ruleNumber, rule);
            } else if (line.contains(",")) {
                sequence.add(Arrays.stream(line.split(","))
                        // Convert each substring to an integer
                        .map(Integer::parseInt)
                        // Collect the result into a List
                        .collect(Collectors.toList()));
            }
        }
    }

    public int calculatePart1() {
        int res = 0;
        main: for (List<Integer> seq: sequence) {
            for (int i = 0; i < seq.size(); i++) {
                int current = seq.get(i);
                for (int j = i + 1; j < seq.size(); j++) {
                    if (rules.containsKey(seq.get(j)) && rules.get(seq.get(j)).contains(current)) {
                        incorrect.add(seq);
                        continue main;
                    }
                }
            }
            int index = seq.size() / 2;
            res += seq.get(index);
        }
        return res;
    }

    public int calculatePart2() {
        int res = 0;
        for (List<Integer> seq: incorrect) {
            main: for (int i = 0; i < seq.size(); i++) {
                int current = seq.get(i);
                for (int j = i + 1; j < seq.size(); j++) {
                    Integer innerNum = seq.get(j);
                    if (rules.containsKey(innerNum) && rules.get(innerNum).contains(current)) {
                        int indexCurrent = seq.indexOf(current);
                        int indexInner = seq.indexOf(innerNum);
                        seq.set(indexCurrent, innerNum);
                        seq.set(indexInner, current);
                        i--;
                        continue main;
                    }
                }
            }
            int index = seq.size() / 2;
            res += seq.get(index);
        }
        return res;
    }
}
