package tests.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;

import tests.collections.utils.TestUtils;
import collections.CustomArrayList;
import collections.CustomLinkedList;
import collections.interfaces.CustomList;

/**
 * Класс для тестирования реализации интерфейса CustomList.
 * <p>
 * Этот класс содержит набор тестовых методов, которые проверяют корректность
 * работы различных методов интерфейса CustomList на примерах реализации
 * CustomLinkedList и CustomArrayList.
 * </p>
 *
 * <table border="1">
 *   <tr><td>Основные функции</td>
 *       <td>
 * <ul>
 *   <li>Инициализация тестовых данных</li>
 *   <li>Проверка базовых операций с коллекциями</li>
 *   <li>Тестирование различных сценариев использования</li>
 * </ul>
 *       </td>
 *   </tr>
 *   <tr><td>Используемые assertion</td>
 *       <td>{@link org.junit.jupiter.api.Assertions}</td>
 *   </tr>
 * </table>
 *
 * @version 1.0
 * @author 4ndr33w
 *
 * @see CustomList
 * @see CustomLinkedList
 * @see CustomArrayList
 */
public class CustomListTests {

    private static CustomLinkedList<String> testLinkedList = new CustomLinkedList<>();
    private static CustomArrayList<String> testArrayList = new CustomArrayList<>();

    static List<Object[]> customCollectionsImplementations() {
        return List.of(
                new Object[] {testLinkedList},
                new Object[] {testArrayList}
        );
    }
    static List<Object[]> customEmptyCollectionsImplementations() {
        return TestUtils.customEmptyCollectionsImplementations();
    }

    @BeforeEach
    public void setUp() {
        testLinkedList = TestUtils.fillTestLinkedListByStrings();
        testArrayList = TestUtils.fillTestArrayListByStrings();
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check collection size. Expected successful result")
    public void sizeTestCorrect(CustomList<String> testList) {
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST, testList.size());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check collection size. Expected unsuccessful result")
    public void sizeTestIncorrect(CustomList<String> testList) {
        Assertions.assertNotEquals(0, testList.size());
    }

    @ParameterizedTest
    @MethodSource("customEmptyCollectionsImplementations")
    @DisplayName("check CustomLinkedList.isEmpty() method. Returns true")
    public void isEmpty_emptyCustomLinkedList_ReturnsTrue(CustomList<String> testList) {
        Assertions.assertTrue(testList.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check isEmpty method. Returns false")
    public void isEmptyTestReturnsFalse(CustomList<String> testList) {
        Assertions.assertFalse(testList.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check get method. Expected successful result")
    public void getTestReturnsTrue(CustomList<String> testList) {
        Assertions.assertEquals(TestUtils.FIRST_ELEMENT, testList.get(0));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check get method. Expected unsuccessful result")
    public void getTestReturnsFalse(CustomList<String> testList) {
        Assertions.assertNotEquals(TestUtils.ELEMENT_NOT_IN_LIST, testList.get(0));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check get method. Expected IndexOutOfBoundsException")
    public void getTestReturnsIndexOutOfBoundsException(CustomList<String> testList) {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.get(TestUtils.SIZE_OF_LIST));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check add method ( add(T item) ). Expected successful result")
    public void add_Item_TestReturnsTrue(CustomList<String> testList) {
        Assertions.assertTrue(testList.add(TestUtils.NEW_ELEMENT));
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST + 1, testList.size());
        Assertions.assertEquals(TestUtils.NEW_ELEMENT, testList.get(TestUtils.SIZE_OF_LIST));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check add method ( add(int index, T item) ) when 0 < index < list.size() . Expected successful result")
    public void add_ByIndex_TestReturnsTrue(CustomList<String> testList) {
        Assertions.assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(5));
        testList.add(5, TestUtils.NEW_ELEMENT);
        Assertions.assertEquals(TestUtils.NEW_ELEMENT, testList.get(5));
        Assertions.assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(6));
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST + 1, testList.size());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check add method ( add(int index, T item) ) when index < 0 . Expected IndexOutOfBoundsException")
    public void add_ByIndex_Test_WhenIndexIsNegative_ReturnsIndexOutOfBoundException(CustomList<String> testList) {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.add(-1, TestUtils.NEW_ELEMENT));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check add method ( add(int index, T item) ) when index > testList.size() . Expected IndexOutOfBoundsException")
    public void add_ByIndex_Test_WhenIndexOverreachCollectionSize_ReturnsIndexOutOfBoundException(CustomList<String> testList) {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.add(TestUtils.SIZE_OF_LIST + 1, TestUtils.NEW_ELEMENT));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check add method ( add(int index, T item) ) when index < 0 . Expected IndexOutOfBoundsException")
    public void add_ByIndex_Test_WhenElementIsNull_ReturnsNullPointerException(CustomList<String> testList) {
        assertThrows(NullPointerException.class, () -> testList.add(1, null));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check addFirst method. Expected successful result")
    public void addFirstTestReturnsTrue(CustomList<String> testList) {

        var size = testList.size();
        testList.addFirst(TestUtils.NEW_ELEMENT);
        Assertions.assertEquals(TestUtils.NEW_ELEMENT, testList.get(0));
        Assertions.assertEquals(size + 1, testList.size());
        Assertions.assertEquals(TestUtils.FIRST_ELEMENT, testList.get(1));
        Assertions.assertEquals(TestUtils.TENTH_ELEMENT, testList.get(TestUtils.SIZE_OF_LIST));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check addFirst method. Expected NullPointerException")
    public void addFirstTestReturnsNullPointerException(CustomList<String> testList) {

        assertThrows(NullPointerException.class, () -> testList.addFirst(null));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check addLast method. Expected successful result")
    public void addLastTestReturnsTrue(CustomList<String> testList) {

        var size = testList.size();
        testList.addLast(TestUtils.NEW_ELEMENT);
        Assertions.assertEquals(TestUtils.NEW_ELEMENT, testList.get(size));
        Assertions.assertEquals(size + 1, testList.size());
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check addLast method. Expected NullPointerException")
    public void addLastTestReturnsNullPointerException(CustomList<String> testList) {

        assertThrows(NullPointerException.class, () -> testList.addLast(null));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check removeFirst method. Expected successful result")
    public void removeFirstTestReturnsTrue(CustomList<String> testList) {
        var size = testList.size();
        Assertions.assertEquals(TestUtils.FIRST_ELEMENT, testList.get(0));
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST, size);
        testList.removeFirst();
        Assertions.assertEquals(size - 1, testList.size());
        Assertions.assertEquals(TestUtils.SECOND_ELEMENT, testList.get(0));
    }

    @ParameterizedTest
    @MethodSource("customEmptyCollectionsImplementations")
    @DisplayName("check CustomList.removeFirst() method. Expected NoSuchElementException")
    public void removeFirst_emptyCustomList_ReturnsNoSuchElementException(CustomList<String> testList) {
        assertThrows(NoSuchElementException.class, testList::removeFirst);
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check removeLast method. Expected successful result")
    public void removeLastTestReturnsTrue(CustomList<String> testList) {
        var size = testList.size();
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST, size);
        Assertions.assertEquals(TestUtils.TENTH_ELEMENT, testList.get(size - 1));
        testList.removeLast();
        Assertions.assertEquals(size - 1, testList.size());
        Assertions.assertEquals(TestUtils.NINTH_ELEMENT, testList.get(size - 2));
    }

    @ParameterizedTest
    @MethodSource("customEmptyCollectionsImplementations")
    @DisplayName("check CustomList.removeLast() method. Expected NoSuchElementException")
    public void removeLast_emptyCustomList_ReturnsNoSuchElementException(CustomList<String> testList) {
        assertThrows(NoSuchElementException.class, testList::removeLast);
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check remove method. Expected successful result")
    public void removeTestReturnsTrue(CustomList<String> testList) {
        var size = testList.size();
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST, size);
        Assertions.assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(5));
        testList.remove(TestUtils.SIXTH_ELEMENT);
        Assertions.assertEquals(size - 1, testList.size());
        Assertions.assertEquals(TestUtils.SEVENTH_ELEMENT, testList.get(5));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check remove method ( remove (Object item) ). Expected unsuccessful result")
    public void remove_Item_TestReturnsFalse(CustomList<String> testList) {
        Assertions.assertNotEquals(testList.remove(TestUtils.ELEMENT_NOT_IN_LIST), true);
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check remove method ( remove (Object item) ). Expected NullPointerException")
    public void remove_Item_Test_ReturnsNullPointerException(CustomList<String> testList) {
        assertThrows(NullPointerException.class, () -> testList.remove(null));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check remove method ( remove (int index ) when 0 < index < list.size(). Expected successful result")
    public void remove_ByMiddleIndex_ShouldRemoveElement(CustomList<String> testList) {
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST, testList.size());
        Assertions.assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(5));
        String removed = testList.remove(5);
        Assertions.assertEquals(TestUtils.SEVENTH_ELEMENT, testList.get(5));
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST - 1, testList.size());
        Assertions.assertEquals(TestUtils.SIXTH_ELEMENT, removed);

    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check remove method ( remove (int index ) when index < 0. Expected IndexOutOfBoundsException")
    public void remove_ByIndex_Test_WhenIndexNegative_ReturnsIndexOutOfBoundException(CustomList<String> testList) {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.remove(-1));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check remove method ( remove (int index ) when index < 0. Expected IndexOutOfBoundsException")
    public void remove_ByIndex_Test_WhenIndexOverreachCollectionSize_ReturnsIndexOutOfBoundException(CustomList<String> testList) {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.remove(TestUtils.SIZE_OF_LIST + 1));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check indexOf method. Expected successful result")
    public void indexOfTestReturnsTrue(CustomList<String> testList) {
        Assertions.assertEquals(5, testList.indexOf(TestUtils.SIXTH_ELEMENT));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check indexOf method. Expected unsuccessful result")
    public void indexOfTestReturnsFalse(CustomList<String> testList) {
        Assertions.assertEquals(-1, testList.indexOf(TestUtils.ELEMENT_NOT_IN_LIST));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check indexOf method. Expected NullPointerException")
    public void indexOfTestReturnsNullPointerException(CustomList<String> testList) {
        assertThrows(NullPointerException.class, () -> testList.indexOf(null));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check contains method. Expected successful result")
    public void containsTestReturnsTrue(CustomList<String> testList) {
        Assertions.assertTrue(testList.contains(TestUtils.SIXTH_ELEMENT));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check contains method. Expected unsuccessful result")
    public void containsTestReturnsFalse(CustomList<String> testList) {
        Assertions.assertFalse(testList.contains(TestUtils.ELEMENT_NOT_IN_LIST));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check contains method. Expected NullPointerException")
    public void containsTestReturnsNullPointerException(CustomList<String> testList) {
        assertThrows(NullPointerException.class, () -> testList.contains(null));
    }

    @ParameterizedTest
    @MethodSource("customCollectionsImplementations")
    @DisplayName("check clear method. Expected successful result")
    public void clearTestReturnsTrue(CustomList<String> testList) {
        Assertions.assertEquals(TestUtils.SIZE_OF_LIST, testList.size());
        Assertions.assertFalse(testList.isEmpty());
        testList.clear();
        Assertions.assertEquals(0, testList.size());
        Assertions.assertTrue(testList.isEmpty());
    }
}
