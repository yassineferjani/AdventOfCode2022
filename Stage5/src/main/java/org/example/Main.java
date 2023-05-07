package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode\\Stage5\\src\\main\\resources\\input5.txt");
        List<Deque<String>> crates = new ArrayList<>();
        crates.add(new LinkedList<>(List.of("N","S","D","C","V","Q","T")));
        crates.add(new LinkedList<>(List.of("M","F","V")));
        crates.add(new LinkedList<>(List.of("F","Q","W","D","P","N","H","M")));
        crates.add(new LinkedList<>(List.of("D","Q","R","T","F")));
        crates.add(new LinkedList<>(List.of("R","F","M","N","Q","H","V","B")));
        crates.add(new LinkedList<>(List.of("C","F","G","N","P","W","Q")));
        crates.add(new LinkedList<>(List.of("W","F","R","L","C","T")));
        crates.add(new LinkedList<>(List.of("T","Z","N","S")));
        crates.add(new LinkedList<>(List.of("M","S","D","J","R","Q","H","N")));

        Service.rearrangement(crates,list);
        Service.printLastStacks(crates);

    }
}