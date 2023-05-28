package org.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
public class Rope {
    private Coordinate [] tail;
    private Coordinate head;

    public Rope(final Coordinate [] tail, final Coordinate head){
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

    public List<Set<Coordinate>> execute(List<Movement> instructions) {
        List<Set<Coordinate>> positions = new ArrayList<>();
        Set<Coordinate> p1 = new HashSet<>();
        Set<Coordinate> p2 = new HashSet<>();

        p1.add(tail[0]);
        p2.add(tail[8]);

        for (Movement line : instructions) {
            Direction d = line.direction();
            int amt = line.step();

            for (int i = 0; i < amt; i++) {
                int newHeadX = head.x();
                int newHeadY = head.y();

                head = move(head, d);

                tail[0] = calculateNextPos(head, tail[0]);

                for (int j = 1; j < 9; j++) {
                    tail[j] = calculateNextPos(tail[j - 1], tail[j]);
                }

                p1.add(tail[0]);
                p2.add(tail[8]);
            }
        }
        positions.add(new HashSet<>(p1));
        positions.add(new HashSet<>(p2));
        return positions;
    }

}
