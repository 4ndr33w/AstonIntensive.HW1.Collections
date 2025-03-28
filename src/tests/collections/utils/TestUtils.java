package tests.collections.utils;

import collections.CustomArrayList;
import collections.CustomLinkedList;

/**
 * Утилитный класс для создания тестовых данных и настройки тестовой среды.
 *
 * Этот класс содержит набор статических методов и констант, которые используются
 * для инициализации тестовых данных при проведении unit-тестов. Он обеспечивает
 * создание заполненных коллекций для проверки функциональности.
 *
 * Основные функции:
 * - Определение констант для тестирования
 * - Создание тестовых экземпляров коллекций
 * - Заполнение коллекций тестовыми данными
 * - Предоставление готовых наборов данных для тестов
 *
 * @see CustomLinkedList
 * @see CustomArrayList
 */
public class TestUtils {

    public static final String PREVIOUS_NODE = "previous node";
    public static final String CURRENT_NODE = "current node";
    public static final String NEXT_NODE = "next node";
    public static final String TEST_NODE = "test node";

    public static final String FIRST_ELEMENT = "One";
    public static final String SECOND_ELEMENT = "Two";
    public static final String THIRD_ELEMENT = "Three";
    public static final String FOURTH_ELEMENT = "Four";
    public static final String FIFTH_ELEMENT = "Five";
    public static final String SIXTH_ELEMENT = "Six";
    public static final String SEVENTH_ELEMENT = "Seven";
    public static final String EIGHTH_ELEMENT = "Eight";
    public static final String NINTH_ELEMENT = "Nine";
    public static final String TENTH_ELEMENT = "Ten";
    public static final String NEW_ELEMENT = "new element";
    public static final String ELEMENT_NOT_IN_LIST = "element not in list";

    public static final int SIZE_OF_LIST = 10;

    public static CustomLinkedList<String> fillTestLinkedList(){
        CustomLinkedList<String> testLinkedList = new CustomLinkedList<String>();
        testLinkedList.add(FIRST_ELEMENT);
        testLinkedList.add(SECOND_ELEMENT);
        testLinkedList.add(THIRD_ELEMENT);
        testLinkedList.add(FOURTH_ELEMENT);
        testLinkedList.add(FIFTH_ELEMENT);
        testLinkedList.add(SIXTH_ELEMENT);
        testLinkedList.add(SEVENTH_ELEMENT);
        testLinkedList.add(EIGHTH_ELEMENT);
        testLinkedList.add(NINTH_ELEMENT);
        testLinkedList.add(TENTH_ELEMENT);
        return testLinkedList;
    }

    public static CustomArrayList<String> fillTestArrayList () {
        CustomArrayList<String> testArrayList = new CustomArrayList<>();
        testArrayList.add(FIRST_ELEMENT);
        testArrayList.add(SECOND_ELEMENT);
        testArrayList.add(THIRD_ELEMENT);
        testArrayList.add(FOURTH_ELEMENT);
        testArrayList.add(FIFTH_ELEMENT);
        testArrayList.add(SIXTH_ELEMENT);
        testArrayList.add(SEVENTH_ELEMENT);
        testArrayList.add(EIGHTH_ELEMENT);
        testArrayList.add(NINTH_ELEMENT);
        testArrayList.add(TENTH_ELEMENT);
        return testArrayList;
    }
}
