package com.company;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            Day02 day02 = new Day02();
            day02.setFilePath(filePath);
            System.out.println(day02.calculatePart1());
            System.out.println(day02.calculatePart2());
        }
    }
}