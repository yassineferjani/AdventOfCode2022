package org.Adapter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.model.Movement;

import java.util.List;
import java.util.stream.Stream;

import static org.model.Direction.*;

class AdapterTest {
    static Stream<Arguments> generateData(){
        return Stream.of(
          Arguments.of(List.of("R 4", "L 7","U 1"), List.of(Movement.builder().step(4).direction(Right).build(),
                  Movement.builder().step(7).direction(Left).build(),Movement.builder().step(1).direction(Up).build()))
        );
    }
    @ParameterizedTest
    @MethodSource("generateData")
    void test_mapToListOfMovement(List<String> input, List<Movement> excepted) {
        Assertions.assertThat(Adapter.mapToListOfMovement(input)).isEqualTo(excepted);
    }
}