package org.example.model;

import lombok.Builder;

import java.util.Deque;
import java.util.List;
import java.util.Map;
@Builder
public record Monkey(int id, Deque<Integer> startingItem, int operation, int multi, int addition, Map<Boolean,Integer> test) {
}
