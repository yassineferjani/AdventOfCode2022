package org.service;

public class Service {

    private static boolean isCode (String s){
        long numberOfDistinctChar = s.chars().distinct().count();
        return numberOfDistinctChar == s.length();
    }

    public static int firstMarker (String s, int numberOfDistinctChar){
        for (int i = 0; i < s.length() - numberOfDistinctChar-1; i++) {
            String substring = s.substring(i, i + numberOfDistinctChar);
            if(isCode(substring))
                return i+numberOfDistinctChar;
        }
        throw new RuntimeException();
    }
}
