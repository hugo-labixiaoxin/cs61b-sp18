public class AList <Item> {
    private Item[] items;
    private int size;

    /**create en empty list*/
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    /**resize the full array to the wanted length*/
    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /**the last position should be the size then insert x to it*/
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size*2);
        }
        items[size] = x;
        size += 1;
    }

    public Item getLast() {
        return items[size-1];
    }

    public Item get(ith) {
        return items[i];
    }

    public Item size() {
        return size;
    }

    public Item removeLast() {
        int x = getLast();
        items[size-1] = null;
        size -= 1;
        return x;
    }

}