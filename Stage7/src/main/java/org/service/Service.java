
package org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Service {

    private static List<Directory> detectDirectories(List<String> list) {
        List<Directory> directories = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).matches("^\\$ ls") && list.get(i - 1).matches("^\\$ cd [a-zA-Z]+")) {
                Directory d = createDirectory(list, i, directories);
                directories.add(d);
            }
        }
        System.out.println(directories);
        return directories;
    }

    private static Directory createDirectory(List<String> list, int startIndex, List<Directory> directories) {
        String directoryName = extractDirectoryNameFromCommand(list.get(startIndex - 1));
        Directory directory = new Directory(directoryName,null);
        int i = startIndex + 1;
        while (i < list.size() && (isFile(list.get(i)) || isDirectory(list.get(i)))) {
            if (isFile(list.get(i))) {
                directory.getFiles().add(createFileFromString(list.get(i)));
            } else if (isDirectory(list.get(i))){
                String subdirName = extractDirectoryName(list.get(i));
                Optional<Directory> subdir = directories.stream().filter(d -> d.getName().equals(subdirName)).findFirst();
                if (subdir.isPresent()) {
                    directory.getDirectories().add(subdir.get());
                } else {
                    directory.getDirectories().add(Directory.builder().name(subdirName).subDirectory(directoryName).build());
                }
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

     public static Long totalSize(List<String> list) {
        List<Directory> directories =detectDirectories (list);
        return directories.stream()
                .map(Directory::totalSize)
                .filter(l -> l <= 100000)
                .mapToLong(s -> s)
                .sum();
    }

}
