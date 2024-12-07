package com.company;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            Day07 day07 = new Day07();
            day07.setFilePath(filePath);
            System.out.println(day07.calculatePart1());
            System.out.println(day07.calculatePart2());
        } else {
            System.out.println("No file path provided.");
        }
    }
}