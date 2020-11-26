public class ArrayDeque<T> {
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

    public void resize(int capacity) {
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
        return items[(index+currIndex)];
   }
   /**
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
    */
}