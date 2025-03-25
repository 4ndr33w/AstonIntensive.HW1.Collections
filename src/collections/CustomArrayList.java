package collections;

import java.util.*;

public class CustomArrayList<T> implements List<T> {


    private static final int DEFAULT_CAPACITY = 5;
    private T[] array;
    private int size = 0;

    public CustomArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }
    public CustomArrayList(int size) {
        array = (T[]) new Object[size];
    }
    public CustomArrayList(Collection<T> collection) {
        if(collection == null) {
            array = (T[]) new Object[DEFAULT_CAPACITY];
        }
        else {
            this.size = collection.size();
            array = (T[]) collection.toArray();
        }
    }
    public CustomArrayList(T[] array) {
        if(array.length < 1) {
            this.array = (T[]) new Object[10];
        }
        else {
            size = array.length;
            this.array = array;
        }

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        switch(this.size){
            case 0:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void clear() {
        size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentIndex = 0;
            private final int lastIndex = size;

            @Override
            public boolean hasNext() {
                return currentIndex < lastIndex;
            }

            @Override
            public T next() {
                if(hasNext()){
                    return array[currentIndex++];
                }
                else{
                    return null;
                }
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(array).toArray();
    }

    @Override
    public boolean add(T item) {
        if(size == array.length) {
            growArray();
        }
        array[size++] = item;
        return true;
    }

    private void growArray() {

        int newLength = array.length == 0 ? DEFAULT_CAPACITY : (array.length * 3) / 2 + 1;
        array = Arrays.copyOf(array, newLength);
    }

    @Override
    public void add(int index, T element) {

        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if(index < 0) {
            throw new IndexOutOfBoundsException("Index can not be negative");
        }

        if (array.length <= size + 1) {
            growArray();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        size++;
        array[index] = element;
    }

    @Override
    public T remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int shiftIndex = index + 1;

        if(shiftIndex < size) {
            System.arraycopy(array, shiftIndex, array, index, size - shiftIndex);
        }
        array[--size] = null;
        return array[index];
    }

    @Override
    public boolean remove(Object item) {

        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object item) {

        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    @Override
    public T get(int index) {

        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        else{
            T oldValue = array[index];
            array[index] = element;
            return oldValue;
        }
    }










    @Override
    public <T1> T1[] toArray(T1[] a) {

        return (T1[]) Arrays.stream(a).toArray();
    }





    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public int lastIndexOf(Object item) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}
