package org.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceTest {
    static Stream<Arguments> generateDataCalculateGroupsSum() {
        return Stream.of(
                Arguments.of(Arrays.asList("12", "10", "5", "3"), Collections.singletonList(30)),
                Arguments.of(Arrays.asList("12", "10", "5", null, "1", "2"), Arrays.asList(27, 3)));
    }

    @ParameterizedTest
    @MethodSource("generateDataCalculateGroupsSum")
    void test_calculateGroupsSum(List<String> input, List<Integer> expected) {
        assertThat(Service.calculateGroupsSum(input)).containsExactlyElementsOf(expected);
    }
    static Stream<Arguments> generateDataMaxCalorie() {
        return Stream.of(
                Arguments.of(List.of(12, 10, 5, 3), 12),
                Arguments.of(List.of(200, 10, 5, 1, 2), 200));
    }
    @ParameterizedTest
    @MethodSource("generateDataMaxCalorie")
    void test_maxCalorie(List<Integer> input, int expected) {
        assertThat(Service.maxCalorie(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataTestSumThreeMaxCalorieGroup(){
        return Stream.of(
                Arguments.of(List.of(2,14,200,100,50,20,36),350),
                Arguments.of(List.of(2,140,20,100,500,20,360),1000)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataTestSumThreeMaxCalorieGroup")
    void test_sumThreeMaxCalorieGroup(List<Integer> input, int expected) {
        assertThat(Service.sumThreeMaxCalorieGroup(input)).isEqualTo(expected);
    }
}