package collections.interfaces;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;


/**
 * Интерфейс CustomList представляет собой упорядоченную коллекцию элементов,
 * которая позволяет хранить дубликаты и обеспечивает доступ к элементам по индексу.
 * <p>
 * Этот интерфейс расширяет {@link Iterable} и добавляет функциональность, специфичную
 * для упорядоченных коллекций, такую как управление элементами по индексу и
 * модификация списка с сохранением порядка.
 * </p>
 * <p>
 *      Так же интерфейс  позволяет получать быстрый доступ к
 *      элементам коллекции с использованием индексов (возможности дополнены интерфейсом {@link RandomAccess})
 * </p>
 * @param <T> тип элементов, которые может содержать список
 * @see Iterable
 * @see RandomAccess
 */
public interface CustomList<T> extends Iterable<T>, RandomAccess {

    /**
     * Возвращает количество элементов  в коллекции.
     *
     * @return количество элементов в коллекции
     */
    int size();

    /**
     * Возвращает true, если коллекция не содержит элементов.
     *
     * @return true, если коллекция не содержит элементов
     */
    boolean isEmpty();

    /**
     * Добавляет новый элемент в конец коллекции.
     *
     * @param item добавляемый элемент
     * @return true (согласно спецификации {@link Collection#add})
     * @throws NullPointerException если {@code item} равен {@code null}
     */
    boolean add(T item);

    /**
     * Вставляет указанный элемент в заданную позицию коллекции.
     *
     * @param index позиция, на которую должен быть вставлен указанный элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если {@code index} выходит за пределы допустимого диапазона
     * <p>
     *         (index < 0 || index >= size)
     * </p>
     * @throws NullPointerException если {@code element} равен {@code null}
     */
    void add(int index, T element);

    /**
     * Возвращает элемент, который находится по указанной позиции в коллекции.
     *
     * @param index позиция элемента, который должен быть возвращен
     * @return элемент в указанной позиции в коллекции
     * @throws IndexOutOfBoundsException в случае, если {@code index} выходит за пределы допустимого диапазона
     * <p>
     *     (index < 0 || index >= size)
     * </p>
     */
    T get(int index);

    /**
     * Заменяет элемент, который находится в указанной позиции на {@code element}, переданный в качестве параметра.
     *<p>
     * Возвращает объект, который ранее находился на указанной позиции в коллекции.
     *</p>
     *
     * @param index Позиция заменяемого элемента в колекции
     * @param element Новый элемент, который будет установлен на указанную позицию
     * @return объект, который ранее находился на указанной позиции в коллекции
     * @throws IndexOutOfBoundsException если {@code index} выходит за границы диапазона
     * <p>
     *         (index < 0 || index >= size)
     * </p>
     * @throws NullPointerException если {@code element} равен {@code null}
     */
    T set(int index, T element);

    /**
     * Удаляет первое вхождение указанного элемента из коллекции, если оно присутствует.
     * Если коллекция не содержит элемент, она остается без изменений.
     *
     * @param item элемент, который должен быть удален из коллекции, если присутствует
     * @return true, если коллекция содержала указанный элемент
     * @throws NullPointerException если {@code item} равен {@code null}
     */
    boolean remove(Object item);

    /**
     * Удаляет элемент, находящийся на указанной позиции в коллекции.
     * <p>
     * Возвращает элемент, который был удален из списка.
     * </p>
     * @param index позиция элемента, который нужно удалить
     * @return элемент, который находился на указанной позиции
     * @throws IndexOutOfBoundsException если {@code index} выходит за пределы допустимого диапазона
     * <p>
     *      (index < 0 || index >= size)
     * </p>
     */
    T remove(int index);

    /**
     * очищает колекцию
     */
    void clear();

    default CustomList<T> sort() {

        return collections.sorts.QuickSort.sort(this, (Comparator<? super T>) Comparator.naturalOrder());
    }

    default CustomList<T> sort(Comparator<? super T> comparator) {

        return collections.sorts.QuickSort.sort(this, comparator);
    }

    default CustomList<T> mergeSort() {
        return collections.sorts.MergeSort.sort(this, (Comparator<? super T>) Comparator.naturalOrder());
    }

    default CustomList<T> mergeSort(Comparator<? super T> comparator) {
        return collections.sorts.MergeSort.sort(this, comparator);
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
     * Вставляет указанный элемент в начало коллекции.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если добавляемый {@code element} равен {@code null}
     */
    void addFirst(T element);

    /**
     * Вставляет указанный элемент в конец коллекции.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если добавляемый {@code element} равен {@code null}
     */
    void addLast(T element);

    /**
     * Удаляет первый элемент коллекции
     * и возврашает значение удаленного элемента.
     *
     * @return удалённый элемент, который находился в начале коллекции
     * @throws NoSuchElementException если коллекция пуста
     * @see #isEmpty()
     */
    T removeFirst();

    /**
     * Удаляет последний элемент из коллекции
     * и возврашает значение удаленного элемента.
     *
     * @return удалённый элемент, который находился в конце коллекции
     * @throws NoSuchElementException если коллекция пуста
     * @see #isEmpty()
     */
    T removeLast();

    /**
     * Возвращает номер позиции первого вхождения указанного элемента коллекции,
     * или -1, если элемент в коллекции не найден.
     *
     * @param element элемент для поиска
     * @return индекс первого вхождения элемента,
     *         или -1, если элемент не найден
     * @throws NullPointerException если искомый {@code element} равен {@code null}
     */
    int indexOf(Object element);

    /**
     * Возвращает true, если коллекция содержит указанный элемент.
     * Более формально, возвращает true тогда и только тогда, когда коллекция содержит
     * хотя бы один элемент e такой, что (element==null ? e==null : element.equals(e)).
     *
     * @param element элемент, наличие которого в коллекции нужно проверить
     * @return true, если коллекция содержит указанный элемент
     * @throws NullPointerException если указанный {@code element} равен {@code null}
     */
    boolean contains(Object element);
}
