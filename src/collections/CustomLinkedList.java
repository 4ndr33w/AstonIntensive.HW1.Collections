package collections;

import java.io.Serializable;
import java.util.*;

import collections.interfaces.CustomList;

/**
 * Реализация упорядоченной коллекции на основе двусвязного списка.
 * <p>
 * CustomLinkedList представляет собой структуру данных, где элементы хранятся
 * в виде узлов, связанных ссылками друг с другом. Каждый узел содержит ссылку
 * на предыдущий и следующий узел, что обеспечивает эффективную вставку и удаление
 * элементов в любой позиции списка.
 * </p>
 * <p>
 * Основные характеристики:
 * - Позволяет хранить дубликаты элементов
 * - Обеспечивает O(1) сложность для вставки и удаления в начало и конец
 * - Имеет O(n) сложность для произвольного доступа
 * - Не требует предварительного выделения памяти
 * - Эффективно использует память при частых вставках/удалениях
 * </p>
 *
 * @version 1.0
 * @author 4ndr33w
 *
 * @param <T> тип хранимых элементов
 * @see CustomList
 * @see Iterable
 */
public class CustomLinkedList<T> implements CustomList<T>, Serializable {

    /**
     * Счетчик изменений для fail-fast поведения
     */
    transient int modCount = 0;
    /**
     * Текущий размер списка (количество элементов)
     */
    transient int size = 0;

    /**
     * Указатель на первый (головной) узел.
     */
    transient LinkedListNode<T> head;

    /**
     * Указатель на последний (хвостовой) узел.
     */
    transient LinkedListNode<T> tail;

    public CustomLinkedList() {
    }

    public CustomLinkedList(Collection<? extends T> c) {
        Objects.requireNonNull(c, "Input array cannot be null");

        for (T item : c) {
            add(item);
        }
    }

    public CustomLinkedList(T[] array) {
        Objects.requireNonNull(array, "Input array cannot be null");

        for (T item : array) {
            add(item);
        }
    }

    /**
     * Возвращает количество элементов списка.
     *
     * @return количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает true, если список не содержит элементов.
     *
     * @return true, если список не содержит элементов
     */
    @Override
    public boolean isEmpty() {
        switch(this.size){
            case 0:
                return true;
            default:
                return false;
        }
    }

    /**
     * Добавляет новый элемент в конец списка.
     *
     * <p>Метод создает новый узел с переданным значением {@code element} и добавляет его в конец списка.
     * Если список пуст, новый узел становится как головой, так и хвостом списка.
     *
     * @param element добавляемый элемент
     * @return true (согласно спецификации {@link Collection#add})
     * @throws NullPointerException если {@code element} равен {@code null}
     */
    @Override
    public boolean add(T element) {
        Objects.requireNonNull(element, "Element cannot be null");
        LinkedListNode<T> node = new LinkedListNode<>(element);

        if(head == null) {
            head = node;
            tail = node;
        }
        else {
            tail.setNext(node);
            tail = node;
        }
        size++;
        modCount++;
        return true;
    }

    /**
     * Вставляет указанный элемент в заданную позицию в списке.
     *
     * @param index позиция, на которую должен быть вставлен указанный элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если {@code index} выходит за пределы допустимого диапазона
     * @throws NullPointerException если {@code element} равен {@code null}
     */
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size);
        Objects.requireNonNull(element, "Element cannot be null");

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        LinkedListNode<T> previous = getNode(index - 1);

        LinkedListNode<T> newNode = new LinkedListNode<>(element);

        newNode.setNext(previous.getNext());
        previous.setNext(newNode);

        modCount++;
        size++;
    }

    /**
     * Возвращает узел, который находится в указанной позиции в списке.
     *
     * @param index позиция узла, который должен быть возвращен
     * @return узел в указанной позиции в данном списке
     * @throws IndexOutOfBoundsException в случае, если {@code index} выходит за пределы допустимого диапазона
     */
    private LinkedListNode<T> getNode(int index) {
        Objects.checkIndex(index, size);

        if(index == size - 1) {
            return tail;
        }

        LinkedListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    /**
     * Возвращает элемент, который находится по указанной позиции в списке.
     *
     * @param index позиция элемента, который должен быть возвращен
     * @return элемент в указанной позиции списке
     * @throws IndexOutOfBoundsException в случае, если {@code index} выходит за пределы допустимого диапазона
     */
    @Override
    public T get(int index) {

        return getNode(index).getNode();
    }

    /**
     * Заменяет элемент, который находится в узле на указанной позиции на новый {@code element}.
     * <p>
     * Возвращает объект, который ранее находился в узле на указанной позиции в списке.
     * </p>
     *
     * @param index Позиция заменяемого элемента в списке
     * @param element Новый элемент, который будет установлен в узле на указанной позиции
     * @return объект, который ранее находился в узле на указанной позиции
     * @throws IndexOutOfBoundsException если {@code index} выходит за границы диапазона
     * @throws NullPointerException если {@code element} равен {@code null}
     */
    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        Objects.requireNonNull(element, "Element cannot be null");

        LinkedListNode<T> current = getNode(index);
        T oldValue = current.getNode();

        if (!Objects.equals(oldValue, element)) {
            current.setNode(element);
            modCount++;
        }

        return oldValue;
    }

    /**
     * Удаляет первое вхождение указанного элемента из списка, если оно присутствует.
     * Если список не содержит элемент, он остается без изменений.
     *
     * @param element элемент, который должен быть удален из списка, если присутствует
     * @return true, если список содержал указанный элемент
     * @throws NullPointerException если {@code element} равен {@code null}
     */
    @Override
    public boolean remove(Object element) {
        Objects.requireNonNull(element, "Element cannot be null");

        LinkedListNode<T> current = head;
        LinkedListNode<T> previous = null;

        while (current != null) {

            if (element.equals(current.getNode())) {

                if (previous != null) {

                    previous.setNext(current.getNext());

                    if(current.getNext() == null){
                        tail = previous;
                    }
                }
                else {
                    head = head.getNext();

                    if(head == null){
                        tail = null;
                    }
                }
                modCount++;
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    /**
     * Удаляет элемент, находящийся на указанной позиции в списке.
     * Возвращает элемент, который был удален из списка.
     *
     * @param index позиция элемента, который нужно удалить
     * @return элемент, который находился на указанной позиции
     * @throws IndexOutOfBoundsException если {@code index} выходит за пределы допустимого диапазона
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        LinkedListNode<T> prev = getNode(index - 1);
        LinkedListNode<T> toRemove = prev.getNext();

        prev.setNext(toRemove.getNext());
        modCount++;
        size--;

        return toRemove.getNode();
    }

    /**
     * обнуляет размер списка, головной и хвостовой элементы.
     */
    @Override
    public void clear() {

        modCount++;
        size = 0;
        head = null;
        tail = null;
    }

    //------------------------------------------------------------------------
    // Изначально я начал расширял CustomList интерфейсом Collection,
    // по этому начал реализовывать остальные его методы.
    // Ниже те методы, работу которых успел реализовать
    // перед тем, как решил отказаться от этой идем
    // и просто унаследоваться от Iterable<T>
    //------------------------------------------------------------------------


    /**
     * Вставляет указанный элемент в начало списка.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если добавляемый {@code element} равен {@code null}
     */
    @Override
    public void addFirst(T element) {
        Objects.requireNonNull(element, "Element cannot be null");

        LinkedListNode<T> newNode = new LinkedListNode<>(element);
        newNode.setNext(head);
        head = newNode;

        if (tail == null) {
            tail = head;
        }
        modCount++;
        size++;
    }

    /**
     * Вставляет указанный элемент в конец списка.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если добавляемый {@code element} равен {@code null}
     */
    @Override
    public void addLast(T element) {
        Objects.requireNonNull(element, "Element cannot be null");

        LinkedListNode<T> newNode = new LinkedListNode<>(element);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        modCount++;
        size++;
    }

    /**
     * Удаляет первый элемент из списка
     * и возврашает значение удаленного элемента.
     *
     * @return первый элемент списка
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Cannot remove from empty list");
        }

        T removedData = head.getNode();
        head = head.getNext();

        if (head != null) {
            head.setPrev(null);
        } else {
            tail = null;
        }
        modCount++;
        size--;
        return removedData;
    }

    /**
     * Удаляет последний элемент из списка
     * и возврашает значение удаленного элемента.
     *
     * @return последний элемент из списка
     * @throws NoSuchElementException если список пуст
     */
    @Override
    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("Cannot remove from empty list");
        }

        if (size == 1) {
            return removeFirst();
        }

        LinkedListNode<T> previous = getNode(size - 2);
        T removedData = tail.getNode();

        tail = previous;
        tail.setNext(null);

        modCount++;
        size--;
        return removedData;
    }

    /**
     * Возвращает индекс первого вхождения указанного элемента списке,
     * или -1, если элемент не содержится в списке.
     *
     * @param element элемент для поиска
     * @return индекс первого вхождения элемента,
     *         или -1, если элемент не найден
     * @throws NullPointerException если искомый {@code element} равен {@code null}
     */
    @Override
    public int indexOf(Object element) {
        Objects.requireNonNull(element, "Element cannot be null");

        LinkedListNode<T> current = head;
        int index = 0;

        while (current != null) {
            if (element.equals(current.getNode())) {
                return index;
            }
            current = current.getNext();
            index++;
        }

        return -1;
    }

    /**
     * Возвращает true, если список содержит указанный элемент.
     * Более формально, возвращает true тогда и только тогда, когда список содержит
     * хотя бы один элемент e такой, что (element==null ? e==null : element.equals(e)).
     *
     * @param element элемент, наличие которого в списке нужно проверить
     * @return true, если список содержит указанный элемент
     * @throws NullPointerException если указанный {@code element} равен {@code null}
     */
    @Override
    public boolean contains(Object element) {
        Objects.requireNonNull(element, "Element cannot be null");

        LinkedListNode<T> current = head;

        while (current != null) {
            if (element.equals(current.getNode())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Возвращает итератор по элементам двусвязного списка.
     *
     * Итератор:
     * - Позволяет проходить по элементам в прямом порядке (в порядке добавления элементов)
     * - Защищен от параллельных модификаций
     *
     * @return итератор по элементам списка
     *
     * @throws ConcurrentModificationException если список был модифицирован
     *         после создания итератора
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private LinkedListNode<T> current = head;
        private LinkedListNode<T> lastReturned = null;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = current;
            current = current.getNext();
            return lastReturned.getNode();
        }

        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
        }
    }

    /**
     * Преобразует список в массив объектов
     *
     * @return массив, содержащий все элементы списка в правильном порядке
     * @throws NullPointerException если список содержит null-элементы
     */
    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        Object[] array = new Object[size];

        int index = 0;
        for (T item : this) {
            array[index++] = item;
        }
        return (T[]) array;
    }

}
