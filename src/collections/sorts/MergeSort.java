package collections.sorts;

import collections.interfaces.CustomList;

import java.util.Comparator;
import java.util.Objects;

public class MergeSort {

    /**
     * Сортирует список с использованием алгоритма сортировки слиянием.
     *
     * @param <T> тип элементов списка
     * @param list сортируемый список (должен реализовывать RandomAccess)
     * @param comparator компаратор для сравнения элементов
     * @return отсортированный список
     * @throws NullPointerException если {@code list} или {@code comparator} равны {@code null}
     */
    public static <T> CustomList<T> sort(CustomList<T> list, Comparator<? super T> comparator) {
        Objects.requireNonNull(list , "List cannot be null");
        Objects.requireNonNull(comparator , "comparator cannot be null");

        if (list.size() <= 1) {
            return list;
        }

        T[] temp = (T[]) new Object[list.size()];
        mergeSort(list, 0, list.size() - 1, temp, comparator);
        return list;
    }

    private static <T> void mergeSort(CustomList<T> list, int left, int right, T[] temp, Comparator<? super T> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(list, left, mid, temp, comparator);
            mergeSort(list, mid + 1, right, temp, comparator);

            merge(list, left, mid, right, temp, comparator);
        }
    }

    private static <T> void merge(CustomList<T> list, int left, int mid, int right, T[] temp, Comparator<? super T> comparator) {
        for (int i = left; i <= right; i++) {
            temp[i] = list.get(i);
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (comparator.compare(temp[i], temp[j]) <= 0) {
                list.set(k++, temp[i++]);
            } else {
                list.set(k++, temp[j++]);
            }
        }

        while (i <= mid) {
            list.set(k++, temp[i++]);
        }
    }
}
