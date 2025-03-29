package tests.collections.sorts;

import collections.interfaces.CustomList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.collections.utils.TestUtils;

import java.util.List;

import static org.junit.Assert.*;

public class QuickSortTests {

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
    @DisplayName("check collection size. Expected successful result")
    void quickSortShouldHandleEmptyList(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfSingleIntegerElement")
    @DisplayName("check quick sort for lists with one element")
    void quickSortShouldHandleSingleElement(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        assertEquals(1, result.size());
        assertTrue(result.get(0).equals(1));
        assertArrayEquals(new Integer[]{1}, result.toArray());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfRandomIntegers")
    @DisplayName("check quick sort for lists with unsorted Integers elements")
    void quickSortShouldSortIntegers(CustomList<Integer> list) {
        CustomList<Integer> result = list.sort();
        assertArrayEquals(TestUtils.sortedIntegerArray, result.toArray());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfStringsImplementations")
    @DisplayName("check quick sort for lists with unsorted Strings elements")
    void quickSortShouldSortStrings(CustomList<String> list) {
        CustomList<String> result = list.sort();
        assertArrayEquals(TestUtils.sortedStringArray, result.toArray());
    }

    @Test
    void quickSortShouldThrowNPEForNullList() {
        CustomList<String> list = null;
        assertThrows(NullPointerException.class, () ->  list.sort());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsOfStringsImplementations")
    void quickSortShouldThrowNPEForNullComparator(CustomList<String> list) {
        assertThrows(NullPointerException.class, () -> list.sort(null));
    }
}
