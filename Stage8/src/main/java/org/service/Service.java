package org.service;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
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


}

