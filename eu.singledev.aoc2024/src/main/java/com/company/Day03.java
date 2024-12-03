package com.company;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mike Kostenko on 02/12/2024
 */
@Slf4j
@NoArgsConstructor
public class Day03 {
    String filePath;
    List<String> input = new ArrayList<>();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        initiateDataStructures();
        log.info("Finish initialization.");
    }

    private void initiateDataStructures() {
        log.info("Initiating data structures...");
        try {
            input.addAll(Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            log.error("An error occurred while reading the file: {}", e.getMessage(), e);
        }
    }

    public long calculatePart1() {
        log.info("Calculating Part 1...");
        long result = 0;
        for (String line: input) {
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int a = Integer.parseInt(matcher.group(1));
                int b = Integer.parseInt(matcher.group(2));
                result += (long) a * b;
            }
        }
        return result;
    }

    public long calculatePart2() {
        log.info("Calculating Part 2...");
        long result = 0;
        String line = String.join("", input);
        String[] parts = line.split("do\\(\\)");
        for (String part: parts) {
            int index = part.indexOf("don't()");
            String searchScope = part.substring(0, index == -1 ? part.length() : index);
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            Matcher matcher = pattern.matcher(searchScope);
            while (matcher.find()) {
                int a = Integer.parseInt(matcher.group(1));
                int b = Integer.parseInt(matcher.group(2));
                result += (long) a * b;
            }
        }

        return result;
    }
}
