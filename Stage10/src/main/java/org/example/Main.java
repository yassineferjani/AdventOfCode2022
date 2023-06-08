package org.example;

import java.io.FileNotFoundException;
import java.util.List;

import static org.example.service.Service.calculateSignalStrengthSum;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode2022\\Stage10\\src\\main\\resources\\input9.txt");

        System.out.println(calculateSignalStrengthSum(list));
    }

}