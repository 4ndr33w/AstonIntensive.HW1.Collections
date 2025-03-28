package collections;


import java.util.Objects;

/**
 * Класс, представляющий узел двусвязного списка.
 * <p>
 * LinkedListNode является базовым строительным блоком для двусвязных списков,
 * содержащий данные и ссылки на предыдущий и следующий узлы. Этот класс
 * используется для создания и управления структурами данных на основе
 * двусвязных списков.
 * </p>
 * <p>
 * Основные характеристики:
 * - Содержит данные произвольного типа
 * - Имеет ссылки на предыдущий и следующий узлы
 * - Является частным компонентом структуры данных двусвязного списка
 * - Обеспечивает возможность эффективной вставки и удаления элементов
 * </p>
 *
 * @param <T> тип хранимых данных в узле
 * @see CustomLinkedList
 */
public class LinkedListNode<T> {

    private T node;
    private LinkedListNode<T> next;
    private LinkedListNode<T> prev;

    public LinkedListNode() {
        this.node = null;
        this.next = null;
        this.prev = null;
    }
    public LinkedListNode(T node) {
        this.node = node;
        this.next = null;
        this.prev = null;
    }
    public LinkedListNode(T value, LinkedListNode<T> next) {
        this.node = value;
        this.next = next;
        this.prev = null;
    }
    public LinkedListNode(T value, LinkedListNode<T> next, LinkedListNode<T> prev) {
        this.node = value;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Возвращает следующий узел в связанном списке.
     *
     * @return следующий узел типа LinkedListNode<T> или null, если узел является последним
     */
    public LinkedListNode<T> getNext() {
        return this.next;
    }

    /**
     * Возвращает предыдущий узел в связанном списке.
     *
     * @return предыдущий узел типа LinkedListNode<T> или null, если узел является первым
     */
    public LinkedListNode<T> getPrev() {
        return this.prev;
    }

    /**
     * Возвращает значение, хранящееся в текущем узле.
     *
     * @return значение типа T, хранящееся в узле
     */
    public T getNode() {
        return this.node;
    }

    /**
     * Устанавливает следующий узел в связанном списке.
     *
     * @param next ссылка на следующий узел типа LinkedListNode<T>
     */
    public void setNext(LinkedListNode<T> next){
        this.next = next;
    }

    /**
     * Устанавливает предыдущий узел в связанном списке.
     *
     * @param prev ссылка на предыдущий узел типа LinkedListNode<T>
     */
    public void setPrev(LinkedListNode<T> prev){
        this.prev = prev;
    }

    /**
     * Устанавливает значение текущего узла.
     *
     * @param node значение типа T, которое будет храниться в узле
     */
    public void setNode(T node){
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListNode<?> that = (LinkedListNode<?>) o;
        return Objects.equals(node, that.node) &&
                Objects.equals(next, that.next) &&
                Objects.equals(prev, that.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, next, prev);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + node +
                ", next=" + (next != null ? "hasNext" : "null") +
                ", prev=" + (prev != null ? "hasPrev" : "null") +
                '}';
    }
}
