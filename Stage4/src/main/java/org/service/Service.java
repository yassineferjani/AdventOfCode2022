package org.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Service {

    private static boolean isFullyContain(List<Integer> list1, List<Integer> list2){
        return  ((list1.get(0)<=list2.get(0))&&(list1.get(1)>=list2.get(1))
                || (list1.get(0)>=list2.get(0))&&(list1.get(1)<=list2.get(1)));
    }

    private static boolean isBetween(int toCheck, int from, int to) {
        return from <= toCheck && toCheck <= to;
    }

        private static boolean isOverlap(List<Integer> list1, List<Integer> list2){
        return  isBetween(list1.get(0),list2.get(0),list2.get(1))|| isBetween(list1.get(1),list2.get(0),list2.get(1))
                || isBetween(list2.get(0),list1.get(0),list1.get(1))||isBetween(list2.get(1),list1.get(0),list1.get(1));
    }

    private static List<List<Integer>> convertLineToListOfInteger(String line) {
        String[] firstSequence = line.split(",");
        List<List<Integer>> result = new ArrayList<>();
        for (String str : firstSequence) {
            List<Integer> sublist = Arrays.stream(str.split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            result.add(sublist);
        }
        return result;
    }

    public static int numberOfFullyContainSeries(List<String> list){
        int score = 0;
        for (String line:list) {
            List<List<Integer>> lists = convertLineToListOfInteger(line);
            if (isFullyContain(lists.get(0),lists.get(1)))
                score++;

        }
        return score;
    }

    public static int numberOfOverlapSeries(List<String> list){
        int score = 0;
        for (String line:list) {
            List<List<Integer>> lists = convertLineToListOfInteger(line);
            if (isOverlap(lists.get(0),lists.get(1)))
                score++;

        }
        return score;
    }
}
