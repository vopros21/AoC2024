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
public class Day02 {
    String filePath;
    List<String> reports = new ArrayList<>();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        fillUpLeftAndRight();
    }

    private void fillUpLeftAndRight() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            reports.addAll(lines);
        } catch (IOException e) {
            log.error("An error occurred while reading the file: {}", e.getMessage(), e);
        }
    }

    public int calculatePart1() {
        int result = 0;
        for (String report : reports) {
            boolean br;
            List<Integer> nums = Arrays.stream(report.split("\\s+")).mapToInt(Integer::parseInt).boxed().toList();
            br = checking(nums);
            result = br ? result : result + 1;
        }
        return result;
    }

    public int calculatePart2() {
        int result = 0;
        for (String report : reports) {
            boolean br;
            List<Integer> nums = Arrays.stream(report.split("\\s+")).mapToInt(Integer::parseInt).boxed().toList();
            br = checking(nums);
            if (br && couldBeFixed(nums)) {
                result = result + 1;
            }
            result = br ? result : result + 1;
        }
        return result;
    }

    private static boolean checking(List<Integer> nums) {
        if (nums.get(0) < nums.get(1)) {
            for (int i = 1; i < nums.size(); i++) {
                if (nums.get(i-1) >= nums.get(i) || nums.get(i) - nums.get(i - 1) > 3) {
                    return true;
                }
            }
        } else {
            for (int i = 1; i < nums.size(); i++) {
                if (nums.get(i-1) <= nums.get(i) || nums.get(i - 1) - nums.get(i) > 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean couldBeFixed(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> tmp = new ArrayList<>(nums);
            tmp.remove(i);
            if (!checking(tmp)) {
                return true;
            }
        }
        return false;
    }

}
