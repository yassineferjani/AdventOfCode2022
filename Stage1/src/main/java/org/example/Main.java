package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode\\Stage1\\src\\main\\resources\\input.txt");
        List<Integer> listGroup = Service.calculateGroupsSum(list);

        System.out.println(Service.maxCalorie(listGroup));
        System.out.println(Service.sumThreeMaxCalorieGroup(listGroup));
    }
}