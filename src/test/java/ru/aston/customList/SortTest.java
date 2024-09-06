package ru.aston.customList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortTest {
    private final CustomArrayList<Integer> customList = new CustomArrayList<>();
    private final List<Integer> list = new ArrayList<>();
    private final int SIZE = 100;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < SIZE; i++) {
            int element = (int) (Math.random() * SIZE);
            customList.add(element);
            list.add(element);
        }
    }
    @Test
    void givenCustomListInteger_whenSortingAscending_thenSortAscending() {
        //given
        Collections.sort(list);
        //when
        CustomCollections.sort(customList);
        //then
        assertThat(list).usingRecursiveComparison().isEqualTo(customList);
    }
    @Test
    void givenCustomListInteger_whenSortingDescending_thenSortDescending() {
        //given
        list.sort(Collections.reverseOrder());
        //when
        CustomCollections.sort(customList, Collections.reverseOrder());
        //then
        assertThat(list).usingRecursiveComparison().isEqualTo(customList);
    }
}
