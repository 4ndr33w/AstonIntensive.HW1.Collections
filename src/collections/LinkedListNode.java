package collections;


/**
 * Класс, представляющий узел двусвязного списка.
 *
 * <p>Каждый узел содержит:
 * - значение типа T
 * - ссылку на предыдущий узел
 * - ссылку на следующий узел
 *
 * @param <T> тип данных, хранимых в узле
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
}
