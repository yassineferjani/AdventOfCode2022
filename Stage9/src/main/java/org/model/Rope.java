package org.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Rope {
    Coordinate tail;
    Coordinate head;

    public Rope(Coordinate tail, Coordinate head){
        this.head = head;
        this.tail = tail;
    }

    private static Coordinate move (final Coordinate coordinate, final Direction direction) {
        return switch (direction) {
            case Up -> Coordinate.builder().x(coordinate.x()).y(coordinate.y() + 1).build();
            case Right -> Coordinate.builder().x(coordinate.x() + 1).y(coordinate.y()).build();
            case Down -> Coordinate.builder().x(coordinate.x()).y(coordinate.y() - 1).build();
            case Left -> Coordinate.builder().x(coordinate.x() - 1).y(coordinate.y()).build();
        };
    }

    public List<Rope> execute(final Movement instruction) {
        List<Rope> list = new ArrayList<>();
        for(int i =0;i<=instruction.step();i++) {
            tail = Coordinate.builder().y(head.y()).x(head.x()).build();
            Coordinate nextCoordinate = move(head, instruction.direction());
            head = Coordinate.builder().x(nextCoordinate.x()).y(nextCoordinate.y()).build();
            list.add(Rope.builder().head(head).tail(tail).build());
        }
        return list;
    }

}
