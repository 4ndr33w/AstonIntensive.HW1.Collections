package tests.collections.sorts;

import collections.CustomArrayList;
import collections.CustomLinkedList;
import collections.interfaces.CustomList;
import collections.sorts.MergeSort;
import collections.sorts.QuickSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.collections.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Класс для тестирования алгоритма сортировки {@link QuickSort}
 * <p>
 *     Методы сортировки вызываются через интерфейс {@link CustomList}, а не обращаясь к классу {@link QuickSort}
 * </p>
 *
 * <p><b>Технические характеристики:</b>
 * <table border="1">
 *   <tr><td>Тестовые данные</td><td>Фиксированные</td></tr>
 *   <tr><td>Проверяемые сценарии</td>
 *       <td>
 * <ul>
 *   <li>Корректность сортировки для различных типов данных (числа, строки)</li>
 *   <li>Обработку граничных случаев (пустой список, один элемент)</li>
 *   <li>Поведение при недопустимых входных данных (null параметры)</li>
 * </ul>
 *       </td>
 *   </tr>
 *   <tr><td>Используемые assertion</td>
 *       <td>{@link org.junit.jupiter.api.Assertions}</td>
 *   </tr>
 * </table>

 * @version 1.0
 * @author 4ndr33w
 *
 * @see QuickSort
 * @see CustomList
 * @see CustomLinkedList
 * @see CustomArrayList
 */
public class QuickSortTests {

    static List<Object[]> customCollectionsOfSingleIntegerElement() {
        return TestUtils.customCollectionOfSingleIntegerElement();
    }

    static List<Object[]> customCollectionsOfUnsortedIntegers() {
        return TestUtils.customCollectionsOfIntegersImplementations();
    }

    static List<Object[]> customCollectionsOfStringsImplementations() {
        return TestUtils.customCollectionsOfStringsImplementations();
    }

    static List<Object[]> customEmptyCollectionsImplementations() {
        return TestUtils.customEmptyCollectionsImplementations();
    }

    @ParameterizedTest
    @MethodSource("customEmptyCollectionsImplementations")
    @DisplayName("check quick sort for empty lists")
    void quickSortShouldHandleEmptyList(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        Assertions.assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfSingleIntegerElement")
    @DisplayName("check quick sort for lists with single element")
    void quickSortShouldHandleSingleElement(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1, (int) result.get(0));
        Assertions.assertArrayEquals(new Integer[]{1}, result.toArray());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfUnsortedIntegers")
    @DisplayName("check quick sort for lists with unsorted Integers")
    void quickSortShouldSortIntegers(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        Assertions.assertArrayEquals(TestUtils.sortedIntegerArray, result.toArray());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfStringsImplementations")
    @DisplayName("check quick sort for lists with unsorted Strings")
    void quickSortShouldSortStrings(CustomList<String> list) {
        CustomList<String> result = list.sort();
        assertArrayEquals(TestUtils.sortedStringArray, result.toArray());
    }

    @Test
    @DisplayName("check quick sort when collection is null")
    void quickSortShouldThrowNPEForNullList() {
        CustomList<String> list = null;
        assertThrows(NullPointerException.class, () ->  list.sort());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfStringsImplementations")
    @DisplayName("check quick sort when comparator is null")
    void quickSortShouldThrowNPEForNullComparator(CustomList<String> list) {
        assertThrows(NullPointerException.class, () -> list.sort(null));
    }
}
