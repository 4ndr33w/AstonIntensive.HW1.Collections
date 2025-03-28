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
 * @param <T> тип хранимых элементов
 * @see CustomList
 * @see Collection
 */
public class CustomLinkedList<T> implements CustomList<T>, Serializable {

    /**
     * Счетчик изменений для fail-fast поведения
     */
    private int modCount = 0; // Счетчик изменений для fail-fast поведения
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
        this();
        addAll(c);
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
     * <p>Метод создает новый узел с переданным значением и добавляет его в конец списка.
     * Если список пуст, новый узел становится как головой, так и хвостом списка.
     *
     * @param element добавляемый элемент
     * @return true (согласно спецификации {@link Collection#add})
     * @throws NullPointerException если {@link @param element} null
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
        return true;
    }

    /**
     * Вставляет указанный элемент в заданную позицию в списке.
     * Сдвигает элемент, который находится в указанной позиции (если он есть),
     * и все последующие элементы вправо (увеличивает их индексы на единицу).
     *
     * @param index индекс, по которому должен быть вставлен указанный элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     *         (index < 0 || index > size)
     * @throws NullPointerException если {@link @param element} null
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

        size++;
    }

    /**
     * Возвращает узел, который находится в указанной позиции в списке.
     *
     * @param index позиция узла, который должен быть возвращен
     * @return узел в указанной позиции в данном списке
     * @throws IndexOutOfBoundsException в случае, если индекс выходит за пределы допустимого диапазона (index < 0 || index >= size)
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
     * Возвращает элемент, который находится в указанной позиции в списке.
     *
     * @param index позиция элемента, который должен быть возвращен
     * @return элемент в указанной позиции в данном списке
     * @throws IndexOutOfBoundsException в случае, если индекс выходит за пределы допустимого диапазона (index < 0 || index >= size)
     */
    @Override
    public T get(int index) {

        return getNode(index).getNode();
    }

    /**
     * Заменяет элемент, который находится в указанной позиции в списке элементом, переданным в качестве параметра.
     * Возвращает объект, который ранее находился на указанной позиции в списке.
     *
     * @param index Позиция заменяемого элемента в списке
     * @param element Новый элемент, который будет установлен на указанную позицию
     * @return объект, который ранее находился на указанной позиции в списке
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона (index < 0 || index >= size)
     * @throws NullPointerException если новый элемент равен null
     */
    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        Objects.requireNonNull(element, "Element cannot be null");

        LinkedListNode<T> current = getNode(index);
        T oldValue = current.getNode();

        current.setNode(element);

        return oldValue;
    }

    /**
     * Удаляет первое вхождение указанного элемента из списка, если оно присутствует.
     * Если список не содержит элемент, он остается без изменений.
     *
     * @param element элемент, который должен быть удален из списка, если присутствует
     * @return true, если список содержал указанный элемент
     * @throws NullPointerException если переданный в качестве параметра элемент равен null
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
     * Сдвигает все последующие элементы влево (уменьшает их индексы на единицу).
     * Возвращает элемент, который был удален из списка.
     *
     * @param index индекс удаляемого элемента (0 ≤ index < size)
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона (index < 0 || index >= size)
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
        size--;

        return toRemove.getNode();
    }

    /**
     * обнуляет размер списка, головной и хвостовой элементы.
     */
    @Override
    public void clear() {

        size = 0;
        head = null;
        tail = null;
    }


    //------------------------------------------------------------------------
    // Далее идут методы, которые не входили в задание, но
    // необходимые для реализации интерфейса Collection
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

        LinkedListNode<T> newNode = new LinkedListNode<>(element);
        newNode.setNext(head);
        head = newNode;

        if (tail == null) {
            tail = head;
        }
        size++;
    }

    /**
     * Вставляет указанный элемент в конец списка.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если указанный элемент равен null
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
        size++;
    }

    /**
     * Удаляет и возвращает первый элемент списка.
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
        size--;
        return removedData;
    }

    /**
     * Удаляет и возвращает последний элемент списка.
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

        size--;
        return removedData;
    }

    /**
     * Возвращает индекс первого вхождения указанного элемента списке,
     * или -1, если элемент не содержится в списке.
     * Более формально, возвращает наименьший индекс {@code i} такой, что
     * {@code Objects.equals(element, get(i))},
     * или -1, если такого индекса не существует.
     *
     * @param element элемент для поиска
     * @return индекс первого вхождения элемента,
     *         или -1, если элемент не найден
     * @throws NullPointerException если переданный в качестве параметра элемент равен null
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
     * @throws NullPointerException если переданный в качестве параметра элемент равен null
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

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private LinkedListNode<T> current = head;
        private LinkedListNode<T> lastReturned = null;
        private int expectedModCount = modCount;
        private boolean canRemove = false;

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
            canRemove = true;
            return lastReturned.getNode();
        }

        @Override
        public void remove() {
            checkForComodification();
            if (!canRemove) {
                throw new IllegalStateException();
            }

            LinkedListNode<T> prev = lastReturned.getPrev();
            LinkedListNode<T> next = lastReturned.getNext();

            if (prev == null) {
                head = next;
            } else {
                prev.setNext(next);
            }

            if (next == null) {
                tail = prev;
            } else {
                next.setPrev(prev);
            }

            size--;
            modCount++;
            expectedModCount = modCount;
            canRemove = false;
        }

        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
        }
    }

    //------------------------------------------------------------------------
    // следующие методы необходимы для реализации интерфейса Collection,
    // но задание не предполагает их реализации
    // в CustomArrayList я их реализовал
    // Реализацию методов в этом классе сделаю по необходимости позже
    //------------------------------------------------------------------------


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
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
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
}
