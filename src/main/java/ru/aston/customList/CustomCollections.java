package ru.aston.customList;

import java.util.Comparator;

/**
 * Utility class for CustomList. Contains methods for sorting.
 */
public class CustomCollections {
    private CustomCollections() {
    }

    /**
     * A method for sorting the CustomList collection. It is intended for sorting collections containing elements
     * that implement the interface is Comparable.
     *
     * @param list - a collection for sorting.
     * @param <T>  - the type of elements. There must be a Comparable implementation.
     */
    public static <T extends Comparable<? super T>> void sort(CustomList<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * A method for sorting the CustomList collection. To sort, you must provide an implementation of Comparator.
     *
     * @param list       - a collection for sorting.
     * @param comparator - the Comparator implementation.
     * @param <T>        - the type of elements.
     */
    public static <T> void sort(CustomList<T> list, Comparator<T> comparator) {
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private static <T extends Comparable<? super T>> void quickSort(CustomList<T> list, int leftIndex, int rightIndex) {
        if (list.size() == 0 || leftIndex >= rightIndex) return;
        T pivot = list.get((leftIndex + rightIndex) / 2);
        int leftMarker = leftIndex;
        int rightMarker = rightIndex;
        while (leftMarker <= rightMarker) {
            while (list.get(leftMarker).compareTo(pivot) < 0) leftMarker++;
            while (list.get(rightMarker).compareTo(pivot) > 0) rightMarker--;
            if (leftMarker <= rightMarker) {
                swap(list, leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        }
        if (leftIndex < rightMarker) quickSort(list, leftIndex, rightMarker);
        if (rightIndex > leftMarker) quickSort(list, leftMarker, rightIndex);
    }

    private static <T> void quickSort(CustomList<T> list, int leftIndex, int rightIndex, Comparator<T> comparator) {
        if (list.size() == 0 || leftIndex >= rightIndex) return;
        T pivot = list.get((leftIndex + rightIndex) / 2);
        int leftMarker = leftIndex;
        int rightMarker = rightIndex;
        while (leftMarker <= rightMarker) {
            while (comparator.compare(list.get(leftMarker), pivot) < 0) leftMarker++;
            while (comparator.compare(list.get(rightMarker), pivot) > 0) rightMarker--;
            if (leftMarker <= rightMarker) {
                swap(list, leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        }
        if (leftIndex < rightMarker) quickSort(list, leftIndex, rightMarker, comparator);
        if (rightIndex > leftMarker) quickSort(list, leftMarker, rightIndex, comparator);
    }

    private static <T> void swap(CustomList<T> list, int i, int j) {
        T temp = list.get(i);
        list.replace(i, list.get(j));
        list.replace(j, temp);
    }
}
