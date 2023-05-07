package org.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    static Stream<Arguments> generateDataToTestCalculateTotalScore(){
        return Stream.of(
                Arguments.of(List.of("A Y", "B X","C Z"),15)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataToTestCalculateTotalScore")
    void test_calculateTotalScore(List<String> input, int expected) {
        assertThat(Service.calculateTotalScore(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataToTestCalculateTopSecretStrategyTotalSore(){
        return Stream.of(
                Arguments.of(List.of("A Y", "B X","C Z"),12)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestCalculateTopSecretStrategyTotalSore")
    void test_calculateTopSecretStrategyTotalSore(List<String> input, int expected) {
        assertThat(Service.calculateTopSecretStrategyTotalSore(input)).isEqualTo(expected);
    }
}