package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Deque;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class Monkey{
    private final int id;
    private Deque<Integer> startingItem;
    private int operation;
    private int multi;
    private int addition;
    private Map<Boolean,Integer> test;
    private int inspections;



    public void round(List<Monkey> monkeyMap) {
        while (!this.startingItem.isEmpty()) {
            int item = this.startingItem.pop();
            int newItem;
            inspections++;
            if (multi == -2) {
                newItem = item * item;
            } else if (multi != -1) {
                newItem = item * multi;
            } else if (addition != -1) {
                newItem = item + addition;
            } else {
                newItem = item;
            }
            newItem = Math.floorDiv(newItem,3);
            if (newItem % operation == 0) {
                monkeyMap.get(test.get(true)).getStartingItem().add(newItem);
            } else {
                monkeyMap.get(test.get(false)).getStartingItem().add(newItem);
            }

        }

    }

}
