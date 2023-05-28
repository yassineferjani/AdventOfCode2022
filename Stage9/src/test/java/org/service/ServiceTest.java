package org.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.model.Coordinate;
import org.model.Movement;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.model.Direction.*;

public class ServiceTest {

    static Stream<Arguments> generateData(){
        return Stream.of(
                Arguments.of( List.of(
                                Movement.builder().step(4).direction(Right).build(),
                                Movement.builder().step(4).direction(Up).build(),
                                Movement.builder().step(3).direction(Left).build(),
                                Movement.builder().step(1).direction(Down).build(),
                                Movement.builder().step(4).direction(Right).build(),
                                Movement.builder().step(1).direction(Down).build(),
                                Movement.builder().step(5).direction(Left).build(),
                                Movement.builder().step(2).direction(Right).build()

                        ),
                        List.of(
                                Set.of(Coordinate.builder().x(0).y(0).build(),Coordinate.builder().x(3).y(3).build()
                                        ,Coordinate.builder().x(2).y(2).build(),Coordinate.builder().y(4).x(3).build(),
                                        Coordinate.builder().x(1).y(2).build(),Coordinate.builder().x(2).y(4).build(),
                                        Coordinate.builder().x(3).y(0).build(),Coordinate.builder().x(4).y(1).build(),
                                        Coordinate.builder().x(2).y(0).build(),Coordinate.builder().x(4).y(2).build(),
                                        Coordinate.builder().x(1).y(0).build(),Coordinate.builder().x(4).y(3).build(),
                                        Coordinate.builder().x(3).y(2).build()),
                                Set.of(Coordinate.builder().x(0).y(0).build()))
                )
        );

    }

        @ParameterizedTest
        @MethodSource("generateData")
        void test_executeInstructions(List<Movement> input, List<Set<Coordinate>> expected) {
             assertThat(Service.executeInstructions(input)).isEqualTo(expected);
        }
}