package collections.sorts;

import collections.interfaces.CustomList;

import java.util.Comparator;
import java.util.Objects;
import java.util.RandomAccess;

//Comparator<? super T> comparator
/**
 * Реализация алгоритма быстрой сортировки (QuickSort) для пользовательских списков.
 * <p>В интерфейсе {@link CustomList} реализованы дефолтные методы: {@link CustomList#sort()} и {@link CustomList#sort(Comparator comparator)}, вызывающие  метод сортировки данного класса</T></p>
 * <p>По этому нет необходимости вызывать этот класс, а сразу вызывать метод сортировки из класса, реализующего интерфейс {@link CustomList}</p>
 *
 * <p>Особенности реализации:
 * <ul>
 *   <li>Опорный элемент (pivot) выбирается как последний элемент подмассива</li>
 *   <li>Алгоритм не является устойчивым (не сохраняет порядок равных элементов)</li>
 * </ul>
 *
 * <p>Пример использования:
 * <pre>{@code
 * CustomList<String> list = new CustomArrayList<>();
 * list.add("banana");
 * list.add("apple");
 * list.add("orange");
 *
 * CustomList<String> sorted = list.sort(String::compareTo);
 * }</pre>
 *
 * @param <T> тип элементов в сортируемом списке
 * @see CustomList
 * @see Comparator
 * @see RandomAccess
 */
public class QuickSort<T> {

    /**
     * Сортирует список с использованием алгоритма быстрой сортировки.
     *
     * @param <T> тип элементов списка
     * @param list сортируемый список (должен поддерживать доступ по индексу)
     * @param comparator компаратор для сравнения элементов
     * @return отсортированный список
     * @throws NullPointerException если {@code list} или {@code comparator} равны {@code null}
     */
    public static <T> CustomList<T> sort(CustomList<T> list, Comparator<? super T> comparator) {
        Objects.requireNonNull(list , "List cannot be null");
        Objects.requireNonNull(comparator , "comparator cannot be null");

        quickSort(list, 0, list.size() - 1, comparator);
        return list;
    }

    private static <T> void quickSort(CustomList<T> list, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int partitionIndex = partition(list, low, high, comparator);
            quickSort(list, low, partitionIndex - 1, comparator);
            quickSort(list, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(CustomList<T> list, int low, int high, Comparator<? super T> comparator) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(CustomList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
