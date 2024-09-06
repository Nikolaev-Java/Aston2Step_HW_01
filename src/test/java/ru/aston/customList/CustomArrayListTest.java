package ru.aston.customList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomArrayListTest {
    private final CustomArrayList<Integer> list = new CustomArrayList<>();
    private final int SIZE = 100;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < SIZE; i++) {
            list.add((int) (Math.random() * SIZE));
        }
    }

    @Test
    @DisplayName("Add element to list functionality")
    void givenInteger_whenAddToList_thenCorrect() {
        //given
        CustomList<Integer> listExpected = new CustomArrayList<>();
        //when
        for (int i = 0; i < 1000; i++) {
            listExpected.add((int) (Math.random() * 1000));
        }
        //then
        assertThat(listExpected).size().isEqualTo(1000);
    }

    @Test
    @DisplayName("Create list by init capacity functionality")
    void givenInteger_whenCreateListByInitCapacity_thenCorrect() {
        //given
        CustomList<Integer> listExpected = new CustomArrayList<>(SIZE);
        //when
        for (int i = 0; i < SIZE; i++) {
            listExpected.add((int) (Math.random() * SIZE));
        }
        //then
        assertThat(listExpected).isNotEmpty().size().isEqualTo(SIZE);
    }

    @Test
    @DisplayName("Create list by init negative capacity functionality")
    void givenInteger_whenCreateListByInitNegativeCapacity_thenThrowsException() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new CustomArrayList<>(-1));
    }

    @Test
    @DisplayName("Add element to list by index functionality")
    void givenInteger_whenAddToListByIndex_thenCorrect() {
        //given
        int randomElement = (int) (Math.random() * 1000);
        int randomIndex = new Random().nextInt(list.size());
        int actualElement = list.get(randomIndex);
        //when
        list.add(randomIndex, randomElement);

        //then
        assertThat(list.get(randomIndex)).isEqualTo(randomElement);
        assertThat(list.get(randomIndex + 1)).isEqualTo(actualElement);
    }
    @Test
    @DisplayName("Add element to list by negative index functionality")
    void givenInteger_whenAddToListByNegativeIndex_thenCorrect() {
        //given
        int negativeIndex = -1;
        int randomElement = (int) (Math.random() * 100);
        //when
        //then
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(negativeIndex,randomElement));
    }
    @Test
    @DisplayName("Add element to list by out of bounds index functionality")
    void givenInteger_whenAddToListByOutOfBoundsIndex_thenCorrect() {
        //given
        int outOfBoundsIndex = SIZE+SIZE;
        int randomElement = (int) (Math.random() * 100);
        //when
        //then
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(outOfBoundsIndex,randomElement));
    }

    @Test
    @DisplayName("Remove by index element from list functionality")
    void givenInteger_whenRemoveByIdFromList_thenCorrect() {
        //given
        int compareElement = list.get(0);
        //when
        list.remove(0);
        //then
        assertThat(list.size()).isEqualTo(SIZE - 1);
        assertThat(compareElement).isNotEqualTo(list.get(0));
    }

    @Test
    @DisplayName("Get element by index from list functionality")
    void givenInteger_whenGetElement_thenCorrect() {
        //given
        int expectedElement = (int) (Math.random() * 10);
        CustomList<Integer> listExpected = new CustomArrayList<>();
        //when
        listExpected.add(expectedElement);
        //then
        assertThat(listExpected.get(0)).isEqualTo(expectedElement);
    }
    @Test
    @DisplayName("Replace element from list functionality")
    void givenInteger_whenReplaceElementInList_thenCorrect() {
        //given
        int expectedElement = 200;
        int indexForReplace = list.size()/2;
        int compareElement = list.get(indexForReplace);
        //when
        list.replace(indexForReplace, expectedElement);
        //then
        assertThat(list.get(indexForReplace)).isEqualTo(expectedElement);
        assertThat(compareElement).isNotEqualTo(expectedElement);
    }
    @Test
    @DisplayName("Remove by element from list functionality")
    void givenInteger_whenRemoveByElementFromList_thenCorrect() {
        //given
        int elementForRemove = 200;
        list.add(50,elementForRemove);
        //when
        list.remove((Integer) elementForRemove);
        //then
        assertThat(list).size().isEqualTo(SIZE);
        assertThat(list).doesNotContain(elementForRemove);
    }
    @Test
    @DisplayName("Remove by NULL from list functionality")
    void givenInteger_whenRemoveByElementNullFromList_thenThrowsException() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> list.remove( null));
    }
    @Test
    @DisplayName("Clear list functionality")
    void givenList_whenClearList_thenCorrect() {
        //given
        //when
        list.clear();
        //then
        assertThat(list).isEmpty();
    }
    @Test
    @DisplayName("Adding an element by index to the end of the list functionality")
    void givenObject_whenAddByIndexInLastPosition_thenCorrect() {
        //given
        CustomList<Object> objects = new CustomArrayList<>();
        //when
        for (int i = 0; i < 10000; i++) {
            objects.add(0, new Object());
        }
        //then
        assertThat(objects.size()).isEqualTo(10000);
    }
}