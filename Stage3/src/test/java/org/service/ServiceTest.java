package org.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceTest {
    static Stream<Arguments> generateDataToTestCalculateScore(){
        return Stream.of(
                Arguments.of(List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                                "PmmdzqPrVvPwwTWBwg", "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"),
                        157)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestCalculateScore")
    void test_calculateScore(List<String> input, int expected) {
        assertThat(Service.calculateScore(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataToTestCalculateGroupBadgesScore(){
        return Stream.of(
                Arguments.of(List.of(List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                                        "PmmdzqPrVvPwwTWBwg"),
                                List.of("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw")), 70
                )
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataToTestCalculateGroupBadgesScore")
    void test_calculateGroupBadgesScore(List<List<String>> input, int expected) {
        assertThat(Service.calculateGroupBadgesScore(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataToTestExtractGroups(){
        return Stream.of(
                Arguments.of(List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                                "PmmdzqPrVvPwwTWBwg", "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"),
                        List.of(List.of("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                                        "PmmdzqPrVvPwwTWBwg"),
                                List.of("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"))
                )
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestExtractGroups")
    void test_extractGroups(List<String> input, List<List<String>> expected) {
        assertThat(Service.extractGroups(input)).isEqualTo(expected);
    }
}