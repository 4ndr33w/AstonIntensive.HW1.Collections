package tests.collections.sorts;

import collections.interfaces.CustomList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.collections.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

import collections.sorts.MergeSort;
import collections.sorts.QuickSort;
import collections.CustomLinkedList;
import collections.CustomArrayList;

/**
 * Класс для тестирования алгоритма сортировки {@link MergeSort}
 * <p>
 *     Методы сортировки вызываются через интерфейс {@link CustomList}, а не обращаясь к классу {@link MergeSort}
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
 * @see MergeSort
 * @see CustomList
 * @see CustomLinkedList
 * @see CustomArrayList
 */
public class MergeSortTests {

    private static List<Object[]> customCollectionsOfSingleIntegerElement() {
        return TestUtils.customCollectionOfSingleIntegerElement();
    }

    private static List<Object[]> customCollectionsOfUnsortedIntegers()
    {
        return TestUtils.customCollectionsOfIntegersImplementations();
    }

    private static List<Object[]> collectionsOfUnsortedStringsImplementations() {
        return TestUtils.customCollectionsOfStringsImplementations();
    }

    private static List<Object[]> emptyCollectionsImplementations() {
        return TestUtils.customEmptyCollectionsImplementations();
    }

    @ParameterizedTest
    @MethodSource("emptyCollectionsImplementations")
    @DisplayName("check merge sort for empty lists")
    public void mergeSortShouldHandleEmptyList(CustomList<Integer> list) {
        var result = list.sort();
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfSingleIntegerElement")
    @DisplayName("check merge sort for lists with single element")
    public void mergeSortShouldHandleSingleElement(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1, (int) result.get(0));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfUnsortedIntegers")
    @DisplayName("check merge sort for lists with unsorted Integers elements")
    public void mergeSortShouldSortIntegers(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        Assertions.assertArrayEquals(TestUtils.sortedIntegerArray, result.toArray());
    }

    @ParameterizedTest
    @MethodSource("collectionsOfUnsortedStringsImplementations")
    @DisplayName("check merge sort for lists with unsorted Strings")
    public void mergeSortShouldSortStrings(CustomList<String> list) {
        CustomList<String> result = list.sort();
        Assertions.assertArrayEquals(TestUtils.sortedStringArray, result.toArray());
    }

    @Test
    @DisplayName("check merge sort when collection is null")
    void mergeSortShouldThrowNPEForNullList() {
        CustomList<String> list = null;
        assertThrows(NullPointerException.class, () -> list.sort());
    }

    @ParameterizedTest
    @MethodSource("collectionsOfUnsortedStringsImplementations")
    @DisplayName("check merge sort when comparator is null")
    void mergeSortShouldThrowNPEForNullComparator(CustomList<String> list) {
        assertThrows(NullPointerException.class, () -> list.sort(null));
    }
}
