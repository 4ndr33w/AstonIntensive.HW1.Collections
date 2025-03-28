import collections.CustomArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>( array );

        List<Integer> test = new ArrayList<Integer>();
        List<Integer> test2 = new LinkedList<>();

        //-------------------------------------------------------------------
        //-------------------------------------------------------------------
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        for(Map.Entry<String, Integer> entry: map.entrySet()) {

        };

        List<Integer> list = new ArrayList<>();
        list.add(1);

        List<Integer> list2 = new LinkedList<>();
        list2.add(1);

        var result = list.getFirst().equals(list2.getFirst());


        //-------------------------------------------------------------------
        //-------------------------------------------------------------------

        System.out.println(String.format("collection size: %s", customArrayList.size()));

        System.out.println(String.format("collection is Empty: %s", customArrayList.isEmpty()));

        System.out.println("Iterator:");
        customArrayList.forEach(System.out::println);

        customArrayList.add(5, 6);

        System.out.println("add element. new size: ");
        System.out.println(customArrayList.size());
        System.out.println("Iterator:");
        customArrayList.forEach(System.out::println);

        customArrayList.remove(4);

        System.out.println("remove element. new size: ");
        System.out.println(customArrayList.size());
        System.out.println("Iterator:");
        customArrayList.forEach(System.out::println);

        customArrayList.remove(2);

        System.out.println("remove element. new size: ");
        System.out.println(customArrayList.size());
        System.out.println("Iterator:");
        customArrayList.forEach(System.out::println);

        System.out.println("Hello world!");
    }
}