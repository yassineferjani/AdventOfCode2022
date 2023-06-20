package org.example.model;

import lombok.Builder;

import java.util.List;
import java.util.Map;
@Builder
public record Monkey(String name, List<Integer> startingItem, String operation, int operationNumber,
                     List<Map<Boolean,Monkey>> test) {
}
