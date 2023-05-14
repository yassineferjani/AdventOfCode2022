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
    List<String> directories = new ArrayList<>();

    public long sizeFiles(){
        return files.stream()
                .map(File::size)
                .mapToLong(l->l)
                .sum();
    }
   /* public long totalSize (){
        return dir.stream().mapToLong(Directory::totalSize).sum() + sizeFiles();
    }*/

}
