package org.example.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceTest {
    static Stream<Arguments> generateData(){
        List<String> input = Arrays.asList("Monkey 0:",
                "Starting items: 79, 98",
                "Operation: new = old * 19",
                "Test: divisible by 23",
                "If true: throw to monkey 2",
                "If false: throw to monkey 3",
                null,
                "Monkey 1:",
                "Starting items: 54, 65, 75, 74",
                "Operation: new = old + 6",
                "Test: divisible by 19",
                "If true: throw to monkey 2",
                "If false: throw to monkey 0",
                null,
                "Monkey 2:",
                "Starting items: 79, 60, 97",
                "Operation: new = old * old",
                "Test: divisible by 13",
                "If true: throw to monkey 1",
                "If false: throw to monkey 3",
                null,
                "Monkey 3:",
                "Starting items: 74",
                "Operation: new = old + 3",
                "Test: divisible by 17",
                "If true: throw to monkey 0",
                "If false: throw to monkey 1");
        return Stream.of(
                Arguments.of(input,10605)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void test_result1(List<String> input, long expected) {
        assertThat(Service.result1(input,20,false)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void test_result2(List<String> input, long expected) {
        assertThat(Service.result1(input,10000,true)).isEqualTo(2713310158L);
    }
}