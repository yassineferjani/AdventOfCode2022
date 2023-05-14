
package org.service;

import java.util.*;

public class Service {

    private static List<Directory> detectDirectories(List<String> list) {
        List<Directory> directories = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).matches("^\\$ ls") && list.get(i - 1).matches("^\\$ cd [a-zA-Z]+")) {
                Directory d = createDirectory(list, i);
                directories.add(d);
            }
        }
        return directories;
    }

    private static Directory createDirectory(List<String> list, int startIndex) {
        String directoryName = extractDirectoryNameFromCommand(list.get(startIndex - 1));
        Directory directory = Directory.builder().name(directoryName).build();
        int i = startIndex + 1;
        while (i < list.size() && (isFile(list.get(i)) || isDirectory(list.get(i)))) {
            if (isFile(list.get(i))) {
                directory.getFiles().add(createFileFromString(list.get(i)));
            } else {
                directory.getDirectories().add(extractDirectoryName(list.get(i)));
            }
            i++;
        }
        return directory;
    }


    private static boolean isFile(String s) {
        return s.matches("^\\d+\\s[a-zA-Z].*");
    }

    private static File createFileFromString(String s){
        long size =  Integer.parseInt(s.replaceAll("[^0-9]", ""));
        String name = s.split(" ")[1];
        return File.builder().name(name).size(size).build();

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
            for (String subdirName : dir.getDirectories()) {
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
        System.out.println(directories);
        return directories.stream()
                .map(d -> calculateSizeIterative(d, directories))
                .filter(l -> l <= 100000)
                .mapToLong(s -> s)
                .sum();
    }

}
