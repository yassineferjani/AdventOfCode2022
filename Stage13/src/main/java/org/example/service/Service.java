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
                map.put(Integer.valueOf(j), Packet.builder().right(right).left(left).build());
                j++;
            }
        }
        map.put(Integer.valueOf(j), Packet.builder().left(left).right(right).build());
        return map;
    }

    private static Boolean compare(JsonNode right, JsonNode left){
         for (int i = 0; i < right.size(); i++) {
             if (left.size() <= i)
                 return Boolean.FALSE;

             if (!right.get(i).isArray() && !left.get(i).isArray()) {
                 if (right.get(i).asInt() > left.get(i).asInt()) {
                     return Boolean.FALSE;
                 }
                 if (right.get(i).asInt() < left.get(i).asInt()) {
                     return Boolean.TRUE;
                 }
             } else {
                 if (right.get(i).isArray() && !left.get(i).isArray()){
                     if (Boolean.FALSE.equals(compare(right.get(i), convertSimpleNodeToJson(left.get(i).toPrettyString())))){
                         if (compare(right.get(i), convertSimpleNodeToJson(left.get(i).toPrettyString())) != null) {
                             return compare(right.get(i), convertSimpleNodeToJson(left.get(i).toPrettyString()));
                         }
                     }
                 }else  if (!right.get(i).isArray() && left.get(i).isArray()){
                     if (Boolean.FALSE.equals(compare(convertSimpleNodeToJson(right.get(i).toPrettyString()), left.get(i))))
                         if (compare(convertSimpleNodeToJson(right.get(i).toPrettyString()), left.get(i)) != null) {
                             return compare(convertSimpleNodeToJson(right.get(i).toPrettyString()), left.get(i));
                         }
                 }else {
                     if (Boolean.FALSE.equals(compare(right.get(i), left.get(i))))
                         if (compare(right.get(i), left.get(i)) != null) {
                             return compare(right.get(i), left.get(i));
                         }
                 }
             }
         }
        return right.size() != left.size() ? Boolean.TRUE : null;
     }

     private static JsonNode convertSimpleNodeToJson(String s) {
         try {
             ObjectMapper objectMapper = new ObjectMapper();
             JsonNode jsonNode = objectMapper.readTree(s);

             if (!jsonNode.isArray()) {
                 jsonNode = objectMapper.createArrayNode().add(jsonNode);
             }

             return jsonNode;
         } catch (Exception e) {
            throw new RuntimeException();
         }
     }


    public static int resolvePart1(List<String> list) throws JsonProcessingException {
        Map<Integer,Packet> map = createListPacket(list);
        int result = 0;
        for (Map.Entry<Integer, Packet> entry : map.entrySet()) {
            if (Boolean.TRUE.equals(compare(entry.getValue().right(), entry.getValue().left()))) {
                result += entry.getKey();
                System.out.println(entry.getKey());
            }
        }
        return result;
    }
}

