package org.example;

import org.example.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode2022\\Stage11\\src\\main\\resources\\input11.txt");
        Service.monkey(list).forEach(System.out::println);
    }
}