package collections;

import java.io.Serializable;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Класс CustomArrayList представляет собой реализацию интерфейса List на основе массива.
 *
 * @param <T> тип элементов, которые будут храниться в коллекции
 */
public class CustomArrayList<T> implements List<T>, Serializable {

    /**
     * Начальная емкость массива по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 5;

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
     * обнуляет размер списка и создаёт пустой массив дефолтного размера.
     */
    @Override
    public void clear() {
        size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
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
     */
    @Override
    public void add(int index, T element) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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

    /**
     * Удаляет элемент по указанному индексу в списке.
     *
     * @param index индекс элемента, который нужно удалить
     * @return элемент, который был удален из списка
     * @throws IndexOutOfBoundsException если индекс выходит за пределы (index < 0 || index >= size())
     */
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

    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если оно присутствует.
     *
     * @param item элемент, который нужно удалить из этого списка, если он присутствует
     * @return true, если этот список содержал указанный элемент
     */
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

        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T oldValue = (T) array[index];
        array[index] = element;
        return oldValue;
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
     * Возвращает массив, содержащий все элементы этого списка в правильной последовательности.
     *
     * @return массив, содержащий все элементы этого списка
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    /**
     * Возвращает индекс первой вхождения указанного элемента в этом списке,
     * или -1, если этот список не содержит элемента.
     *
     * @param item элемент для поиска
     * @return i - индекс первого вхождения указанного элемента,
     *         или -1, если этот список не содержит элемента
     */
    @Override
    public int indexOf(Object item) {

        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
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
     * @param item элемент, наличие которого в этом списке нужно проверить
     * @return true, если этот список содержит указанный элемент
     * @throws NullPointerException если указанный элемент null
     *         и этот список не допускает null элементы (опционально)
     */
    @Override
    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    /**
     * Возвращает массив, содержащий все элементы этого списка в правильной последовательности;
     * тип возвращаемого массива во время выполнения совпадает с типом указанного массива.
     *
     * @param <T1> тип компонентов массива, который будет содержать коллекцию
     * @param incArray массив, в который будут сохранены элементы этого списка,
     *        если он достаточно большой; в противном случае выделяется новый массив того же типа
     * @return массив, содержащий элементы этого списка
     * @throws NullPointerException если указанный массив null
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] incArray) {
        if (incArray == null) {
            throw new NullPointerException("Target array must not be null");
        }

        if (incArray.length < size) {
            return (T1[]) Arrays.copyOf(array, size, incArray.getClass());
        }

        System.arraycopy(array, 0, incArray, 0, size);

        if (incArray.length > size) {
            incArray[size] = null;
        }

        return incArray;
    }

    /**
     * Возвращает true, если этот список содержит все элементы указанной коллекции.
     *
     * @param collection коллекция, наличие которой будет проверяться в этом списке
     * @return true, если этот список содержит все элементы указанной коллекции
     * @throws NullPointerException если указанная коллекция null
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }

        if (collection.isEmpty()) {
            return true;
        }

        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавляет все элементы из указанной коллекции в конец этого списка.
     *
     * @param collection коллекция, содержащая элементы, которые будут добавлены в этот список
     * @return true, если этот список изменился в результате вызова
     * @throws NullPointerException если указанная коллекция null
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        Object[] incCollection = collection.toArray();
        int incCollectionSize = incCollection.length;

        ensureCapacity(size + incCollectionSize);

        System.arraycopy(incCollection, 0, array, size, incCollectionSize);
        size += incCollectionSize;

        return incCollectionSize > 0;
    }

    /**
     * Вставляет все элементы из указанной коллекции в этот список в указанной позиции.
     *
     * @param index индекс, по которому будет вставлен первый элемент из указанной коллекции
     * @param collection коллекция, содержащая элементы, которые будут добавлены в этот список
     * @return true, если этот список изменился в результате вызова
     * @throws IndexOutOfBoundsException если индекс выходит за пределы (index < 0 || index >= size())
     * @throws NullPointerException если указанная коллекция null
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Object[] elementsToAdd = collection.toArray();
        int numNew = elementsToAdd.length;
        if (numNew == 0) {
            return false;
        }

        ensureCapacity(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(array, index, array, index + numNew, numMoved);
        }

        System.arraycopy(elementsToAdd, 0, array, index, numNew);
        size += numNew;

        return true;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - array.length > 0) {
            growArray(minCapacity);
        }
    }

    /**
     * Удаляет из этого списка все его элементы, которые содержатся в указанной коллекции.
     *
     * @param c коллекция, содержащая элементы, которые должны быть удалены из этого списка
     * @return true, если этот список изменился в результате вызова
     * @throws NullPointerException если указанная коллекция null
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }

        boolean modified = false;
        final Object[] tempArray = array;
        int i = 0;

        for (; i < size; i++) {
            if (c.contains(tempArray[i])) {
                modified = true;
                break;
            }
        }

        if (modified) {
            int newSize = i;

            for (i++; i < size; i++) {
                if (!c.contains(tempArray[i])) {
                    tempArray[newSize++] = tempArray[i];
                }
            }

            for (int k = newSize; k < size; k++) {
                tempArray[k] = null;
            }

            size = newSize;
        }

        return modified;
    }

    /**
     * Оставляет в этом списке только те элементы, которые содержатся в указанной коллекции.
     * Другими словами, удаляет из этого списка все его элементы, которых нет в указанной коллекции.
     *
     * @param c коллекция, содержащая элементы, которые должны быть сохранены в этом списке
     * @return true, если этот список изменился в результате вызова
     * @throws NullPointerException если указанная коллекция null
     * @throws ClassCastException если типы элементов несовместимы
     * @throws NullPointerException если этот список содержит null, а коллекция не допускает null
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection must not be null");
        }

        boolean modified = false;
        int newSize = 0;

        for (int i = 0; i < size; i++) {
            if (c.contains(array[i])) {
                if (newSize != i) {
                    array[newSize] = array[i];
                }
                newSize++;
            } else {
                modified = true;
            }
        }

        if (modified) {
            for (int i = newSize; i < size; i++) {
                array[i] = null;
            }
            size = newSize;
        }

        return modified;
    }

    /**
     * Возвращает индекс последнего вхождения указанного элемента в этом списке
     * или -1, если элемент не содержится в списке.
     * Более формально, возвращает максимальный индекс {@code i}, такой что
     * {@code Objects.equals(o, get(i))},
     * или -1, если такого индекса не существует.
     *
     * @param item элемент, который нужно найти
     * @return индекс последнего вхождения элемента или -1, если элемент не найден
     */
    @Override
    public int lastIndexOf(Object item) {
        if (item == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (item.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Возвращает список итераторов элементов в этом списке в правильной последовательности.
     * <p>
     *     Реализация этого метода не входила в задание, но переорпделение
     *     этого метода обязательно для имплементации интерфейса List.
     *     Поэтому в данном метде вызывается метод listIterator() -без параметра- из класса ArrayList.
     *     </p>
     * @return итератор списка, который можно использовать для обхода элементов в обоих направлениях,
     *         добавления, удаления и изменения элементов во время итерации.
     */
    @Override
    public ListIterator<T> listIterator() {
        List<T> list = new ArrayList<>(List.of(array));

        return list.listIterator();
    }

    /**
     * Возвращает итератор списка элементов в этом списке в правильной последовательности, начиная с указанного индекса.
     * <p>
     *     Реализация этого метода не входила в задание, но переорпделение
     *     этого метода обязательно для имплементации интерфейса List.
     *     Поэтому в данном метде вызывается метод listIterator(int index) -с параметром int index- из класса ArrayList.
     *     </p>
     * @param index
     *          Индекс, с которого должен начинаться итератор (0 для первого элемента).
     *
     * @return итератор списка, который можно использовать для обхода элементов в обоих направлениях,
     *         начиная с указанного индекса, а также для добавления, удаления и изменения элементов во время итерации.
     *
     * @throws IndexOutOfBoundsException
     *          если index < 0 || index >= size()
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        List<T> list = new ArrayList<>(List.of(array));
        return list.listIterator(index);
    }

    /**
     * Возвращает представление части этого списка между указанными индексами.
     *
     * <p>
     *     Реализация этого метода не входила в задание, но переорпделение
     *     этого метода обязательно для имплементации интерфейса List.
     *     Поэтому в данном метде вызывается метод subList() из класса ArrayList.
     *     </p>
     * @param fromIndex
     * Индекс начала подсписка (включительно).
     *
     * @param toIndex
     * Индекс конца подсписка (исключительно).
     *
     * @return представление части этого списка между fromIndex (включительно) и toIndex (исключительно).
     *
     * @throws IndexOutOfBoundsException
     * если fromIndex < 0 || toIndex > size() || fromIndex >= toIndex
     *
     * @throws IllegalArgumentException
     * если fromIndex > toIndex
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> list = new ArrayList<>(List.of(array));
        return list.subList(fromIndex, toIndex);
    }
}
