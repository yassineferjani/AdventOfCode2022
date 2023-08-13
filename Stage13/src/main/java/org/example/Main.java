package org.example;

import org.example.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = Read.readFile("D:\\AdventOfCode2022\\Stage13\\src\\main\\resources\\input13.txt");
        //list.forEach(System.out::println);
        //Service.split(list);
        //System.out.println(list.get(1));
        //List<String> input = List.of("[1,[2,[3,4],[5,6,0]],8,9]");
        //System.out.println(input.substring(12,19));
        //Service.extractSubList(input).forEach(System.out::println);
        System.out.println(Service.convertInputToMap(input));
    }
}
