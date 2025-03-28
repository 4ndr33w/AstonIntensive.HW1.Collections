package collections;

import java.io.Serializable;
import java.util.*;

import collections.interfaces.CustomList;

/**
 * Класс CustomLinkedList представляет собой кастомную реализацию связанного списка.
 *
 * @param <T> тип элементов, которые будут храниться в списке
 */
public class CustomLinkedList<T> implements CustomList<T>, Serializable {

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
     * @param item добавляемый элемент
     * @return true (согласно спецификации {@link Collection#add})
     */
    @Override
    public boolean add(T item) {
        LinkedListNode<T> node = new LinkedListNode<>(item);

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

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if(element == null) {
            throw new NullPointerException("Element cannot be null");
        }

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

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

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
     * Вставляет указанный элемент в начало списка.
     *
     * @param element элемент для добавления
     * @throws NullPointerException если указанный элемент равен null
     */
    @Override
    public void addFirst(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

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
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
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
     * Удаляет первое вхождение указанного элемента из списка, если оно присутствует.
     * Если список не содержит элемент, он остается без изменений.
     *
     * @param item элемент, который должен быть удален из списка, если присутствует
     * @return true, если список содержал указанный элемент
     * @throws NullPointerException если переданный в качестве параметра элемент равен null
     */
    @Override
    public boolean remove(Object item) {
        if (item == null) {
            throw new NullPointerException("Element cannot be null");
        }
        LinkedListNode<T> current = head;
        LinkedListNode<T> previous = null;

        while (current != null) {

            if (item.equals(current.getNode())) {

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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        LinkedListNode<T> current = getNode(index);
        T oldValue = current.getNode();

        current.setNode(element);

        return oldValue;
    }

    /**
     * Удаляет элемент, находящийся на указанной позиции в списке.
     * Сдвигает все последующие элементы влево (уменьшает их индексы на единицу).
     * Возвращает элемент, который был удален из списка.
     *
     * @param index индекс элемента, который должен быть удален
     * @return элемент, который находился на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона (index < 0 || index >= size)
     */
    @Override
    public T remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        LinkedListNode<T> previous = getNode(index - 1);
        LinkedListNode<T> current = previous.getNext();

        previous.setNext(current.getNext());

        size--;
        T removedData = current.getNode();
        current.setNode(null);
        current.setNext(null);

        return removedData;
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
            throw new NoSuchElementException();
        }

        T removedData = head.getNode();
        head = head.getNext();

        if (head == null) {
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
            throw new NoSuchElementException();
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
     * {@code Objects.equals(o, get(i))},
     * или -1, если такого индекса не существует.
     *
     * @param o элемент для поиска
     * @return индекс первого вхождения элемента,
     *         или -1, если элемент не найден
     * @throws NullPointerException если переданный в качестве параметра элемент равен null
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            throw new NullPointerException("Element cannot be null");
        }

        LinkedListNode<T> current = head;
        int index = 0;

        while (current != null) {
            if (o.equals(current.getNode())) {
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
     * хотя бы один элемент e такой, что (o==null ? e==null : o.equals(e)).
     *
     * @param o элемент, наличие которого в списке нужно проверить
     * @return true, если список содержит указанный элемент
     * @throws NullPointerException если переданный в качестве параметра элемент равен null
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Element cannot be null");
        }
        LinkedListNode<T> current = head;

        while (current != null) {
            if (o.equals(current.getNode())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
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

    // следующие методы необходимы для реализации интерфейса Collection,
    // но задание не предполагает их реализации
    // в CustomArrayList я их реализовал
    // Реализацию методов в этом классе сделаю по необходимости позже
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
    public Iterator<T> iterator() {
        return null;
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
