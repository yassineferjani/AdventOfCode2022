package org.service;

import org.model.Coordinate;
import org.model.Movement;
import org.model.Rope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Service {

    public static List<List<Rope>> executeInstructions(List<Movement> list){
        List<List<Rope>> lists = new ArrayList<>();
        Rope rope = new Rope(Coordinate.builder().x(0).y(0).build(), Coordinate.builder().x(0).y(0).build());
        for (Movement movement : list){
            lists.add(rope.execute(movement));
        }
        return lists;
    }

    public static Set<Coordinate> tailPosition(List<List<Rope>> lists){
        Set<Coordinate> set = new HashSet<>();
        for (List<Rope> list : lists) {
            for (Rope rope : list) {
                set.add(rope.getTail());
            }
        }
        return set;
    }

    public static int countTailPosition(Set<Coordinate> set){
        return set.size();
    }


}
