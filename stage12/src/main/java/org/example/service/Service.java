package org.example.service;

import java.util.List;

public class Service {

    public static char[][] convertToMultidimensionalArray(List<String> list) {
        return list.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
