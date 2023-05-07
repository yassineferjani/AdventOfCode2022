package org.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Service {

    private static char findCommonChar(String s1, String s2) {
        return s1.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> s2.contains(Character.toString(c)))
                .distinct()
                .findFirst()
                .orElseThrow();
    }
    private static int calculatepriority(char commonArticle) {
        if (commonArticle >= 'a' && commonArticle <= 'z')
            return commonArticle - 'a' + 1;
        else if (commonArticle >= 'A' && commonArticle <= 'Z')
            return commonArticle - 'A' + 27;
        throw new IllegalArgumentException();
    }


    public static int calculateScore(List<String> list){
        return list.stream()
                .map(c->calculatepriority(findCommonChar(c.substring(0,c.length()/2),c.substring(c.length()/2))))
                .mapToInt(c->c)
                .sum();
    }

    public static List<List<String>> extractGroups(List<String> list){
       return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(index -> index / 3))
                .values()
                .stream()
                .map(indices -> indices.stream()
                        .map(list::get)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static char groupBadge(List<String> list){
        Set<Character> set1 = list.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        Set<Character> set2 = list.get(1).chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        Set<Character> set3 = list.get(2).chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

        set1.retainAll(set2);
        set1.retainAll(set3);

        if (set1.isEmpty()) {
            throw new RuntimeException();
        } else {
            return set1.iterator().next();
        }
    }


    public static int calculateGroupBadgesScore(List<List<String>> list){
        int score = 0;
        for (List<String> g: list) {
            char badge = groupBadge(g);
            score+= calculatepriority(badge);

        }
        return score;
    }

}
