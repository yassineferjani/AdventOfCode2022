package org.model;

import lombok.Builder;

@Builder
public record Rope(Position tail ,Position head ) {

}
