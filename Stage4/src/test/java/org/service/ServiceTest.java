package org.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    static Stream<Arguments> generateDataToTestIsFullyContain(){
        return Stream.of(
                Arguments.of(List.of(2,4),List.of(6,8),false),
                Arguments.of(List.of(2,3),List.of(4,5),false),
                Arguments.of(List.of(5,7),List.of(7,9),false),
                Arguments.of(List.of(2,8),List.of(3,7),true),
                Arguments.of(List.of(6,6),List.of(4,6),true),
                Arguments.of(List.of(2,6),List.of(4,8),false)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestIsFullyContain")
    void isFullyContain(List<Integer> list1,List<Integer> list2,boolean expected) {
        Assertions.assertThat(Service.isFullyContain(list1,list2)).isEqualTo(expected);
    }

/*    static Stream<Arguments> generateDataToTestIsFullyContainScore(){
        return Stream.of(
                Arguments.of(List.of("18-20,19-21",
                        "9-86,9-87",
                        "7-8,8-18",
                        "82-98,98-99",
                        "17-17,17-77"))
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestIsFullyContain")
    void isFullyContain(List<Integer> list1,List<Integer> list2,boolean expected) {
        Assertions.assertThat(Service.isFullyContain(list1,list2)).isEqualTo(expected);
    }*/
}