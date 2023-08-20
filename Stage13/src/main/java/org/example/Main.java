package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.service.Packet;
import org.example.service.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JsonProcessingException {
        List<String> input = Read.readFile("D:\\AdventOfCode2022\\Stage13\\src\\main\\resources\\input13.txt");
        Map<Integer,Packet> map = Service.createListPacket(input);
        map.forEach((key, value) -> System.out.println(key + " " + value));

    }



}
