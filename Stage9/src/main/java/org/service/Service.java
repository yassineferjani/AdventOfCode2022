package org.service;

import org.model.Coordinate;
import org.model.Movement;
import org.model.Rope;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Service {

    public static List<Rope> executeInstructions(List<Movement> list){
        Rope rope = new Rope(Coordinate.builder().x(0).y(0).build(), Coordinate.builder().x(0).y(0).build());
        return rope.execute(list);
    }

    public static Set<Coordinate> tailPosition(List<Rope> lists){
        return lists.stream()
                .map(Rope::getTail)
                .collect(Collectors.toSet());
    }

    public static List<Coordinate> tailPosition1(List<Rope> lists){
        return lists.stream()
                .skip(9)
                .filter(r -> lists.indexOf(r) % 9 == 0)
                .map(Rope::getTail)
                .collect(Collectors.toList());
    }

    public static int countTailPosition(Set<Coordinate> set){
        return set.size();
    }
    public static int countTailPosition(List<Coordinate> set){
        return set.size();
    }

}
