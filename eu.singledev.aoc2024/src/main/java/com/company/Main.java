package com.company;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            Day06 day06 = new Day06();
            day06.setFilePath(filePath);
//            System.out.println(day06.calculatePart1()); // 188116424
            System.out.println(day06.calculatePart2()); // 104245808
        } else {
            System.out.println("No file path provided.");
        }
    }
}