package org.example.service;

import java.util.ArrayList;
import java.util.List;

public class Service {

    public static List<List<String>> groups(List<String> list) {
        List<List<String>> finalList = new ArrayList<>();
        List<String> s = new ArrayList<>();

        list.forEach(l -> {
                    if (l != null) {
                        s.add(l);
                    } else {
                        finalList.add(new ArrayList<>(s));
                        s.clear();
                    }
                });

        finalList.add(new ArrayList<>(s));
        return finalList;
    }
}
