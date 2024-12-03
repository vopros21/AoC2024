package com.company;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            Day03 day03 = new Day03();
            day03.setFilePath(filePath);
            System.out.println(day03.calculatePart1()); // 188116424
            System.out.println(day03.calculatePart2()); // 104245808
        }
    }
}