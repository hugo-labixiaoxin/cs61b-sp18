public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /** the first item(if it exists) is at sentinel.next*/
    private IntNode sentinel;
    private int size;

    /**create an empty SLList*/
    public SLList() {
        sentinel = new IntNode(6,null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(6,null);
        sentinel.next = new IntNode(x,null);
        size = 1;
    }

    public void addfirst(int x) {
        sentinel.next = new IntNode(x,sentinel.next);
        size += 1;
    }

    public int getfirst() {
        return sentinel.next.item;
    }

    public void addlast(int x) {
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x,null);
        size += 1;
    }

    public int getlast() {
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p.item;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /** a one integer list*/
        SLList L = new SLList();
        L.addfirst(9);
        L.addfirst(8);
        L.addlast(11);
        System.out.println(L.getlast());
    }
}