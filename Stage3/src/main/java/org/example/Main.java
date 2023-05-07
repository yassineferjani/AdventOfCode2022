package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> list = Read.readFile("D:\\AdventOfCode\\Stage3\\src\\main\\resources\\input3.txt");
        System.out.println(Service.calculateScore(list));
        List<List<String>> groups = Service.extractGroups(list);
        System.out.println(Service.calculateGroupBadgesScore(groups));

    }
}