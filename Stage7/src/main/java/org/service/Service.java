package org.service;

import java.util.*;

public class Service {

    private static List<Directory> detectDirectories(List<String> list) {
        List<Directory> directories = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).matches("^\\$ ls") && list.get(i - 1).matches("^\\$ cd [a-zA-Z]+")) {
                Directory d = new Directory();
                d.setName(extractDirectoryNameFromCommand(list.get(i - 1)));
                int j = i + 1;
                while (j < list.size() && (isDirectory(list.get(j)) || isFile(list.get(j)))) {
                    if (isDirectory(list.get(j)))
                        d.getDir().add(extractDirectoryName(list.get(j)));
                    else
                        d.getFiles().add(list.get(j));
                    j++;
                }
                directories.add(d);
            }
        }
        return directories;
    }

    private static boolean isFile(String s) {
        return s.matches("^\\d+\\s[a-zA-Z].*");
    }

    private static boolean isDirectory(String s) {
        return s.matches("^dir [a-zA-Z]+");
    }

    private static String extractDirectoryNameFromCommand(String s) {
        return s.replace("$ cd ", "").trim();
    }

    private static String extractDirectoryName(String s) {
        return s.replace("dir ", "").trim();
    }

    private static long calculateSizeIterative(Directory directory, List<Directory> directories) {
        long totalSize = directory.sizeFiles();
        Set<String> visited = new HashSet<>();
        Stack<Directory> stack = new Stack<>();
        stack.push(directory);
        visited.add(directory.getName());
        while (!stack.isEmpty()) {
            Directory dir = stack.pop();
            for (String subdirName : dir.getDir()) {
                if (!visited.contains(subdirName)) {
                    Directory subdir = directories.stream().filter(d -> d.getName().equals(subdirName)).findFirst().orElse(null);
                    if (subdir != null) {
                        stack.push(subdir);
                        visited.add(subdirName);
                        totalSize += subdir.sizeFiles();
                    }
                }
            }
        }
        return totalSize;
    }

    public static Long totalSize(List<String> list) {
        List<Directory> directories =detectDirectories (list);
        return directories.stream()
                .map(d -> calculateSizeIterative(d, directories))
                .filter(l -> l < 100000)
                .mapToLong(s -> s)
                .sum();
    }

}
