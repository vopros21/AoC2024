package com.company;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Kostenko on 04/12/2024
 */
@Slf4j
@NoArgsConstructor
public class Day04 {
    String filePath;
    List<String> input = new ArrayList<>();

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        initiateDataStructures();
        System.out.println("Finish initialization.");
    }

    private void initiateDataStructures() {
        System.out.println("Initiating data structures...");
        try {
            input.addAll(Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            log.error("An error occurred while reading the file: {}", e.getMessage(), e);
        }
    }

    public int calculatePart1() {
        int result = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'X') {
                    result += searchXMAS(i, j);
                }
            }
        }
        return result;
    }

    private int searchXMAS(int i, int j) {
        int result = 0;
        for (Direction direction : Direction.values()) {
            int y = i;
            int x = j;
            switch (direction) {
                case E:
                    if (++x < input.get(y).length() && input.get(y).charAt(x) != 'M') {
                        continue;
                    } else if (++x < input.get(y).length() && input.get(y).charAt(x) != 'A') {
                        continue;
                    } else if (++x < input.get(y).length() && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
                    break;
                case W:
                    if (--x > 0 && input.get(y).charAt(x) != 'M') {
                        continue;
                    } else if (--x > 0 && input.get(y).charAt(x) != 'A') {
                        continue;
                    } else if (--x >= 0 && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
                    break;
                case N:
                    if (--y > 0 && input.get(y).charAt(x) != 'M') {
                        continue;
                    } else if (--y > 0 && input.get(y).charAt(x) != 'A') {
                        continue;
                    } else if (--y >= 0 && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
                    break;
                case S:
                    if (++y < input.size() && input.get(y).charAt(x) != 'M') {
                        continue;
                    } else if (++y < input.size() && input.get(y).charAt(x) != 'A') {
                        continue;
                    } else if (++y < input.size() && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
                    break;
                case NE:
                    if (--y > 0 && ++x < input.get(y).length() && input.get(y).charAt(x) != 'M') {
                        continue;
                    } else if (--y > 0 && ++x < input.get(y).length() && input.get(y).charAt(x) != 'A') {
                        continue;
                    } else if (--y >= 0 && ++x < input.get(y).length() && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
                    break;
                case NW:
                    if (--y > 0 && --x > 0 && input.get(y).charAt(x) != 'M') {
                        continue;
                    } else if (--y > 0 && --x > 0 && input.get(y).charAt(x) != 'A') {
                        continue;
                    } else if (--y >= 0 && --x >= 0 && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
                    break;
                case SE:
                    if (++y < input.size() && ++x < input.get(y).length() && input.get(y).charAt(x) != 'M') {
                        continue;
                    } else if (++y < input.size() && ++x < input.get(y).length() && input.get(y).charAt(x) != 'A') {
                        continue;
                    } else if (++y < input.size() && ++x < input.get(y).length() && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
                    break;
                case SW:
                    if (++y < input.size() && --x > 0 && input.get(y).charAt(x) != 'M') {
                        break;
                    } else if (++y < input.size() && --x > 0 && input.get(y).charAt(x) != 'A') {
                        break;
                    } else if (++y < input.size() && --x >= 0 && input.get(y).charAt(x) == 'S') {
                        result++;
                    }
            }
        }
        return result;
    }

    public int calculatePart2() {
        int result = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'A') {
                    result += searchMAS(i, j);
                }
            }
        }
        return result;
    }

    private int searchMAS(int i, int j) {
        boolean l2r = false;
        boolean r2l = false;
        if (i > 0 && j > 0 && i < input.size() - 1 && j < input.get(i).length() - 1) {
            if ((input.get(i - 1).charAt(j - 1) == 'M' && input.get(i + 1).charAt(j + 1) == 'S') || (input.get(i - 1).charAt(j - 1) == 'S' && input.get(i + 1).charAt(j + 1) == 'M')) {
                l2r = true;
            }
            if ((input.get(i - 1).charAt(j + 1) == 'M' && input.get(i + 1).charAt(j - 1) == 'S') || (input.get(i - 1).charAt(j + 1) == 'S' && input.get(i + 1).charAt(j - 1) == 'M')) {
                r2l = true;
            }
        }
        return l2r && r2l ? 1 : 0;
    }

    private enum Direction {
        N, NE, E, SE, S, SW, W, NW
    }
}
