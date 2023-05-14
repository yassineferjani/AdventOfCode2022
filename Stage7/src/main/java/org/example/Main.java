package org.example;

import org.service.Service;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = Read.readFile("D:\\AdventOfCode\\Stage7\\src\\main\\resources\\input7.txt");
        list= List.of("$ cd /", "$ ls", "dir a", "14848514 b.txt", "8504156 c.dat",
                "dir d", "$ cd a", "$ ls", "dir e", "29116 f", "2557 g", "62596 h.lst", "$ cd e", "$ ls", "584 i",
                "$ cd ..", "$ cd ..", "$ cd ..", "$ cd d", "$ ls", "4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k");
        Service service = new Service();
        System.out.println(service.totalSize(list));
    }
}