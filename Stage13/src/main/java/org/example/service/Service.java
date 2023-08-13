package org.example.service;

import java.util.*;
import java.util.stream.Collectors;

public class Service {

    public static void split(List<String> list){
        for (String l :list) {
            if (l!=null) {
                Arrays.stream(l.split(",")).forEach(System.out::println);
            }
        }
    }

    public static List<List<Integer>> extractIndexList(String s){
        List<Integer> listStart = new ArrayList<>();
        List<Integer> listEnd = new ArrayList<>();
        char [] chars = s.toCharArray();
        for (int i=0;i<chars.length;i++) {
            if (chars[i] == '[')
                listStart.add(i);
            else if (chars[i]==']')
                listEnd.add(i);
        }
        return List.of(listStart,listEnd);
    }

    public static List<List<String>> extractSubList(String s){
        List<List<Integer>> lists = extractIndexList(s);
        List<List<String>>listCombination = new ArrayList<>();
        int j = 0;
        while (!lists.get(0).isEmpty() && !lists.get(1).isEmpty()){
            int i = lists.get(0).size();
        if(lists.get(0).get(i-1)<lists.get(1).get(j)) {
            listCombination.add(List.of(s.substring(lists.get(0).get(i-1)+1, lists.get(1).get(j))));
            lists.get(0).remove(i-1);
            lists.get(1).remove(j);
            j=0;
        }else if(j+1 <lists.get(1).size()) {
            j++;
        }
        }
        return listCombination;
     }

     public static Map<Integer, List<List<String>>> convertInputToMap(List<String> list){
       /* return list.stream().map(Service::extractSubList).collect(Collectors.toMap(i -> i, i -> list.get(i))))*/
         Map<Integer,List<List<String>>> map = new HashMap<>();
         int j = 1;
         for (String s : list){
             if(null!=s) {
                 map.put(j, extractSubList(s));
                 j++;
             }
         }
         return map;
     }


}

