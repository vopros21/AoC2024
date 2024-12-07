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
                }
            } else if (direction == Direction.DOWN) {
                if (current.y == grid.length - 1) {
                    break;
                } else if (grid[current.y + 1][current.x] == '#') {
                    direction = Direction.LEFT;
                } else {
                    grid[current.y][current.x] = 'X';
                    current.y++;
                }
            } else if (direction == Direction.LEFT) {
                if (current.x == 0) {
                    break;
                } else if (grid[current.y][current.x - 1] == '#') {
                    direction = Direction.UP;
                } else {
                    grid[current.y][current.x] = 'X';
                    current.x--;
                }
            } else {
                if (current.x == grid[current.y].length - 1) {
                    break;
                } else if (grid[current.y][current.x + 1] == '#') {
                    direction = Direction.DOWN;
                } else {
                    grid[current.y][current.x] = 'X';
                    current.x++;
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
        int count = 0;
        Point start = new Point(0, 0);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    start.setLocation(j, i);
                    break;
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i != start.y || j != start.x) {
                    char c = grid[i][j];
                    if (c == '.') {
                        grid[i][j] = 'O';
                        if (isLooping(grid)) {
                            count++;
                        }
                        grid[i][j] = c;
                    }
                }
            }
        }
        return count;
    }

    public boolean isLooping ( char[][] grid){
        Point current = new Point(0, 0);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    current.setLocation(j, i);
                    break;
                }
            }
        }
        Direction direction = Direction.UP;
        Position position = null;
        while (current.x >= 0 && current.x <= grid[0].length - 1 && current.y >= 0 && current.y <= grid.length - 1) {
            if (direction == Direction.UP) {
                if (current.y == 0) {
                    break;
                } else if (grid[current.y - 1][current.x] == 'O') {
                    if (position == null) {
                        position = new Position();
                        position.point = new Point(current.x, current.y);
                        position.direction = Direction.UP;
                        direction = Direction.RIGHT;
                    } else if (position.point.equals(new Point(current.x, current.y)) && position.direction == Direction.UP) {
                        return true;
                    }
                } else if (grid[current.y - 1][current.x] == '#') {
                    direction = Direction.RIGHT;
                } else {
                    current.y--;
                }
            } else if (direction == Direction.DOWN) {
                if (current.y == grid.length - 1) {
                    break;
                } else if (grid[current.y + 1][current.x] == 'O') {
                    if (position == null) {
                        position = new Position();
                        position.point = new Point(current.x, current.y);
                        position.direction = Direction.DOWN;
                        direction = Direction.LEFT;
                    } else if (position.point.equals(new Point(current.x, current.y)) && position.direction == Direction.DOWN) {
                        return true;
                    }
                } else if (grid[current.y + 1][current.x] == '#') {
                    direction = Direction.LEFT;
                } else {
                    current.y++;
                }
            } else if (direction == Direction.LEFT) {
                if (current.x == 0) {
                    break;
                } else if (grid[current.y][current.x - 1] == '#' || grid[current.y][current.x - 1] == 'O') {
                    if (position == null) {
                        position = new Position();
                        position.point = new Point(current.x, current.y);
                        position.direction = Direction.LEFT;
                    } else {
                        return true;
                    }
                    direction = Direction.UP;
                } else {
                    current.x--;
                }
            } else {
                if (current.x == grid[current.y].length - 1) {
                    break;
                } else if (grid[current.y][current.x + 1] == '#' || grid[current.y][current.x + 1] == 'O') {
                    if (position == null) {
                        position = new Position();
                        position.point = new Point(current.x, current.y);
                        position.direction = Direction.RIGHT;
                    } else {
                        return true;
                    }
                    direction = Direction.DOWN;
                } else {
                    current.x++;
                }
            }
        }
        return false;
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private class Position {
        Point point;
        Direction direction;
    }
}
