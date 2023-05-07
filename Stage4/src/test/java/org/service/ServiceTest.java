package org.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class ServiceTest {
    static Stream<Arguments> generateDataToTestIsFullyContainScore(){
        return Stream.of(
                Arguments.of(List.of("2-4,6-8",
                        "2-3,4-5",
                        "5-7,7-9",
                        "2-8,3-7",
                        "6-6,4-6",
                        "2-6,4-8"),2)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestIsFullyContainScore")
    void test_numberOfFullyContainSeries(List<String> input,int expected) {
        Assertions.assertThat(Service.numberOfFullyContainSeries(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataToTestIsOverlapScore(){
        return Stream.of(
                Arguments.of(List.of("2-4,6-8",
                        "2-3,4-5",
                        "5-7,7-9",
                        "2-8,3-7",
                        "6-6,4-6",
                        "2-6,4-8"),4)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestIsOverlapScore")
    void test_numberOfOverlapSeries(List<String> input,int expected) {
        Assertions.assertThat(Service.numberOfOverlapSeries(input)).isEqualTo(expected);
    }
}