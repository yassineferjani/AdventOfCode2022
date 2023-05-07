package org.example;

import org.service.Service;
import org.stage.Read;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> list = Read.readFile("D:\\AdventOfCode\\app\\src\\main\\resources\\input3.txt");
        System.out.println(Service.calculateScore(list));
        List<List<String>> groups = Service.extractGroups(list);
        System.out.println(Service.calculateGroupBadgesScore(groups));

    }
}