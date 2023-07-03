package org.example;

import org.example.service.Service;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode2022\\stage12\\src\\main\\resources\\input12.txt");
        Arrays.stream(Service.convertToMultidimensionalArray(list)).forEach(System.out::println);
    }
}