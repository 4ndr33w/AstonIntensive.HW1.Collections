package tests.collections.utils;

import collections.CustomArrayList;
import collections.CustomLinkedList;
import collections.interfaces.CustomList;

import java.util.List;

/**
 * Утилитный класс для создания тестовых данных и настройки тестовой среды.
 * <p>
 * Этот класс содержит набор статических методов и констант, которые используются
 * для инициализации тестовых данных при проведении unit-тестов. Он обеспечивает
 * создание заполненных коллекций для проверки функциональности.
 * </p>
 * <table border="1">
 *   <tr><td>Основные функции</td>
 *       <td>
 * <ul>
 *   <li>Определение констант для тестирования</li>
 *   <li>Создание тестовых экземпляров коллекций</li>
 *   <li>Заполнение коллекций тестовыми данными</li>
 *   <li>Предоставление готовых наборов данных для тестов</li>
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


    public static CustomLinkedList<String> fillTestLinkedListByStrings(){
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

    public static CustomArrayList<String> fillTestArrayListByStrings() {
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

    public static Integer[] unsortedIntegerArray = new Integer[]{5,10,2,7,9,8,4,3,6,1};

    public static Integer[] sortedIntegerArray = new Integer[] {1,2,3,4,5,6,7,8,9,10};

    public static String[]  sortedStringArray = new String[] {"Eight", "Five", "Four", "Nine", "One", "Seven", "Six", "Ten", "Three", "Two"};

    public static CustomLinkedList<Integer> fillTestLinkedListByIntegers(){
        return new CustomLinkedList<>(unsortedIntegerArray);
    }

    public static CustomArrayList<Integer> fillTestArrayListByIntegers() {
        return new CustomArrayList<>(unsortedIntegerArray);
    }

    public static CustomLinkedList<Integer> customLinkedListIfSingleInteger = new CustomLinkedList<>(new Integer[]{1});
    public static CustomArrayList<Integer> customArrayListIfSingleInteger = new CustomArrayList<>(new Integer[]{1});

    public static List<Object[]> customCollectionsOfStringsImplementations() {
        return List.of(
                new Object[] {fillTestLinkedListByStrings()},
                new Object[] {fillTestArrayListByStrings()}
        );
    }

    public static List<Object[]> customCollectionsOfIntegersImplementations() {
        return List.of(
                new Object[] {fillTestLinkedListByIntegers()},
                new Object[] {fillTestArrayListByIntegers()}
        );
    }

    public static List<Object[]> customCollectionOfSingleIntegerElement() {
        return List.of(
                new Object[] {customLinkedListIfSingleInteger},
                new Object[] {customArrayListIfSingleInteger}
        );
    }

    public static List<Object[]> customEmptyCollectionsImplementations() {
        return List.of(
                new Object[] {new CustomLinkedList<String>()},
                new Object[] {new CustomArrayList<String>()}
        );
    }

}
