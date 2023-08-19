package org.example.service;

import lombok.Builder;

import java.util.List;
@Builder
public record Packet(List<List<Integer>> list) {

}
