package tests.collections;

import collections.CustomArrayList;
import collections.CustomLinkedList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import tests.collections.utils.TestUtils;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CustomArrayListTests {


    CustomArrayList<String> testList = TestUtils.fillTestArrayList();

    @BeforeEach
    public void setTestListToBaseCondition() {
        testList = TestUtils.fillTestArrayList();
    }

    @Test
    @DisplayName("check collection size. Expected successful result")
    public void sizeTestCorrect() {
        assertEquals(testList.size(), TestUtils.SIZE_OF_LIST);
    }

    @Test
    @DisplayName("check collection size. Expected unsuccessful result")
    public void sizeTestIncorrect() {
        assertNotEquals(0, testList.size());
    }

    @Test
    @DisplayName("check isEmpty method. Returns true")
    public void isEmptyTestReturnsTrue() {
        assertTrue(new CustomLinkedList<String>().isEmpty());
    }

    @Test
    @DisplayName("check isEmpty method. Returns false")
    public void isEmptyTestReturnsFalse() {
        assertFalse(testList.isEmpty());
    }

    @Test
    @DisplayName("check get method. Expected successful result")
    public void getTestReturnsTrue() {
        assertEquals(TestUtils.FIRST_ELEMENT, testList.get(0));
    }

    @Test
    @DisplayName("check get method. Expected unsuccessful result")
    public void getTestReturnsFalse() {
        assertNotEquals(TestUtils.ELEMENT_NOT_IN_LIST, testList.get(0));
    }

    @Test
    @DisplayName("check get method. Expected IndexOutOfBoundsException")
    public void getTestReturnsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.get(TestUtils.SIZE_OF_LIST));
    }

    @Test
    @DisplayName("check add method ( add(T item) ). Expected successful result")
    public void add_Item_TestReturnsTrue() {
        assertTrue(testList.add(TestUtils.NEW_ELEMENT));
        assertEquals(TestUtils.SIZE_OF_LIST + 1, testList.size());
        assertEquals(TestUtils.NEW_ELEMENT, testList.get(TestUtils.SIZE_OF_LIST));
    }

    @Test
    @DisplayName("check add method ( add(int index, T item) ) when 0 < index < list.size() . Expected successful result")
    public void add_ByIndex_TestReturnsTrue() {
        assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(5));
        testList.add(5, TestUtils.NEW_ELEMENT);
        assertEquals(TestUtils.NEW_ELEMENT, testList.get(5));
        assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(6));
        assertEquals(TestUtils.SIZE_OF_LIST + 1, testList.size());
    }

    @Test
    @DisplayName("check add method ( add(int index, T item) ) when index < 0 . Expected IndexOutOfBoundsException")
    public void add_ByIndex_Test_WhenIndexIsNegative_ReturnsIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.add(-1, TestUtils.NEW_ELEMENT));
    }

    @Test
    @DisplayName("check add method ( add(int index, T item) ) when index > testList.size() . Expected IndexOutOfBoundsException")
    public void add_ByIndex_Test_WhenIndexOverreachCollectionSize_ReturnsIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.add(TestUtils.SIZE_OF_LIST + 1, TestUtils.NEW_ELEMENT));
    }

    @Test
    @DisplayName("check add method ( add(int index, T item) ) when index < 0 . Expected IndexOutOfBoundsException")
    public void add_ByIndex_Test_WhenElementIsNull_ReturnsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testList.add(1, null));
    }

    @Test
    @DisplayName("check addFirst method. Expected successful result")
    public void addFirstTestReturnsTrue() {

        var size = testList.size();
        testList.addFirst(TestUtils.NEW_ELEMENT);
        assertEquals(TestUtils.NEW_ELEMENT, testList.get(0));
        assertEquals(size + 1, testList.size());
        assertEquals(TestUtils.FIRST_ELEMENT, testList.get(1));
        assertEquals(TestUtils.TENTH_ELEMENT, testList.get(TestUtils.SIZE_OF_LIST));
    }

    @Test
    @DisplayName("check addFirst method. Expected NullPointerException")
    public void addFirstTestReturnsNullPointerException() {

        assertThrows(NullPointerException.class, () -> testList.addFirst(null));
    }

    @Test
    @DisplayName("check addLast method. Expected successful result")
    public void addLastTestReturnsTrue() {

        var size = testList.size();
        testList.addLast(TestUtils.NEW_ELEMENT);
        assertEquals(TestUtils.NEW_ELEMENT, testList.get(size));
        assertEquals(size + 1, testList.size());
    }

    @Test
    @DisplayName("check addLast method. Expected NullPointerException")
    public void addLastTestReturnsNullPointerException() {

        assertThrows(NullPointerException.class, () -> testList.addLast(null));
    }

    @Test
    @DisplayName("check removeFirst method. Expected successful result")
    public void removeFirstTestReturnsTrue() {
        var size = testList.size();
        assertEquals(TestUtils.FIRST_ELEMENT, testList.get(0));
        assertEquals(TestUtils.SIZE_OF_LIST, size);
        testList.removeFirst();
        assertEquals(size - 1, testList.size());
        assertEquals(TestUtils.SECOND_ELEMENT, testList.get(0));
    }
    @Test
    @DisplayName("check removeFirst method. Expected NoSuchElementException")
    public void removeFirstTestReturnsNoSuchElementException() {
        CustomLinkedList<String> testListEmpty = new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class, testListEmpty::removeFirst);
    }

    @Test
    @DisplayName("check removeLast method. Expected successful result")
    public void removeLastTestReturnsTrue() {
        var size = testList.size();
        assertEquals(TestUtils.SIZE_OF_LIST, size);
        assertEquals(TestUtils.TENTH_ELEMENT, testList.get(size - 1));
        testList.removeLast();
        assertEquals(size - 1, testList.size());
        assertEquals(TestUtils.NINTH_ELEMENT, testList.get(size - 2));
    }

    @Test
    @DisplayName("check removeLast method. Expected NoSuchElementException")
    public void removeLastTestReturnsNoSuchElementException() {
        CustomLinkedList<String> testListEmpty = new CustomLinkedList<>();
        assertThrows(NoSuchElementException.class, testListEmpty::removeLast);
    }

    @Test
    @DisplayName("check remove method. Expected successful result")
    public void removeTestReturnsTrue() {
        var size = testList.size();
        assertEquals(TestUtils.SIZE_OF_LIST, size);
        assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(5));
        testList.remove(TestUtils.SIXTH_ELEMENT);
        assertEquals(size - 1, testList.size());
        assertEquals(TestUtils.SEVENTH_ELEMENT, testList.get(5));
    }

    @Test
    @DisplayName("check remove method ( remove (Object item) ). Expected unsuccessful result")
    public void remove_Item_TestReturnsFalse() {
        assertNotEquals(testList.remove(TestUtils.ELEMENT_NOT_IN_LIST), true);
    }

    @Test
    @DisplayName("check remove method ( remove (Object item) ). Expected NullPointerException")
    public void remove_Item_Test_ReturnsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testList.remove(null));
    }

    @Test
    @DisplayName("check remove method ( remove (int index ) when 0 < index < list.size(). Expected successful result")
    public void remove_ByIndex_TestReturnsTrue() {

        assertEquals(TestUtils.SIZE_OF_LIST, testList.size());
        assertEquals(TestUtils.SIXTH_ELEMENT, testList.get(5));
        testList.remove(5);
        assertEquals(TestUtils.SEVENTH_ELEMENT, testList.get(5));
        assertEquals(TestUtils.SIZE_OF_LIST - 1, testList.size());
    }

    @Test
    @DisplayName("check remove method ( remove (int index ) when index < 0. Expected IndexOutOfBoundsException")
    public void remove_ByIndex_Test_WhenIndexNegative_ReturnsIndexOutOfBoundException() {

        assertThrows(IndexOutOfBoundsException.class, () -> testList.remove(-1));
    }

    @Test
    @DisplayName("check remove method ( remove (int index ) when index < 0. Expected IndexOutOfBoundsException")
    public void remove_ByIndex_Test_WhenIndexOverreachCollectionSize_ReturnsIndexOutOfBoundException() {

        assertThrows(IndexOutOfBoundsException.class, () -> testList.remove(TestUtils.SIZE_OF_LIST + 1));
    }

    @Test
    @DisplayName("check indexOf method. Expected successful result")
    public void indexOfTestReturnsTrue() {
        assertEquals(5, testList.indexOf(TestUtils.SIXTH_ELEMENT));
    }

    @Test
    @DisplayName("check indexOf method. Expected unsuccessful result")
    public void indexOfTestReturnsFalse() {
        assertEquals(-1, testList.indexOf(TestUtils.ELEMENT_NOT_IN_LIST));
    }

    @Test
    @DisplayName("check indexOf method. Expected NullPointerException")
    public void indexOfTestReturnsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testList.indexOf(null));
    }

    @Test
    @DisplayName("check contains method. Expected successful result")
    public void containsTestReturnsTrue() {
        assertTrue(testList.contains(TestUtils.SIXTH_ELEMENT));
    }

    @Test
    @DisplayName("check contains method. Expected unsuccessful result")
    public void containsTestReturnsFalse() {
        assertFalse(testList.contains(TestUtils.ELEMENT_NOT_IN_LIST));
    }

    @Test
    @DisplayName("check contains method. Expected NullPointerException")
    public void containsTestReturnsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testList.contains(null));
    }

    @Test
    @DisplayName("check clear method. Expected successful result")
    public void clearTestReturnsTrue() {
        assertEquals(TestUtils.SIZE_OF_LIST, testList.size());
        assertFalse(testList.isEmpty());
        testList.clear();
        assertEquals(0, testList.size());
        assertTrue(testList.isEmpty());
    }

}
