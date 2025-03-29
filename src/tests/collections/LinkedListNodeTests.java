package tests.collections;

import collections.LinkedListNode;
import org.junit.Test;
import tests.collections.utils.TestUtils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Набор тестов для проверки функциональности класса LinkedListNode.
 * <p>
 * Этот тестовый класс содержит набор методов для проверки корректности
 * работы узлов двусвязного списка. Тесты охватывают основные операции:
 * создание узлов, установку и получение значений, управление ссылками
 * на соседние узлы.
 * </p>
 *
 * @version 1.0
 * @author 4ndr33w
 *
 * @see LinkedListNode
 */
public class LinkedListNodeTests {

    @Test
    public void testDefaultConstructor() {
        LinkedListNode<String> node = new LinkedListNode<>();
        assertNull(node.getNode());
        assertNull(node.getNext());
        assertNull(node.getPrev());
    }

    @Test
    public void testValueConstructor() {
        String value = TestUtils.TEST_NODE;
        LinkedListNode<String> node = new LinkedListNode<>(value);
        assertEquals(value, node.getNode());
        assertNull(node.getNext());
        assertNull(node.getPrev());
    }

    @Test
    public void testValueAndNextConstructor() {
        LinkedListNode<String> nextNode = new LinkedListNode<>(TestUtils.NEXT_NODE);
        LinkedListNode<String> node = new LinkedListNode<>(TestUtils.CURRENT_NODE, nextNode);

        assertEquals(TestUtils.CURRENT_NODE, node.getNode());
        assertEquals(nextNode, node.getNext());
        assertNull(node.getPrev());
        assertNull(nextNode.getPrev());
    }

    @Test
    public void testFullConstructor() {
        LinkedListNode<String> prevNode = new LinkedListNode<>(TestUtils.PREVIOUS_NODE);
        LinkedListNode<String> nextNode = new LinkedListNode<>(TestUtils.NEXT_NODE);
        LinkedListNode<String> node = new LinkedListNode<>(TestUtils.CURRENT_NODE, nextNode, prevNode);

        assertEquals(TestUtils.CURRENT_NODE, node.getNode());
        assertEquals(nextNode, node.getNext());
        assertEquals(prevNode, node.getPrev());
        assertNull(prevNode.getPrev());
        assertNull(nextNode.getNext());
    }

    @Test
    public void testSetters() {
        LinkedListNode<String> node = new LinkedListNode<>();
        LinkedListNode<String> next = new LinkedListNode<>(TestUtils.NEXT_NODE);
        LinkedListNode<String> prev = new LinkedListNode<>(TestUtils.PREVIOUS_NODE);

        node.setNode(TestUtils.TEST_NODE);
        node.setNext(next);
        node.setPrev(prev);

        assertEquals(TestUtils.TEST_NODE, node.getNode());
        assertEquals(next, node.getNext());
        assertEquals(prev, node.getPrev());
    }

    @Test
    public void testEqualsAndHashCode() {
        LinkedListNode<String> node1 = new LinkedListNode<>(TestUtils.TEST_NODE);
        LinkedListNode<String> node2 = new LinkedListNode<>(TestUtils.TEST_NODE);
        LinkedListNode<String> node3 = new LinkedListNode<>(TestUtils.NEW_ELEMENT);

        assertEquals(node1, node2);
        assertNotEquals(node1, node3);
        assertEquals(node1.hashCode(), node2.hashCode());
    }

    @Test
    public void testToString() {
        LinkedListNode<String> node = new LinkedListNode<>(TestUtils.TEST_NODE);
        String str = node.toString();
        assertTrue(str.contains(TestUtils.TEST_NODE));
        assertTrue(str.contains("null"));
    }

    @Test
    public void testLinkedNodes() {
        LinkedListNode<String> first = new LinkedListNode<>(TestUtils.PREVIOUS_NODE);
        LinkedListNode<String> second = new LinkedListNode<>(TestUtils.CURRENT_NODE);
        LinkedListNode<String> third = new LinkedListNode<>(TestUtils.NEXT_NODE);

        first.setNext(second);
        second.setPrev(first);
        second.setNext(third);
        third.setPrev(second);

        assertNull(first.getPrev());
        assertEquals(second, first.getNext());
        assertEquals(first, second.getPrev());
        assertEquals(third, second.getNext());
        assertEquals(second, third.getPrev());
        assertNull(third.getNext());
    }
}
