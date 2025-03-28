package tests.collections.utils;

import collections.CustomArrayList;
import collections.CustomLinkedList;
import collections.LinkedListNode;

public class TestUtils {

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
    public static LinkedListNode<String> testLinkedListNodePrevious = new LinkedListNode<String>("PreviousReference");
    public static LinkedListNode<String> testLinkedListNode = new LinkedListNode<String>("CurrentReference");
    public static LinkedListNode<String> testLinkedListNodeNext = new LinkedListNode<String>("NextReference");

    public static CustomLinkedList<String> fillTestLinkedList(){
        CustomLinkedList<String> testLinkedList1 = new CustomLinkedList<String>();
        testLinkedList1.add(FIRST_ELEMENT);
        testLinkedList1.add(SECOND_ELEMENT);
        testLinkedList1.add(THIRD_ELEMENT);
        testLinkedList1.add(FOURTH_ELEMENT);
        testLinkedList1.add(FIFTH_ELEMENT);
        testLinkedList1.add(SIXTH_ELEMENT);
        testLinkedList1.add(SEVENTH_ELEMENT);
        testLinkedList1.add(EIGHTH_ELEMENT);
        testLinkedList1.add(NINTH_ELEMENT);
        testLinkedList1.add(TENTH_ELEMENT);
        return testLinkedList1;
    }

    public static CustomArrayList<String> fillTestArrayList () {
        CustomArrayList<String> testList = new CustomArrayList<>();
        testList.add(FIRST_ELEMENT);
        testList.add(SECOND_ELEMENT);
        testList.add(THIRD_ELEMENT);
        testList.add(FOURTH_ELEMENT);
        testList.add(FIFTH_ELEMENT);
        testList.add(SIXTH_ELEMENT);
        testList.add(SEVENTH_ELEMENT);
        testList.add(EIGHTH_ELEMENT);
        testList.add(NINTH_ELEMENT);
        testList.add(TENTH_ELEMENT);
        return testList;
    }
}
