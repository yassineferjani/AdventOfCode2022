package org.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.model.Direction.Right;
import static org.model.Direction.Up;

class RopeTest {
    static Stream<Arguments> generateData(){
        return Stream.of(
          Arguments.of(
                  List.of(
                          Movement.builder().step(1).direction(Right).build(),
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
                                          Coordinate.builder().y(0).x(0).build())
                                  .head(Coordinate.builder().x(1).y(1).build())
                                  .build()))
        );
    }
    @ParameterizedTest
    @MethodSource("generateData")
    void execute(List<Movement> input, List<Rope> expected) {
    Rope rope = Rope.builder()
            .head(Coordinate.builder().x(0).y(0).build())
            .tail(Coordinate.builder().y(0).x(0).build())
            .build();
        Assertions.assertThat(rope.execute(input)).isEqualTo(expected);
    }
}