package com.company;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            Day05 day05 = new Day05();
            day05.setFilePath(filePath);
            System.out.println(day05.calculatePart1()); // 188116424
            System.out.println(day05.calculatePart2()); // 104245808
        } else {
            System.out.println("No file path provided.");
        }
    }
}