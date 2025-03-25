import collections.CustomArrayList;

public class Main {
    public static void main(String[] args) {

        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>( array );

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