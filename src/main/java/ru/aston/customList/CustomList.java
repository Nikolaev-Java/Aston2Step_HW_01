package ru.aston.customList;

/**
 * Interface for a custom collection. For implementation, it contains the basic methods for working with the collection.
 *
 * @param <T> - тип коллеции.
 */
public interface CustomList<T> extends Iterable<T> {
    /**
     * Adding an item to the end of the collection.
     *
     * @param elem - the element to add.
     */
    void add(T elem);

    /**
     * Adding an element by index.
     *
     * @param index - the index to add.
     * @param elem  - element to add.
     */
    void add(int index, T elem);

    /**
     * Getting an item by index.
     *
     * @param index - the index of the element.
     * @return a type T element.
     */
    T get(int index);

    /**
     * Deleting an item by index.
     *
     * @param index - the index of the item being deleted.
     */
    void remove(int index);

    /**
     * Deleting an element by value.
     *
     * @param elem - the item to delete.
     */
    void remove(T elem);

    /**
     * Replacing an element by index.
     *
     * @param index - the index to replace.
     * @param elem  - the element to replace.
     */
    void replace(int index, T elem);

    /**
     * Cleaning the collection.
     */
    void clear();

    /**
     * Getting the size of the collection.
     *
     * @return the size of the collection.
     */
    int size();
}
