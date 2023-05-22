package org.example;

import org.Adapter.Adapter;
import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> list =Read.readFile("D:\\AdventOfCode2022\\Stage9\\src\\main\\resources\\input9.txt");
        System.out.println(Service.executeInstructions(Adapter.mapToListOfMovement(list)));

    }
}