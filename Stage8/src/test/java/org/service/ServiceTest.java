package org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;

class ServiceTest {
    static Stream<Arguments> generateDataToTestConvertListTo2DTable(){
        int[][] grid = {
                {3,0,3,7,3},
                {2,5,5,1,2},
                {6,5,3,3,2},
                {3,3,5,4,9},
                {3,5,3,9,0}
        };
        return Stream.of(
          Arguments.of(List.of("30373","25512","65332","33549","35390"),grid)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestConvertListTo2DTable")
    void convertTo2DArray(List<String> input, int[][] expected) {
        assertThat(Service.convertTo2DArray(input)).isEqualTo(expected);
    }

    static Stream<Arguments> generateDataToTestVisibleTree(){
        int[][] grid = {
                {3,0,3,7,3},
                {2,5,5,1,2},
                {6,5,3,3,2},
                {3,3,5,4,9},
                {3,5,3,9,0}
        };
        return Stream.of(
                Arguments.of(grid,21)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestVisibleTree")
    void countVisibleTrees(int[][] input, long expected) {
        assertThat(Service.countVisibleTrees(input)).isEqualTo(expected);
    }
    /*@Test
    public void testMaxScenicScore() {
        int[][] grid = {
                {3, 0, 3, 7, 3},
                {2, 5, 5, 1, 2},
                {6, 5, 3, 3, 2},
                {3, 3, 5, 4, 9},
                {3, 5, 3, 9, 0}
        };

        long maxScenicScore = Service.highestScenicScore(grid);

        assertThat(maxScenicScore).isEqualTo(8);
    }*/

}