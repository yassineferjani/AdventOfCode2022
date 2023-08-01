package org.example;

import org.example.service.Maze;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode2022\\stage12\\src\\main\\resources\\input12.txt");
        Maze maze = new Maze(list);
        System.out.println(maze.part1Path());
        System.out.println(maze.part2Path());
    }



}