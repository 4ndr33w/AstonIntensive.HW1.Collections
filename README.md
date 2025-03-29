## Aston Intensive HW 1. Коллекции

### Описание проекта
Проект представляет кастомную реализацию `ArrayList` и `LinkedList`,
а так же `LinkedListNode`, выступающий в качестве узла в составе `CustomLinkedList`

### Дополнительно
- создан общий для обоих списков интерфейс `CustomList`, имплементирующий `Iterable` и `RandomAccess`
- методы сортировки `quickSort` и `mergeSort` вынесены в отдельные классы, но в `CustomList` написана дефолтная реализация с вызовом сортировки из `T` extends `CustomList`
- Для упорядоченного перебора коллекций написана реализация `Iterator`
- `toArray()` для удобства тестирования
- Добавлена документация `JavaDoc`
- написаны тесты на основные ситуации
- в классе `Main` представлена работа методов кастомных коллекций 