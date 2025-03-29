import collections.CustomArrayList;
import collections.CustomLinkedList;
import collections.interfaces.CustomList;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Integer[] array = new Integer[]{1, 2, 3, 4, 5};

        System.out.println("\n-------------------------------------------------------------\n");
        CustomList<Integer> customLinkedList = new CustomLinkedList<>(array);
        System.out.println("CustomLinkedList инициализирован массивом Integer {1, 2, 3, 4, 5}");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        System.out.println("Выводим элементы списка, используя реализованный Iterator:");
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        CustomList<Integer> customArrayList = new CustomArrayList<>(array);
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

        customLinkedList.remove((Object) 4);
        System.out.println("Удаляем элемент с значением 4 из CustomLinkedList. Проверяем размер списка и выводим его:");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        customArrayList.remove((Object) 4);
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

        System.out.println("\n-------------------------------------------------------------");

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
        System.out.println("\n-------------------------------------------------------------");

        System.out.println("\nПроверяем алгоритмы сортировки:\n");
        var numbers = new Integer[20];
        for (int i = 0; i < 20; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }
        System.out.println("Исходный массив:");
        for (Integer i : numbers) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println("Заполняем данными массива CustomLinkedList и CustomArrayList");
        customLinkedList = new CustomLinkedList<>(numbers);
        customArrayList = new CustomArrayList<>(numbers);

        System.out.println("\n\nПрименим suickSort на CustomLinkedList:");
        var startTime = System.nanoTime();
        var sortedCustomLinkedList = customLinkedList.sort();
        var endTime = System.nanoTime();
        float duration = (float)(endTime - startTime);
        System.out.println("Отсортированный массив:");
        for (Integer i : sortedCustomLinkedList) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println(String.format("Время сортировки: %s (наносекунд),  %s (миллисекунд)", duration, (float) (duration / 1000000)));

        System.out.println("\n\nПрименим suickSort на CustomArrayList:");
        startTime = System.nanoTime();
        var sortedCustomArrayList = customArrayList.sort();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Отсортированный массив:");
        for (Integer i : sortedCustomArrayList) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println(String.format("Время сортировки: %s (наносекунд),  %s (миллисекунд)", duration, (float) (duration / 1000000)));

        System.out.println("\n-------------------------------------------------------------");

        System.out.println("\nПроверим mergeSort:\n");
        for (int i = 0; i < 20; i++) {
            numbers[i] = (int) (Math.random() * 100);
        }
        System.out.println("Заново заполняем массив рандомными числами:");
        for (Integer i : numbers) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println("Заполняем данными массива CustomLinkedList и CustomArrayList");
        customLinkedList = new CustomLinkedList<>(numbers);
        customArrayList = new CustomArrayList<>(numbers);

        System.out.println("\n\nПрименим mergeSort на CustomLinkedList:");
        startTime = System.nanoTime();
        sortedCustomLinkedList = customLinkedList.mergeSort();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Отсортированный массив:");
        for (Integer i : sortedCustomLinkedList) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println(String.format("Время сортировки: %s (наносекунд),  %s (миллисекунд)", duration, (float) (duration / 1000000)));

        System.out.println("\n\nПрименим mergeSort на CustomArrayList:");
        startTime = System.nanoTime();
        sortedCustomArrayList = customArrayList.mergeSort();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Отсортированный массив:");
        for (Integer i : sortedCustomArrayList) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println(String.format("Время сортировки: %s (наносекунд),  %s (миллисекунд)", duration, (float) (duration / 1000000)));
    }


}