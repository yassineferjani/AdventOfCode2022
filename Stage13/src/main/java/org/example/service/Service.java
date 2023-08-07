package org.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
        List<List<String>>listCombinaison = new ArrayList<>();
        int j = 0;
        while (!lists.get(0).isEmpty() && !lists.get(1).isEmpty()){
            int i = lists.get(0).size();
        if(lists.get(0).get(i-1)<lists.get(1).get(j)) {
            int a = lists.get(0).get(i-1);
            int b = lists.get(1).get(j);
            listCombinaison.add(List.of(s.substring(a, b+1)));
            lists.get(0).remove(i-1);
            lists.get(1).remove(j);
            j=0;
        }else if(j+1 <lists.get(1).size()) {
            j++;
        }
        }
        return listCombinaison;
     }


}

