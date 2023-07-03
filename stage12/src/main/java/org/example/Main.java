package org.example;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode2022\\stage12\\src\\main\\resources\\input12.txt");
        System.out.println(list);
    }
}