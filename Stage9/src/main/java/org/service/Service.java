package org.service;

import org.model.Coordinate;
import org.model.Movement;
import org.model.Rope;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Service {

    public static int executeInstructions(List<Movement> list){
        Rope rope = new Rope(Coordinate.builder().x(0).y(0).build(), Coordinate.builder().x(0).y(0).build());
        Set<Coordinate> set = new HashSet<>();
        for (Movement movement : list){
            rope.execute(movement);
            set.add(rope.getTail());
        }
        System.out.println(rope);
        return set.size();
    }


}
