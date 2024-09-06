package ru.aston.customList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the CustomList custom interface. Custom ArrayList collection.
 * Methods for adding, getting and removing an element are implemented. The collection is based on an array.
 * Capacity is the size of the array. The size of the array is always not less than the size of the list.
 * As elements are added, the capacity automatically increases.
 * The ability to iterate through the list using a for-each loop is implemented.
 *
 * @param <T> - type of collection elements.
 */
public class CustomArrayList<T> implements CustomList<T> {
    /**
     * An array of type T to store data.
     */
    private T[] elementData;
    /**
     * The size of the list.
     */
    private int size;
    /**
     * The default size of the array.
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * A constructor for an empty list with a capacity other than the initial one.
     *
     * @param capacity - the value of the capacity is not more than Max Integer.
     * @throws IllegalArgumentException -  if the capacity is less than 0.
     */
    public CustomArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.elementData = (T[]) new Object[capacity];
    }

    /**
     * A constructor for an empty list with the initial capacity set to 10.
     */
    public CustomArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Adding an element to the end of the list. When adding, the list is checked for fullness,
     * and if necessary, the capacity is increased.
     *
     * @param elem - the element to be added.
     */
    @Override
    public void add(T elem) {
        checkFullness();
        elementData[size++] = elem;
    }

    /**
     * Adding an element by index within the size of the list. When adding to the list,
     * the correctness of the index and the fullness of the list are checked.
     *
     * @param index - the index at which the element needs to be added.
     * @param elem  -  the element to be added.
     * @throws IndexOutOfBoundsException -  if the index is incorrect. Less than 0 or greater than the size of the list.
     */
    @Override
    public void add(int index, T elem) {
        checkIndex(index);
        checkFullness();
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = elem;
        size++;
    }

    /**
     * Getting an item by index.
     *
     * @param index - the index of the desired element
     * @return an element of type T is returned if the index is correct.
     * @throws IndexOutOfBoundsException - it is thrown out if the index is not correct. Less than 0 or
     *                                   larger than the list size.
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    /**
     * Deleting an item by index. The elements are sequentially shifted from the index by -1 to the end of the list.
     *
     * @param index - the index of the item being deleted.
     * @throws IndexOutOfBoundsException - it is thrown out if the index is not correct. Less than 0 or
     *                                   larger than the list size.
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
            elementData[i + 1] = null;
        }
        size--;
    }

    /**
     * Deleting an element by a value other than Null. We find the index of the element
     * and use the remove(int index) method.
     *
     * @param elem - the item to delete.
     * @throws IllegalArgumentException it is thrown if the element is Null.
     */
    @Override
    public void remove(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException("Element to remove is null");
        }
        if (getIndex(elem) >= 0) {
            remove(getIndex(elem));
        }
    }

    /**
     * Replacing an element by index.
     *
     * @param index - the index to replace.
     * @param elem  - the element to replace.
     * @throws IndexOutOfBoundsException - it is thrown out if the index is not correct. Less than 0
     *                                   or larger than the list size.
     */
    @Override
    public void replace(int index, T elem) {
        checkIndex(index);
        elementData[index] = elem;
    }

    /**
     * Complete cleaning of the list. Return to the default capacity. The size is set to 0.
     */
    @Override
    public void clear() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Getting the size of the list.
     *
     * @return the size of the subscription.
     */
    @Override
    public int size() {
        return size;
    }

    private void increaseCapacity() {
        int newCapacity = elementData.length * 2;
        T[] newElementData = (T[]) new Object[newCapacity];
        for (int i = 0; i < elementData.length; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
    }

    private void checkFullness() {
        if (elementData.length - size == 1) {
            increaseCapacity();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("an impossible index value");
        }
    }

    private int getIndex(T elem) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomArrayListIterator();
    }

    private class CustomArrayListIterator implements Iterator<T> {
        int cursor = 0;
        int lastReturned = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            int current = cursor;
            if (current >= size) {
                throw new NoSuchElementException();
            }
            cursor = current + 1;
            lastReturned = current;
            return elementData[lastReturned];
        }
    }
}
