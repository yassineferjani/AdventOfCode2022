package org.example;

import org.service.Node;
import org.service.Tree;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



public class Main {
    private static Tree directories ;
    private static Node<Integer> currentNode;
    public static void main(String[] args) throws FileNotFoundException {
        parseInput(Read.readFile("D:\\AdventOfCode2022\\Stage7\\src\\main\\resources\\input7.txt"));
        List<Node> folders = directories.treeToList();
        AtomicInteger count = new AtomicInteger(0);
        folders.forEach(f->{
            if(f.getDirSize()<=100000)
                count.getAndAdd(f.getDirSize());
        });
        System.out.println("Size of all folders with at most 100k data: "+count.get());
        int free =  calculateToFreeSize( 70000000, 30000000, directories);
        System.out.println(smallestDirectory(free));
    }


    public static void parseInput(List<String> list) {
        for (String s:list ) {

            String[] line = s.split(" ");
            if (line.length == 2) {
                eval2long(line);
            } else if (line.length == 3) {
                eval3long(line);
            } else {
                System.out.println("No valid option for input line found!");
            }
        }
    }

    private static int calculateToFreeSize( int maxMemory, int necesseryMem, Tree directories){
        return necesseryMem - ( maxMemory - directories.getRoot().getDirSize());
    }
    private static int smallestDirectory(int toFree) {
        List<Node> folders = directories.treeToList();
        Collections.sort(folders);

        for (Node folder : folders) {
            if (folder.getDirSize() >= toFree) {
                return folder.getDirSize();
            }
        }
        throw new RuntimeException();
    }

    private static void eval3long(String[] s) {
        if(s[1].equals("cd") && s[2].equals("..")){
            currentNode = currentNode.getParent();
        }else if(s[1].equals("cd") && s[2].equals("/")) {
            directories = new org.service.Tree();
            currentNode = directories.getRoot();
        }else if(s[1].equals("cd") && !s[2].equals("..")) {
            Node<Integer> newNode = new Node<Integer>(currentNode,s[2]);
            currentNode.addSubDir(newNode);
            currentNode = newNode;
        }
    }
    private static void eval2long(String[] s) {
        if(s[1].equals("ls") || s[0].equals("dir")) {
        }
        else{
            try {
                int a = Integer.parseInt(s[0]);
                currentNode.addFile(a);
            }catch(NumberFormatException e) {
            }
        }
    }
}