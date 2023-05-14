package org.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceTest {

    static Stream<Arguments> generateDataToTestFirstMarker(){
    return Stream.of(
            Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz",5),
            Arguments.of("nppdvjthqldpwncqszvftbrmjlhg",6),
            Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",10),
            Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",11)
    );
    }
    static Stream<Arguments> generateDataToTestFirstMarker14(){
        return Stream.of(
                Arguments.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb",19),
                Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz",23),
                Arguments.of("nppdvjthqldpwncqszvftbrmjlhg",23),
                Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",29),
                Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",26)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestFirstMarker")
    void test_firstMarker( String input , int expected) {
        assertThat(Service.firstMarker(input,4)).isEqualTo(expected);
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestFirstMarker14")
    void test_firstMarker14( String input , int expected) {
        assertThat(Service.firstMarker(input,14)).isEqualTo(expected);
    }
}