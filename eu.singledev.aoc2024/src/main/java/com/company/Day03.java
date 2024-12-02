package com.company;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mike Kostenko on 02/12/2024
 */
@Slf4j
@NoArgsConstructor
public class Day03 {
    String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        initiateDataStructures();
        log.info("Finish initialization.");
    }

    private void initiateDataStructures() {
        log.info("Initiating data structures...");
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

        } catch (IOException e) {
            log.error("An error occurred while reading the file: {}", e.getMessage(), e);
        }
    }

    public int calculatePart1() {
        log.info("Calculating Part 1...");
        return 0;
    }

    public int calculatePart2() {
        log.info("Calculating Part 2...");
        return 0;
    }
}
