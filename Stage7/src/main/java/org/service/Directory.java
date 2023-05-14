package org.service;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
@Builder
@Value
public class Directory  {
    String name;
    List<File> files = new ArrayList<>();
    List<Directory> directories = new ArrayList<>();
    String subDirectory;

    public long sizeFiles(){
        return files.stream()
                .map(File::size)
                .mapToLong(l->l)
                .sum();
    }
    public long totalSize (){
        return directories.stream().mapToLong(Directory::totalSize).sum() + sizeFiles();
    }
    public String toString(){
        return name + "  "+totalSize() + "  "+directories;
    }

}
