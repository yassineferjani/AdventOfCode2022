package org.example.service;

import java.util.Arrays;
import java.util.List;

public class Service {

    public static void split(List<String> list){
        for (String l :list) {
            if (l!=null) {
                Arrays.stream(l.split(",")).forEach(System.out::println);
            }
        }
    }
}
