package org.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private static List<List<Integer>> extractInstructionFromInput(List<String> list) {
        return list.stream()
                .map(move -> {
                    String[] parts = move.split(" ");
                    int piece = Integer.parseInt(parts[1]);
                    int from = Integer.parseInt(parts[3]);
                    int to = Integer.parseInt(parts[5]);
                    return List.of(piece, from, to);
                })
                .collect(Collectors.toList());
    }
    public static void rearrangement(List<Deque<String>> crates, List<String> instructions){
        List<List<Integer>> movements = extractInstructionFromInput(instructions);
        for (List<Integer> move: movements) {
            int numberOfStacks = move.get(0);
            int from = move.get(1)-1;
            int to = move.get(2)-1;
            moveCrates0(crates,from,to,numberOfStacks);
        }
    }

    public static void printLastStacks(List<Deque<String>> crates){
        crates.stream()
                .map(Deque::getLast)
                .forEach(System.out::print);
    }
    private static void moveCrates0(List<Deque<String>> crates, int from, int to, int numCrates) {
        for (int i = 0; i < numCrates; i++) {
            crates.get(to).offerLast(crates.get(from).pollLast());
        }
    }

    private static void moveCrates(List<Deque<String>> crates, int from, int to, int numCrates) {
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < numCrates; i++) {
            String element = crates.get(from).pollLast();
            deque.offerFirst(element);
        }
        crates.get(to).addAll(deque);
    }

    public static void rearrangement9001(List<Deque<String>> crates, List<String> instructions) {
        List<List<Integer>> movements = extractInstructionFromInput(instructions);
        for (List<Integer> move : movements) {
            int numberOfCrates = move.get(0);
            int from = move.get(1) - 1;
            int to = move.get(2) - 1;
            moveCrates(crates, from, to, numberOfCrates);
        }
    }
}
