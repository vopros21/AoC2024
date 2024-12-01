package com.company;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mike Kostenko on 01/12/2024
 */
@Slf4j
@NoArgsConstructor
public class Day01 {
    String filePath;
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        fillUpLeftAndRight();
    }

    private void fillUpLeftAndRight() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split("\\s+");
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));
            }
            left.sort(Integer::compareTo);
            right.sort(Integer::compareTo);
        } catch (IOException e) {
            log.error("An error occurred while reading the file: {}", e.getMessage(), e);
        }
    }

    public int calculatePart1() {
        int result = 0;
        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }
        return result;
    }

    public long calculatePart2() {
        long result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer n : right) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (Integer n : left) {
            result += (long) n * map.getOrDefault(n, 0);
        }
        return result;
    }
}
