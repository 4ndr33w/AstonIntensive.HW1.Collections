package collections;

import java.io.Serializable;
import java.util.*;

import collections.interfaces.CustomList;

/**
 * Реализация упорядоченной коллекции на основе динамического массива.
 * <p>
 * CustomArrayList представляет собой структуру данных, которая хранит элементы
 * в порядке их добавления и обеспечивает быстрый доступ к элементам по индексу.
 * </p>
 * <p>
 * Основные характеристики:
 * - Позволяет хранить дубликаты элементов
 * - Обеспечивает произвольный доступ O(1) по индексу
 * - Автоматически расширяет внутренний массив при необходимости
 * </p>
 * @param <T> тип хранимых элементов
 * @see CustomList
 * @see Iterable
 */
public class CustomArrayList<T> implements CustomList<T>, Serializable {

    /**
     * Начальная емкость массива по умолчанию
     */
    transient static final int DEFAULT_CAPACITY = 5;

    /**
     * Массив для хранения элементов списка элементов типа Т
     */
    private T[] array;

    /**
     * Текущий размер списка (количество элементов)
     */
    private int size = 0;

    /**
     * Создает новый пустой список с начальной емкостью по умолчанию
     */
    public CustomArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Создает пустой список с указанной начальной емкостью.
     *
     * @param initialCapacity начальная емкость списка
     * если начальная емкость меньше или равна нулю,
     * будет использована начальная емкость по умолчанию.
     */
    public CustomArrayList(int initialCapacity) {
        if(initialCapacity <= 0) {
            this.array = (T[]) new Object[DEFAULT_CAPACITY];
        }
        else {
            this.array = (T[]) new Object[initialCapacity];
        }
        this.size = 0;
    }

    /**
     * Создает список, содержащий элементы указанной коллекции,
     * в том порядке, в котором они возвращаются итератором коллекции.
     * <p>
     * Создаёт новый список с начальной ёмкостью по умолчанию, если в коллекции нету элементов.
     *
     * @param collection коллекция, чьи элементы должны быть помещены в этот список
     */
    public CustomArrayList(Collection<? extends T> collection) {
        if (collection == null || collection.size() == 0) {
            this.array = (T[]) new Object[DEFAULT_CAPACITY];
            size = 0;
        }
        else  {
            Object[] elements = collection.toArray();
            array = (T[]) Arrays.copyOf(elements, size, Object[].class);
            size = elements.length;
        }
    }

    /**
     * Создает список, содержащий элементы указанного массива.
     * <p>
     * Создаёт новый список с начальной ёмкостью по умолчанию, если в коллекции нету элементов.
     *
     * @param array массив, чьи элементы должны быть помещены в этот список
     */
    public CustomArrayList(T[] array) {
        if (array == null || array.length == 0) {
            this.array = (T[]) new Object[DEFAULT_CAPACITY];
            this.size = 0;
        }
        else {
            this.size = array.length;
            this.array = Arrays.copyOf(array, array.length);
        }
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов в списке
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Возвращает true, если список не содержит элементов.
     *
     * @return true, если этот список не содержит элементов
     */
    @Override
    public boolean isEmpty() {
        switch (this.size){
            case 0:
                return true;
            default:
                return false;
        }
    }

    /**
     * Добавляет указанный элемент в конец списка.
     *
     * @param item элемент, который нужно добавить в конец списка
     * @return true (согласно спецификации {@link Collection#add})
     */
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

    private void growArray(int minRequiredSize) {

        int newLength = (minRequiredSize * 3) / 2 + 1;
        array = Arrays.copyOf(array, newLength);
    }

    /**
     * Вставляет указанный элемент на требуемую позицию в списке.
     * <p>
     * При необходимости увеличивает емкость списка (вызвав метод {@link #growArray()})
     * </p>
     *
     * @param index индекс, по которому должен быть вставлен указанный элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы (index < 0 || index > size())
     * @throws NullPointerException если {@link @param element} null
     */
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        Objects.requireNonNull(element, "Element cannot be null");

        if (size == array.length) {
            growArray();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Возвращает элемент в указанной позиции в этом списке.
     *
     * @param index индекс возвращаемого элемента
     * @return элемент в указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     *         (index < 0 || index >= size)
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке на указанный элемент.
     *
     * @param index индекс заменяемого элемента
     * @param element элемент, который будет сохранен в указанной позиции
     * @return предыдущий элемент в указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за границы диапазона
     *         (index < 0 || index >= size)
     */
    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldValue = (T) array[index];
        array[index] = element;
        return oldValue;
    }

    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если оно присутствует.
     *
     * @param element элемент, который нужно удалить из этого списка, если он присутствует
     * @return true, если этот список содержал указанный элемент
     */
    @Override
    public boolean remove(Object element) {
        Objects.requireNonNull(element, "Element cannot be null");
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет элемент по указанному индексу в списке.
     *
     * @param index индекс элемента, который нужно удалить
     * @return элемент, который был удален из списка
     * @throws IndexOutOfBoundsException если индекс выходит за пределы (index < 0 || index >= size())
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);

        T removedElement = array[index];
        int shiftIndex = index + 1;

        if(shiftIndex < size) {
            System.arraycopy(array, shiftIndex, array, index, size - shiftIndex);
        }
        array[--size] = null;
        return removedElement;
    }

    /**
     * обнуляет размер списка и создаёт пустой массив дефолтного размера.
     */
    @Override
    public void clear() {
        size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    //------------------------------------------------------------------------
    // Изначально я наследовал CustomList<T> от интерфейса Collection,
    // по этому начал реализовывать остальные его методы.
    // Ниже те методы, работу которых успел реализовать
    // перед тем, как решил отказаться от этой идем
    // и просто унаследоваться от Iterable<T>
    // для перебора коллекций в цикле foreach.
    //------------------------------------------------------------------------

    /**
     * Вставляет указанный элемент в начало списка.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если указанный элемент равен null
     */
    @Override
    public void addFirst(T element) {
        Objects.requireNonNull(element, "Element cannot be null");

        if (size == array.length) {
            growArray();
        }

        if (size > 0) {
            System.arraycopy(array, 0, array, 1, size);
        }

        array[0] = element;
        size++;
    }

    /**
     * Вставляет указанный элемент в конец списка.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если добавляемый {@link @param element} равен null
     */
    @Override
    public void addLast(T element) {
        Objects.requireNonNull(element, "Element cannot be null");
        this.add(element);
    }

    /**
     * Возвращает итератор по элементам этого списка в правильной последовательности.
     *
     * @return итератор по элементам этого списка в правильной последовательности
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[currentIndex++];
            }
        };
    }

    /**
     * Возвращает индекс первой вхождения указанного элемента в этом списке,
     * или -1, если этот список не содержит элемента.
     *
     * @param element элемент для поиска
     * @return i - индекс первого вхождения указанного элемента,
     *         или -1, если этот список не содержит элемента
     */
    @Override
    public int indexOf(Object element) {
        Objects.requireNonNull(element, "Element cannot be null");

        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Возвращает true, если этот список содержит указанный элемент.
     * Более формально, возвращает true тогда и только тогда, когда этот список содержит
     * хотя бы один элемент e такой, что Objects.equals(item, e).
     *
     * @param element элемент, наличие которого в этом списке нужно проверить
     * @return true, если этот список содержит указанный элемент
     * @throws NullPointerException если указанный элемент null
     *         и этот список не допускает null элементы (опционально)
     */
    @Override
    public boolean contains(Object element) {
        Objects.requireNonNull(element, "Element cannot be null");
        return indexOf(element) != -1;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - array.length > 0) {
            growArray(minCapacity);
        }
    }

    /**
     * Удаляет и возвращает первый элемент из списка.
     * <p>
     *     Реализация этого метода не входила в задание, но переорпделение
     *     этого метода обязательно для имплементации интерфейса {@link @param Collection}.
     *     Поэтому в данном метде вызывается метод {@link #remove(int)} с параметром 0.
     *     </p>
     * @return первый элемент списка
     * @throws NoSuchElementException если список пуст
     * @see #isEmpty()
     * @see #remove(int)
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from empty list");
        }
        T removedElement = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        array[--size] = null; // Помогаем GC
        return removedElement;
    }

    /**
     * Удаляет и возвращает последний элемент из списка.
     * <p>
     *     Реализация этого метода не входила в задание, но переорпделение
     *     этого метода обязательно для имплементации интерфейса {@link @param Collection}.
     *     Поэтому в данном метде вызывается метод {@link #remove(int)} с параметром size - 1.
     *     </p>
     * @return последний элемент списка
     * @throws NoSuchElementException если список пуст
     * @see #isEmpty()
     * @see #remove(int)
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        return remove(size - 1);
    }

}
