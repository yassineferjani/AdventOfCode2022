package org.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class ServiceTest {

    static Stream<Arguments> generateData(){
        return Stream.of(
                Arguments.of(List.of("$ cd /", "$ ls", "dir a", "14848514 b.txt", "8504156 c.dat",
                        "dir d", "$ cd a", "$ ls", "dir e", "dir ee", "29116 f", "2557 g", "62596 h.lst", "$ cd e", "$ ls", "584 i",
                        "$ cd ..", "$ cd ee","$ ls", "100 e", "$ cd ..", "$ cd ..", "$ cd d", "$ ls", "4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k"),95637)
        );
    }
    @ParameterizedTest
    @MethodSource("generateData")
    void totalSize(List<String> input, long expected) {
        Assertions.assertThat(Service.totalSize(input)).isEqualTo(expected);
    }
}