package org.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Rope {
    private Coordinate tail;
    private Coordinate head;

    public Rope(final Coordinate tail, final Coordinate head){
        this.head = head;
        this.tail = tail;
    }

    private Coordinate calculateNextPos(final Coordinate head, final Coordinate tail) {
        int differenceX = head.x() - tail.x();
        int differenceY = head.y() - tail.y();

        Coordinate newTail = tail;

        if (differenceX == 2) {
            newTail = Coordinate.builder().x(head.x() - 1).y(head.y()).build();
            if (Math.abs(differenceY) < 2) {
                newTail = Coordinate.builder().x(newTail.x()).y(head.y()).build();
            }
        } else if (differenceX == -2) {
            newTail = Coordinate.builder().x(head.x() + 1).y(head.y()).build();
            if (Math.abs(differenceY) < 2) {
                newTail = Coordinate.builder().x(newTail.x()).y(head.y()).build();
            }
        } else if (differenceY == 2) {
            newTail = Coordinate.builder().x(head.x()).y(head.y() - 1).build();
            if (Math.abs(differenceX) < 2) {
                newTail = Coordinate.builder().x(head.x()).y(newTail.y()).build();
            }
        } else if (differenceY == -2) {
            newTail = Coordinate.builder().x(head.x()).y(head.y() + 1).build();
            if (Math.abs(differenceX) < 2) {
                newTail = Coordinate.builder().x(head.x()).y(newTail.y()).build();
            }
        }

        return newTail;
    }


    private static Coordinate move (final Coordinate coordinate, final Direction direction) {
        return switch (direction) {
            case Up -> Coordinate.builder().x(coordinate.x()).y(coordinate.y() + 1).build();
            case Right -> Coordinate.builder().x(coordinate.x() + 1).y(coordinate.y()).build();
            case Down -> Coordinate.builder().x(coordinate.x()).y(coordinate.y() - 1).build();
            case Left -> Coordinate.builder().x(coordinate.x() - 1).y(coordinate.y()).build();
        };
    }


    public List<Rope> execute(final List<Movement> instructions) {
        List<Rope> ropes = new ArrayList<>();
        Rope currentRope = Rope.builder().head(head).tail(tail).build();
        ropes.add(currentRope);

        for (Movement movement : instructions) {
            for (int i = 0; i < movement.step(); i++) {
                head = move(head, movement.direction());
                tail = calculateNextPos(head, tail);
                currentRope = Rope.builder().head(head).tail(tail).build();
                ropes.add(currentRope);
            }
        }
        System.out.println(ropes);
        return ropes;
    }


}
