package org.example;

import org.example.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode2022\\Stage13\\src\\main\\resources\\input13.txt");
        list.forEach(System.out::println);
        Service.split(list);
    }
}