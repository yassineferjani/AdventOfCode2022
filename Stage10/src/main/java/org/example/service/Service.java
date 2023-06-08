package org.example.service;

import java.util.ArrayList;
import java.util.List;

public class Service {

    public static long calculateSignalStrengthSum(List<String> list) {
        List<Integer> stops = createStopsList();
        long sum = 0;
        int cycleCounter = 0;
        long currentValue = 1;

        for (String line : list) {
            if ("noop".equals(line)) {
                cycleCounter++;

                if (stops.contains(cycleCounter)) {
                    sum += calculateCycleSum(cycleCounter, currentValue);
                }
                lettresWriter(cycleCounter, currentValue);
            }
            if (line.startsWith("addx")) {
                cycleCounter++;
                if (stops.contains(cycleCounter)) {
                    sum += calculateCycleSum(cycleCounter, currentValue);
                }
                lettresWriter(cycleCounter, currentValue);
                cycleCounter++;
                if (stops.contains(cycleCounter)) {
                    sum += calculateCycleSum(cycleCounter, currentValue);
                }
                lettresWriter(cycleCounter, currentValue);
                currentValue += parseAddxValue(line);
            }
        }
        return sum;
    }

    private static List<Integer> createStopsList() {
        List<Integer> stops = new ArrayList<>();
        stops.add(20);
        stops.add(60);
        stops.add(100);
        stops.add(140);
        stops.add(180);
        stops.add(220);
        return stops;
    }

    private static long calculateCycleSum(int cycleCounter, long currentValue) {
        return cycleCounter * currentValue;
    }

    private static int parseAddxValue(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }

    private static void lettresWriter(int cycleCounter, long currentValue) {
        int currentPos = (cycleCounter % 40);

        if (currentPos < currentValue || currentPos > currentValue + 2) {
            System.out.print(".");
        } else {
            System.out.print("#");
        }
        if (currentPos == 0) {
            System.out.println();
        }
    }

}
