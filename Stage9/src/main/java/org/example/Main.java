package org.example;

import org.Adapter.Adapter;
import org.model.Coordinate;
import org.model.Rope;
import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        List<String> list =Read.readFile("D:\\AdventOfCode2022\\Stage9\\src\\main\\resources\\input9.txt");
        List<Set<Coordinate>> positions= Service.executeInstructions(Adapter.mapToListOfMovement(list));
        System.out.println(positions.get(0).size());
        System.out.println(positions.get(1).size());
    }
}