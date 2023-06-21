package org.example.model;

import lombok.Builder;

import java.util.List;
import java.util.Map;
@Builder
public record Monkey(int id, List<Integer> startingItem,int operation, int multi, int addition, Map<Boolean,Integer> test) {
}
