import collections.CustomArrayList;
import collections.CustomLinkedList;
import collections.interfaces.CustomList;

/**
 * Класс для демонстрации работы различных реализаций интерфейса CustomList.
 * <p>
 * Этот класс содержит набор сценариев работы с коллекциями, которые демонстрируют
 * работы различных методов интерфейса CustomList на примерах реализации
 * CustomLinkedList и CustomArrayList.
 * </p>
 *
 * <table border="1">
 *   <tr><td>представлена работа</td>
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
public class Main {
    public static void main(String[] args) {

        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        CustomList<Integer> customLinkedList = new CustomLinkedList<>(array);
        CustomList<Integer> customArrayList = new CustomArrayList<>(array);
        var numbers = new Integer[20];

        customListSizeAndIteratorDemo(customLinkedList, customArrayList);

        printDivider();

        customListAddAndIndexOfDemo(customLinkedList, customArrayList);

        printDivider();

        customListAddByIndexDemo(customLinkedList, customArrayList);

        printDivider();

        customListRemoveObjectDemo(customLinkedList, customArrayList);

        printDivider();

        customListRemoveByIndexDemo(customLinkedList, customArrayList);

        printDivider();

        customListGetDemo(customLinkedList, customArrayList);

        printDivider();

        customListClearDemo(customLinkedList, customArrayList);

        printDivider();
        System.out.println("Сортировка массива Integer[20]");
        printDivider();

        quickSortDemo(numbers, customLinkedList, customArrayList);

        printDivider();

        mergeSortDemo(numbers, customLinkedList, customArrayList);

    }

    private static void customListSizeAndIteratorDemo(CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
        System.out.println("\n-------------------------------------------------------------\n");

        System.out.println("CustomLinkedList инициализирован массивом Integer {1, 2, 3, 4, 5}");
        System.out.println(String.format("Размер списка: %s", customLinkedList.size()));
        System.out.println("Выводим элементы списка, используя реализованный Iterator:");
        for (Integer i : customLinkedList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customLinkedList.indexOf(i), i));
        }
        System.out.println("\n");

        System.out.println("CustomArrayList инициализирован массивом Integer {1, 2, 3, 4, 5}");
        System.out.println(String.format("Размер списка: %s", customArrayList.size()));
        System.out.println("Выводим элементы списка, используя реализованный Iterator:");
        for (Integer i : customArrayList) {
            System.out.print(String.format("%s-й элемент: %s |   ", customArrayList.indexOf(i), i));
        }
    }

    private static void customListAddAndIndexOfDemo(CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
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
    }

    private static void customListAddByIndexDemo(CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
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
    }

    private static void customListRemoveObjectDemo(CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
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
    }

    private static void customListRemoveByIndexDemo(CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
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
    }

    private static void customListGetDemo(CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
        var element = customLinkedList.get(3);
        System.out.println(String.format("Получаем элемент по индексу 3 из CustomLinkedList и получаем его значение. Полученное значение: %s", element));

        System.out.println("\n");

        element = customArrayList.get(3);
        System.out.println(String.format("Получаем элемент по индексу 3 из CustomArrayList и получаем его значение. Полученное значение: %s", element));
    }

    private static void customListClearDemo(CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
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
    }

    private static void quickSortDemo(Integer[] numbers, CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList ) {
        System.out.println("Заполняем массив рандомными данными");
        numbers = fillIntegerArrayByRandomData(numbers);
        System.out.println("Исходный массив:");
        for (Integer i : numbers) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println("\nЗаполняем данными массива CustomLinkedList и CustomArrayList");
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
    }

    private static void mergeSortDemo(Integer[] numbers, CustomList<Integer> customLinkedList, CustomList<Integer> customArrayList  ) {
        System.out.println("Заполняем массив рандомными данными");
        numbers = fillIntegerArrayByRandomData(numbers);
        System.out.println("Исходный массив:");
        for (Integer i : numbers) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println("\nЗаполняем данными массива CustomLinkedList и CustomArrayList");
        customLinkedList = new CustomLinkedList<>(numbers);
        customArrayList = new CustomArrayList<>(numbers);

        System.out.println("\n\nПрименим mergeSort на CustomLinkedList:");
        var startTime = System.nanoTime();
        var sortedCustomLinkedList = customLinkedList.mergeSort();
        var endTime = System.nanoTime();
        var nanosecDuration = endTime - startTime;
        System.out.println("Отсортированный массив:");
        for (Integer i : sortedCustomLinkedList) {
            System.out.print(String.format("%s |   ", i));
        }
        double millisecDuration = (double) (nanosecDuration / 1000000);
        System.out.println(String.format("Время сортировки: %s (наносекунд),  %s (миллисекунд)", nanosecDuration, millisecDuration));

        System.out.println("\n\nПрименим mergeSort на CustomArrayList:");
        startTime = System.nanoTime();
        var sortedCustomArrayList = customArrayList.mergeSort();
        endTime = System.nanoTime();
        nanosecDuration = endTime - startTime;
        millisecDuration = (double) (nanosecDuration / 1000000);
        System.out.println("Отсортированный массив:");
        for (Integer i : sortedCustomArrayList) {
            System.out.print(String.format("%s |   ", i));
        }
        System.out.println(String.format("Время сортировки: %s (наносекунд),  %s (миллисекунд)", nanosecDuration, millisecDuration));
    }

    private static Integer[] fillIntegerArrayByRandomData(Integer[] array) {
        for (int i = 0; i < 20; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    private static void printDivider() {
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("\n");
    }
}