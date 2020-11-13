public class LinkedListDeque<T> {
    private class Deque {
        public Deque pre;
        public T item;
        public Deque after;

        public Deque(Deque a, T b, Deque c) {
            pre = a;
            item = b;
            after = c;

        }
    }
    private Deque first;
    private int size;

    public LinkedListDeque() {
        first = new Deque(null,null,null);
        size = 0;
    }
    public void addFirst(T item) {
        first = new Deque(null,item,null);

    }
    public void addLast(T item) {

    }
    public boolean isEmpty() {

    }
    public int size() {


    }
    public void printDeque() {

    }
    public T removeFirst() {

    }
    public T get(int index) {

    }
}