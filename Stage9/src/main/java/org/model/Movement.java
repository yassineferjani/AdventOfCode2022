package org.model;

import lombok.Builder;

@Builder
public record Movement(Direction direction, int step) {
}
