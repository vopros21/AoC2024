package com.company;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            Day01 day01 = new Day01();
            day01.setFilePath(filePath);
            System.out.println(day01.calculatePart1());
            System.out.println(day01.calculatePart2());
        }
    }
}