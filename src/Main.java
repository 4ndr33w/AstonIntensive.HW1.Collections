import collections.CustomArrayList;
import collections.CustomLinkedList;
import collections.interfaces.CustomList;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Integer[] array = new Integer[]{1, 2, 3, 4, 5};

        System.out.println("\n-------------------------------------------------------------\n");
        CustomList<Integer> customLinkedList = new CustomLinkedList<>( array );
        System.out.println("CustomLinkedList инициализирован массивом Integer {1, 2, 3, 4, 5}");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        System.out.println("Выводим элементы списка, используя реализованный Iterator:");
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        CustomList<Integer> customArrayList = new CustomArrayList<>( array );
        System.out.println("CustomArrayList инициализирован массивом Integer {1, 2, 3, 4, 5}");
        System.out.println(String.format("Размер списка: %s", customArrayList.size()));
        System.out.println("Выводим элементы списка, используя реализованный Iterator:");
        for (Integer i : customArrayList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customArrayList.indexOf(i), i));
        }
        System.out.println("\n");
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");

        customLinkedList.add(6);
        System.out.println("Добавляем элемент в CustomLinkedList. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        customArrayList.add(6);
        System.out.println("Добавляем элемент в CustomArrayList. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customArrayList.size()));
        for (Integer i : customArrayList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customArrayList.indexOf(i), i));
        }
        System.out.println("\n");
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");

        customLinkedList.add(4, 10);
        System.out.println("Добавляем элемент в CustomLinkedList на позицию по индексу 4. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        customArrayList.add(4, 10);
        System.out.println("Добавляем элемент в CustomArrayList на позицию по индексу 4. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customArrayList.size()));
        for (Integer i : customArrayList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customArrayList.indexOf(i), i));
        }
        System.out.println("\n");
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");

        customLinkedList.remove((Object)4);
        System.out.println("Удаляем элемент с значением 4 из CustomLinkedList. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        customArrayList.remove((Object)4);
        System.out.println("Удаляем элемент с значением 4 из CustomArrayList. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customArrayList.size()));
        for (Integer i : customArrayList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customArrayList.indexOf(i), i));
        }
        System.out.println("\n");
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");

        var element = customLinkedList.remove(4);
        System.out.println(String.format("Удаляем элемент по индексу 4 из CustomLinkedList и получаем его значение. Полученное значение: %s", element));
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        element = customArrayList.remove(4);
        System.out.println(String.format("Удаляем элемент по индексу 4 из CustomArrayList и получаем его значение. Полученное значение: %s", element));
        System.out.println(String.format("Размер списка: %s", customArrayList.size()));
        for (Integer i : customArrayList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customArrayList.indexOf(i), i));
        }
        System.out.println("\n");
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");

        element = customLinkedList.get(3);
        System.out.println(String.format("Получаем элемент по индексу 3 из CustomLinkedList и получаем его значение. Полученное значение: %s", element));

        System.out.println("\n");

        element = customArrayList.get(3);
        System.out.println(String.format("Получаем элемент по индексу 3 из CustomArrayList и получаем его значение. Полученное значение: %s", element));

        System.out.println("\n");
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");

        customLinkedList.clear();
        System.out.println("Очищаем список CustomLinkedList. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        customArrayList.clear();
        System.out.println("Очищаем список CustomArrayList. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customArrayList.size()));
        for (Integer i : customArrayList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customArrayList.indexOf(i), i));
        }
        System.out.println("\n");
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");

    }
}