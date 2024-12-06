package com.company;

import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Kostenko on 06/12/2024
 */
@NoArgsConstructor
public class Day06 {
    String filePath;
    char[][] grid;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        initiateDataStructures();
        System.out.println("Finish initialization.");
    }

    private void initiateDataStructures() {
        System.out.println("Initiating data structures...");
        List<String> input = new ArrayList<>();
        try {
            input.addAll(Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.printf("An error occurred while reading the file: %s%n%s%n", e.getMessage(), e);
        }
        grid = new char[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }
    }

    public int calculatePart1() {
        Point current = new Point(0, 0);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    current.setLocation(j, i);
                    break;
                }
            }
        }
        int count = 0;
        Direction direction = Direction.UP;
        while (current.x >= 0 && current.x <= grid[0].length - 1 && current.y >= 0 && current.y <= grid.length - 1) {
            if (direction == Direction.UP) {
                if (current.y == 0) {
                    break;
                } else if (grid[current.y - 1][current.x] == '#') {
                    direction = Direction.RIGHT;
                } else {
                    grid[current.y][current.x] = 'X';
                    current.y--;
//                    count++;
                }
            } else if (direction == Direction.DOWN) {
                if (current.y == grid.length - 1) {
                    break;
                } else if (grid[current.y + 1][current.x] == '#') {
                    direction = Direction.LEFT;
                } else {
                    grid[current.y][current.x] = 'X';
                    current.y++;
//                    count++;
                }
            } else if (direction == Direction.LEFT) {
                if (current.x == 0) {
                    break;
                } else if (grid[current.y][current.x - 1] == '#') {
                    direction = Direction.UP;
                } else {
                    grid[current.y][current.x] = 'X';
                    current.x--;
//                    count++;
                }
            } else {
                if (current.x == grid[current.y].length - 1) {
                    break;
                } else if (grid[current.y][current.x + 1] == '#') {
                    direction = Direction.DOWN;
                } else {
                    grid[current.y][current.x] = 'X';
                    current.x++;
//                    count++;
                }
            }
        }
        for (char[] row : grid) {
            for (char c : row) {
                if (c == 'X') {
                    count++;
                }
            }
        }
        return count + 1;
    }

    public int calculatePart2() {
        return 0;
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
