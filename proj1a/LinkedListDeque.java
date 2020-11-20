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
    private Deque sentinel;
    private int size;

    /**create an empty llD*/
    public LinkedListDeque() {
        sentinel = new Deque(null,null,null);
        sentinel.pre = sentinel;
        sentinel.after = sentinel;
        size = 0;
    }

    /**add T to the front of the LLD*/
    public void addFirst(T item) {
        sentinel.after = new Deque(sentinel, item, sentinel.after);
        sentinel.after.after.pre = sentinel.after;
        size+=1;
    }

    /**add T to the last of the LLD*/
    public void addLast(T item) {
        sentinel.pre = new Deque(sentinel.pre, item, sentinel);
        sentinel.pre.pre.after = sentinel.pre;
        size+=1;
    }

    /**judge whether the LLD is empty or not*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**return the size of the LLD*/
    public int size() {
        return size;
    }

    public void printDeque() {
        Deque currentLLD = sentinel;
        while (currentLLD.after != sentinel) {
            System.out.print(currentLLD.after.item + " ");
            currentLLD = currentLLD.after;
        }
    }

    /**remove the first node of the LLD and return the item of it*/
    public  T removeFirst() {
        if (sentinel.after != sentinel) {
            T item = sentinel.after.item;
            sentinel.after = sentinel.after.after;
            sentinel.after.pre = sentinel;
            size -= 1;
            return item;
        }
        return null;
    }

    /**remove the last node of the LLD and return the item of it*/
    public T removeLast() {
        if (sentinel.pre != sentinel) {
            T item = sentinel.pre.item;
            sentinel.pre = sentinel.pre.pre;
            sentinel.pre.after = sentinel;
            size -= 1;
            return item;
        }
        return null;
    }

    /**the iteration get method*/
    public T get(int index) {
        Deque P = sentinel;
        if (size == 0 || index >= size) {
            return null;
        }
        while (sentinel.after != sentinel && index < size) {
            sentinel = sentinel.after;
            size -= 1;
        }
        return sentinel.after.item;
    }

    /**the recursion get method (not finished yet it's wrong)*/
    public T getRecursion(int index) {
        if (size== 0 || index >= size-1) {
            return null;
        } else if ((size == 1) && (index == size-1)) {
            return sentinel.after.item;
        }
        sentinel = sentinel.after;
        size -= 1;
        return getRecursion(index-1);
    }

    public static void main(String[] args) {
        LinkedListDeque P = new LinkedListDeque();
        P.addFirst(1);
        P.addLast(2);
        System.out.println(P.get(1));
    }
}