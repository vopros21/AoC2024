package com.company;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            Day04 day04 = new Day04();
            day04.setFilePath(filePath);
            System.out.println(day04.calculatePart1()); // 188116424
            System.out.println(day04.calculatePart2()); // 104245808
        }
    }
}