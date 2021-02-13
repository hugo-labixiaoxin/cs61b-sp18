/**
public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[100];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size*2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size*2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldIndex = (nextFirst+1) % items.length;
        for (int newIndex = 0; newIndex <= size; newIndex++) {
            a[newIndex] = items[oldIndex];
            oldIndex = (oldIndex+1) % items.length;
        }
        items = a;
        nextFirst = capacity-1;
        nextLast = size;
    }

    public T removeFirst() {
        int FirstIndex = (nextFirst+1) % items.length;
        T First = items[FirstIndex];
        items[FirstIndex] = null;
        size -= 1;
        return First;
    }

    public T removeLast() {
        int LastIndex = (nextLast-1+ items.length) % items.length;
        T Last = items[LastIndex];
        items[LastIndex] = null;
        size -= 1;
        return Last;
    }

    public void printDeque() {
        int FirstIndex = (nextFirst + 1) % items.length;
        int CurrIndex = 0;
        while (CurrIndex < size) {
            System.out.print(items[FirstIndex] + " ");
            FirstIndex = (FirstIndex + 1) % items.length;
            CurrIndex += 1;
        }
    }

   public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        int currIndex = (nextFirst+1) % items.length;
        return items[(index+currIndex) % items.length];
   }

    public static void main(String[] args) {
         ArrayDeque L = new ArrayDeque();
         L.addFirst(1);
         L.addFirst(2);
         L.addFirst(3);
         L.addFirst(4);
         L.addLast(5);
         L.addLast(6);
         L.addLast(7);
         L.addLast(8);
         L.addLast(9);
         System.out.println(L.get(8));

    }

}*/
public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] newitems = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex <= size; newIndex++) {
            newitems[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newitems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) %  items.length;
    }

    private void upSize() {
        this.resize(size * 2);
    }

    private void downSize() {
        this.resize(items.length/2);
    }

    private boolean isFull() {
        return (items.length == size);
    }

    public void addFirst(T item) {
        if (isFull()) {
            upSize();
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (isFull()) {
            upSize();
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size==0);
    }

    public void printDeque() {
        if (size == items.length) {
            for (int i = plusOne(nextFirst); i < items.length; i = i + 1)
                System.out.print(items[i] + " ");
            for (int i = 0; i<nextLast;i =i+1) {
                System.out.print(items[i] + " ");
            }
        }

        for (int i=plusOne(nextFirst); i != nextLast;i=plusOne(i)) {
            System.out.print(items[i]+" ");
        }
    }


    public T removeFirst() {
        if (items.length >= 16 && items.length >= 4 * size) {
            downSize();
        }

        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return item;

    }

    public T removeLast() {
        if (items.length >= 16 && items.length >= 4 * size) {
            downSize();
        }

        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }


}