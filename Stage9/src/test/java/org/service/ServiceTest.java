package org.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.model.Coordinate;
import org.model.Movement;
import org.model.Rope;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.model.Direction.Right;
import static org.model.Direction.Up;

class ServiceTest {
    static Stream<Arguments>generateDataToTestExecuteInstruction(){
        return Stream.of(
                Arguments.of(List.of(
                        Movement.builder().step(2).direction(Right).build(),
                        Movement.builder().step(1).direction(Up).build()),
                        List.of(
                                Rope.builder().tail(Coordinate.builder().y(0).x(0).build())
                                        .head(Coordinate.builder().x(0).y(0).build())
                                        .build(),
                                Rope.builder().tail(
                                                Coordinate.builder().y(0).x(0).build())
                                        .head(Coordinate.builder().x(1).y(0).build())
                                        .build(),
                                Rope.builder().tail(
                                                Coordinate.builder().y(0).x(1).build())
                                        .head(Coordinate.builder().x(2).y(0).build())
                                        .build(),
                                Rope.builder().tail(
                                                Coordinate.builder().y(0).x(1).build())
                                        .head(Coordinate.builder().x(2).y(1).build())
                                        .build())
                        )
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestExecuteInstruction")
    void test_executeInstructions(List<Movement> input, List<Rope> expected) {
        assertThat(Service.executeInstructions(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataToTestTailPosition(){
        return Stream.of(
          Arguments.of(
                  List.of(
                          Rope.builder().tail(Coordinate.builder().y(0).x(0).build())
                                  .head(Coordinate.builder().x(0).y(0).build())
                                  .build(),
                          Rope.builder().tail(
                                          Coordinate.builder().y(0).x(0).build())
                                  .head(Coordinate.builder().x(1).y(0).build())
                                  .build()),
                  Set.of(Coordinate.builder().y(0).x(0).build())

          )
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestTailPosition")
    void test_tailPosition(List<Rope> input, Set<Coordinate> expected) {
        assertThat(Service.tailPosition(input)).isEqualTo(expected);
    }
    static Stream<Arguments> generateDataToTestCountTailPosition(){
        return Stream.of(
                Arguments.of(
                        Set.of(Coordinate.builder().y(0).x(0).build(), Coordinate.builder().y(1).x(0).build())
                        ,2
                )
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestCountTailPosition")
    void test_countTailPosition(Set<Coordinate> input, int expected) {
        assertThat(Service.countTailPosition(input)).isEqualTo(expected);
    }
}