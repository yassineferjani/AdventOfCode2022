package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

     private static JsonNode createPacket(String s) throws JsonProcessingException {
         ObjectMapper objectMapper = new ObjectMapper();
         JsonNode jsonNode = objectMapper.readTree(s);
         if (jsonNode.isArray())
             return jsonNode;
         throw new RuntimeException();
     }

    private static Map<Integer,Packet> createListPacket (List<String> list) throws JsonProcessingException {
        Map<Integer,Packet> map = new HashMap<>();
        int j = 1;
        int i = 0;
        JsonNode right = null;
        JsonNode left = null;
        for (String s : list){
            if(s!=null && i<1) {
                i++;
                right = createPacket(s);
            }
            else if (i>=1) {
                i=0;
                left = createPacket(s);
            }
            else {
                map.put(j, Packet.builder().right(right).left(left).build());
                j++;
            }
        }
        map.put(j, Packet.builder().left(left).right(right).build());
        return map;
    }

    private static boolean compare(JsonNode right, JsonNode left) {
        int size = right.size();

        for (int i = 0; i < size; i++) {
            if (i >= left.size()) {
                return false;
            }
            if (right.get(i).size() < left.get(i).size())
                return false;
            if (!right.get(i).isArray() || !left.get(i).isArray()) {
                int  a = right.get(i).asInt();
                int  b = left.get(i).asInt();
                if (right.get(i).asInt() > left.get(i).asInt()) {
                    return false;
                }
            } else {
                if (!compare(right.get(i), left.get(i)))
                    return false;
            }
            }
        return true;
     }


    public static int resolvePart1(List<String> list) throws JsonProcessingException {
        Map<Integer,Packet> map = createListPacket(list);
        int result = 0;
        for (Map.Entry<Integer, Packet> entry : map.entrySet()) {
            if (compare(entry.getValue().right(), entry.getValue().left())) {
                result += entry.getKey();
                System.out.println(entry.getKey());
            }
        }
        return result;
    }




}

