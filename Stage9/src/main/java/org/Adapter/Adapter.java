package org.Adapter;

import org.model.Direction;
import org.model.Movement;

import java.util.List;
import java.util.stream.Collectors;

import static org.model.Direction.*;

public class Adapter {
    private static Direction mapToDirection(char s){
        return switch (s){
            case 'R'-> Right;
            case 'L'-> Left;
            case 'U'->Up;
            case 'D'->Down;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }

    private static Movement mapToMovement(String s){
        String [] strings = s.split(" ");
        return Movement.builder().direction(mapToDirection(strings[0].charAt(0))).step(Integer.parseInt(strings[1])).build();
    }


    public static List<Movement> mapToListOfMovement(List<String> list){
        return list.stream()
                .map(Adapter::mapToMovement)
                .collect(Collectors.toList());
    }
}
