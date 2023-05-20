package org.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    static Stream<Arguments> generateDataToTestGetScenicGrid(){
        int[][] grid = {
                {3, 0, 3, 7, 3},
                {2, 5, 5, 1, 2},
                {6, 5, 3, 3, 2},
                {3, 3, 5, 4, 9},
                {3, 5, 3, 9, 0}
        };
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 1, 4, 1, 0},
                {0, 6, 1, 2, 0},
                {0, 1, 8, 3, 0},
                {0, 0, 0, 0, 0}
        };
        return Stream.of(
                Arguments.of(grid,expected)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestGetScenicGrid")
    public void testGetScenicGrid(int[][] input, int[][] expected) {
        assertThat(Service.getScenicGrid(input)).isEqualTo(expected);

    }

    static Stream<Arguments> generateDataToTestMaxScenicScore(){
        int[][] grid = {
                {3, 0, 3, 7, 3},
                {2, 5, 5, 1, 2},
                {6, 5, 3, 3, 2},
                {3, 3, 5, 4, 9},
                {3, 5, 3, 9, 0}
        };
        return Stream.of(
                Arguments.of(grid,9)
        );
    }
    @ParameterizedTest
    @MethodSource("generateDataToTestMaxScenicScore")
    public void testMaxScenicScore(int[][] input, int expected) {

        assertThat(Service.getMaxScenicScore(input)).isEqualTo(expected);

    }

}