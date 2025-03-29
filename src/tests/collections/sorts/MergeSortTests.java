package tests.collections.sorts;

import collections.CustomArrayList;
import collections.interfaces.CustomList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.collections.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class MergeSortTests {

    static List<Object[]> customCollectionsOfSingleIntegerElement() {
        return TestUtils.customCollectionOfSingleIntegerElement();
    }

    static List<Object[]> customCollectionsOfRandomIntegers() {
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
    @DisplayName("check merge sort for empty lists")
    public void mergeSortShouldHandleEmptyList(CustomList<Integer> list) {
        var result = list.sort();
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfSingleIntegerElement")
    @DisplayName("check merge sort for lists with one element")
    public void mergeSortShouldHandleSingleElement(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        assertEquals(1, result.size());
        assertTrue(result.get(0).equals(1));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfRandomIntegers")
    @DisplayName("check merge sort for lists with unsorted Integers elements")
    void mergeSortShouldSortIntegers(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        assertArrayEquals(TestUtils.sortedIntegerArray, result.toArray());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfStringsImplementations")
    @DisplayName("check merge sort for lists with unsorted Strings elements")
    void mergeSortShouldSortStrings(CustomList<String> list) {
        CustomList<String> result = list.sort();
        assertArrayEquals(TestUtils.sortedStringArray, result.toArray());
    }

    @Test
    void mergeSortShouldThrowNPEForNullList() {
        CustomList<String> list = null;
        assertThrows(NullPointerException.class, () -> list.sort());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfStringsImplementations")
    void mergeSortShouldThrowNPEForNullComparator(CustomList<String> list) {
        assertThrows(NullPointerException.class, () -> list.sort(null));
    }
}
