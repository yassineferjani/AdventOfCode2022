package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> list = Read.readFile("D:\\AdventOfCode\\Stage2\\src\\main\\resources\\input2.txt");
        System.out.println(Service.calculateTotalScore(list));
        System.out.println(Service.calculateTopSecretStrategyTotalSore(list));
    }
}