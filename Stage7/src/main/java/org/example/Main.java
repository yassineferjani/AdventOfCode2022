package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode\\Stage7\\src\\main\\resources\\input7.txt");
        System.out.println(Service.totalSize(list));
    }
}