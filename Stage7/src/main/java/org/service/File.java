package org.service;

import lombok.Builder;

@Builder
public record File (String name, long size) {
}
