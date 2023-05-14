package org.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
public class Directory  {
    private String name;
    private List<String> files = new ArrayList<>();
    private List<String> dir=new ArrayList<>();
    public long sizeFiles(){
        return files.stream()
                .map(this::convertStringToInt)
                .mapToInt(s->s)
                .sum();
    }

    private int convertStringToInt(String s){
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }
}
