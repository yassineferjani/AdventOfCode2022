package org.service;

import java.util.List;
import java.util.stream.IntStream;

public class Service {

    public static int[][] convertTo2DArray(List<String> list) {
        return list.stream().map(s -> s
                        .chars()
                        .map(Character::getNumericValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    public static long countVisibleTrees(int[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        return IntStream.range(0, rowCount)
                .flatMap(row -> IntStream.range(0, colCount)
                        .filter(col -> isTreeVisibleFromAllDirections(grid, row, col, rowCount, colCount)))
                .count();
    }

    private static boolean isTreeVisibleFromAllDirections(int[][] grid, int row, int col, int rowCount, int colCount) {
        int currentHeight = grid[row][col];
        return isTreeVisibleFromLeft(grid, row, col, currentHeight) ||
                isTreeVisibleFromRight(grid, row, col, colCount, currentHeight) ||
                isTreeVisibleFromTop(grid, row, col, currentHeight) ||
                isTreeVisibleFromBottom(grid, row, col, rowCount, currentHeight);
    }

    private static boolean isTreeVisibleFromLeft(int[][] grid, int row, int col, int currentHeight) {
        return IntStream.range(0, col)
                .allMatch(i -> grid[row][i] < currentHeight);
    }

    private static boolean isTreeVisibleFromRight(int[][] grid, int row, int col, int colCount, int currentHeight) {
        return IntStream.range(col+1, colCount)
                .allMatch(i -> grid[row][i] < currentHeight);
    }

    private static boolean isTreeVisibleFromTop(int[][] grid, int row, int col, int currentHeight) {
        return IntStream.range(0, row)
                .allMatch(i -> grid[i][col] < currentHeight);
    }

    private static boolean isTreeVisibleFromBottom(int[][] grid, int row, int col, int rowCount, int currentHeight) {
        return IntStream.range(row + 1, rowCount)
                .allMatch(i -> grid[i][col] < currentHeight);
    }
    public static int[][] getScenicGrid(int[][] grid) {
        int[][] scenicGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                scenicGrid[i][j] = getScenic(grid, j, i);
            }
        }
        return scenicGrid;
    }

    public static int getMaxScenicScore(int[][] grid) {
        int maxScenicScore = Integer.MIN_VALUE;
        for (int[] row : grid) {
            for (int score : row) {
                if (score > maxScenicScore) {
                    maxScenicScore = score;
                }
            }
        }
        return maxScenicScore;
    }

    private static int getScenic(int[][] grid, int x, int y) {
        int eastVal = numSeenEast(grid, x - 1, y, grid[y][x]);
        int westVal = numSeenWest(grid, x + 1, y, grid[y][x]);
        int northVal = numSeenNorth(grid, x, y - 1, grid[y][x]);
        int southVal = numSeenSouth(grid, x, y + 1, grid[y][x]);

        return eastVal * westVal * northVal * southVal;
    }

    public static int numSeenEast(int[][] grid, int x, int y, int val) {
        if (x > -1) {
            if (val > grid[y][x]) {
                return 1 + numSeenEast(grid, x - 1, y, val);
            }
            return 1;
        }
        return 0;
    }

    public static int numSeenWest(int[][] grid, int x, int y, int val) {
        if (x < grid[0].length) {
            if (val > grid[y][x]) {
                return 1 + numSeenWest(grid, x + 1, y, val);
            }
            return 1;
        }
        return 0;
    }

    public static int numSeenNorth(int[][] grid, int x, int y, int val) {
        if (y > -1) {
            if (val > grid[y][x]) {
                return 1 + numSeenNorth(grid, x, y - 1, val);
            }
            return 1;
        }
        return 0;
    }

    public static int numSeenSouth(int[][] grid, int x, int y, int val) {
        if (y < grid.length) {
            if (val > grid[y][x]) {
                return 1 + numSeenSouth(grid, x, y + 1, val);
            }
            return 1;
        }
        return 0;
    }

}

