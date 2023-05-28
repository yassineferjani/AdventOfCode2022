package org.service;

import org.model.Coordinate;
import org.model.Movement;
import org.model.Rope;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Service {

    public static List<Set<Coordinate>> executeInstructions(List<Movement> list){
        Coordinate [] tail = new Coordinate[10];
        Arrays.fill(tail,Coordinate.builder().y(0).x(0).build());
        Rope rope = new Rope(tail,Coordinate.builder().x(0).y(0).build());
        return rope.execute(list);
    }

}
