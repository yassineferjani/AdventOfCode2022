package org.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Service {

    public static List<Integer> calculateGroupsSum(List<String> list){
        List <Integer> groupSum = new ArrayList<>();
        int currentSum = 0;
        for (String number : list) {
            if (number == null) {
                groupSum.add(currentSum);
                currentSum = 0;
            } else {
                currentSum += Integer.parseInt(number);
            }
        }
        groupSum.add(currentSum);
        return groupSum;
    }

    public static int maxCalorie (List<Integer> list){
        return list.stream()
                .mapToInt(i->i)
                .max()
                .orElseThrow(RuntimeException::new);
    }

    public static int sumThreeMaxCalorieGroup(List<Integer> list){
        return list.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
