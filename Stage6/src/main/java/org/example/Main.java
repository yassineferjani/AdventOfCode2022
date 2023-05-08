package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode\\Stage6\\src\\main\\resources\\input6.txt");
        System.out.println(list);
        System.out.println(Service.firstMarker(list.get(0),4));
        System.out.println(Service.firstMarker(list.get(0),14));
    }
}