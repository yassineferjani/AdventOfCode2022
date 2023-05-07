package org.service;

import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    public static List<List<Integer>> extractInstructionFromInput(List<String> list) {
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
            for (int i =0; i<numberOfStacks;i++){
                crates.get(to).offerLast(crates.get(from).pollLast());
            }
        }
    }

    public static void printLastStacks(List<Deque<String>> crates){
        crates.stream()
                .map(Deque::getLast)
                .forEach(System.out::print);
    }
}
