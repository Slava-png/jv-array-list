package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float INTERNAL_GROW = 1.5f;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void resizeArray() {
        if (array.length - 1 == size) {
            T[] resizedArray = (T[]) new Object[(int) (array.length * INTERNAL_GROW)];
            System.arraycopy(array, 0, resizedArray, 0, array.length);
            array = resizedArray;
        }
    }

    public void checkIndex(int index) {
        if (index < -1 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("There is no such " +
                         "element with index " + index);
        }
    }

    @Override
    public void add(T value) {
        resizeArray();

        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < -1 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element with index " + index);
        }
        resizeArray();

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size] = null;
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(element, array[i])) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("There is no such element in " +
                    "the arrayList with value " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
