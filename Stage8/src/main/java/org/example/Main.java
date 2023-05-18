package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode2022\\Stage8\\src\\main\\resources\\input8.txt");
        int[][] forest = Service.convertTo2DArray(list);
        System.out.println(Service.countVisibleTrees(forest));
       // System.out.println(Service.highestScenicScore(forest));
    }
}