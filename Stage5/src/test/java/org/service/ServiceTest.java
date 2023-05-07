package org.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceTest {

    static Stream<Arguments> generateDataToTestRearrangement(){
        List<Deque<String>> crates = new ArrayList<>();
        crates.add(new LinkedList<>(List.of("Z","N")));
        crates.add(new LinkedList<>(List.of("M","C","D")));
        crates.add(new LinkedList<>(List.of("P")));

        List<Deque<String>> expected = new ArrayList<>();
        expected.add(new LinkedList<>(List.of("C")));
        expected.add(new LinkedList<>(List.of("M")));
        expected.add(new LinkedList<>(List.of("P","D","N","Z")));

        return Stream.of(
                Arguments.of(crates,List.of("move 1 from 2 to 1","move 3 from 1 to 3","move 2 from 2 to 1","move 1 from 1 to 2") ,expected)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestRearrangement")
    void rearrangement(List<Deque<String>> input, List<String> instructions , List<Deque<String>> expected) {
        Service.rearrangement(input,instructions);
        assertThat(input).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataToTestRearrangement9001(){
        List<Deque<String>> crates = new ArrayList<>();
        crates.add(new LinkedList<>(List.of("Z","N")));
        crates.add(new LinkedList<>(List.of("M","C","D")));
        crates.add(new LinkedList<>(List.of("P")));

        List<Deque<String>> expected = new ArrayList<>();
        expected.add(new LinkedList<>(List.of("M")));
        expected.add(new LinkedList<>(List.of("C")));
        expected.add(new LinkedList<>(List.of("P","Z","N","D")));

        return Stream.of(
                Arguments.of(crates,List.of("move 1 from 2 to 1","move 3 from 1 to 3","move 2 from 2 to 1","move 1 from 1 to 2") ,expected)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestRearrangement9001")
    void test_rearrangement9001(List<Deque<String>> input, List<String> instructions , List<Deque<String>> expected) {
        Service.rearrangement9001(input,instructions);
        assertThat(input).isEqualTo(expected);
    }
}