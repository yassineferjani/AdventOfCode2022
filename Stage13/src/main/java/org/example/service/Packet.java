package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;

@Builder
public record Packet(JsonNode right, JsonNode left) {

}
