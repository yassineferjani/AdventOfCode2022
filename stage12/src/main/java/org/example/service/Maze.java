package org.example.service;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Maze {
   private static final int[][] DIRECTIONS = new int[][]{
        {-1, 0}, // Up
        {1, 0}, // Down
        {0, -1}, // Left
        {0, 1}, // Right
    };
    private final int height;
    private final int width;

    private final int[][] heights;
    private final int[] start = new int[2];
    private final int[] end = new int[2];

    public Maze(List<String> list) {
        height = list.size();
        width = list.get(0).length();
        heights = new int[height][width];
        for (var row = 0; row < height; row++) {
            for (var col = 0; col < width; col++) {
                switch (list.get(row).charAt(col)) {
                    case 'S' -> {
                        start[0] = row;
                        start[1] = col;
                        heights[row][col] = 0;
                    }
                    case 'E' -> {
                        end[0] = row;
                        end[1] = col;
                        heights[row][col] = 25;
                    }
                    default -> heights[row][col] = list.get(row).charAt(col) - 'a';
                }
            }
        }
    }

    public int part1Path() {
        int[][] gScore = new int[height][width];
        int[][] fScore = new int[height][width];
        initializeScores(gScore, fScore);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(p -> fScore[p[0]][p[1]]));
        enqueueOrigin(queue,gScore, fScore);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == start[0] && current[1] == start[1]) {
                return gScore[current[0]][current[1]];
            }
            exploreNeighbors(current, gScore, fScore, queue);
        }
        throw new IllegalStateException("No path found");
    }

    private void initializeScores(int[][] gScore, int[][] fScore) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                gScore[row][col] = Integer.MAX_VALUE;
                fScore[row][col] = Integer.MAX_VALUE;
            }
        }
    }

    private int calculateManhattanDistance(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2);
    }

    private void enqueueOrigin(PriorityQueue<int[]> queue,int[][] gScore, int[][] fScore) {
        gScore[end[0]][end[1]] = 0;
        fScore[end[0]][end[1]] = calculateManhattanDistance(end[0], end[1], start[0], start[1]);
        queue.add(end);
    }

    private void exploreNeighbors(int[] current, int[][] gScore, int[][] fScore, PriorityQueue<int[]> queue) {
        int score = gScore[current[0]][current[1]] + 1;
        for (int[] direction : DIRECTIONS) {
            int row = current[0] + direction[0];
            int col = current[1] + direction[1];
            if (isValidPosition(row, col) && isReachableNeighbor(current, row, col, score, gScore)) {
                gScore[row][col] = score;
                fScore[row][col] = score + calculateManhattanDistance(row, col, start[0], start[1]);
                queue.add(new int[]{row, col});
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    private boolean isReachableNeighbor(int[] current, int row, int col, int score, int[][] gScore) {
        return heights[current[0]][current[1]] - 1 <= heights[row][col] && score < gScore[row][col];
    }


    public int part2Path() {
        int[][] distance = new int[height][width];
        initializeDistances(distance);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(p -> distance[p[0]][p[1]]));
        enqueueOrigin(queue,distance);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (heights[current[0]][current[1]] == 0) {
                return distance[current[0]][current[1]];
            }
            exploreNeighbors(current, distance, queue);
        }
        throw new IllegalStateException("No path found");
    }

    private void initializeDistances(int[][] distance) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                distance[row][col] = Integer.MAX_VALUE;
            }
        }
    }

    private void enqueueOrigin(PriorityQueue<int[]> queue,int[][] distance) {
        distance[end[0]][end[1]] = 0;
        queue.add(end);
    }

    private void exploreNeighbors(int[] current, int[][] distance, PriorityQueue<int[]> queue) {
        int score = distance[current[0]][current[1]] + 1;
        for (int[] direction : DIRECTIONS) {
            int row = current[0] + direction[0];
            int col = current[1] + direction[1];
            if (isValidPosition(row, col) && isReachableNeighbor(current, row, col, score, distance)) {
                distance[row][col] = score;
                queue.add(new int[]{row, col});
            }
        }
    }

}

