/** @author : Lu
 * @date 2020/5
 *     CS61B proj1a create Deque
 */

public class LinkedListDeque <T> implements Deque<T> {

    /**  TNode private class */
    /* should be private */
    private class TNode {
        public TNode prev;
        public T item;
        public TNode next;

        public TNode(TNode p, T t, TNode n){
            this.prev = p;
            this.item = t;
            this.next = n;
        }
    }

    /* create sentinel node*/
    private TNode Sentinel;
    /* record size */
    private int size;

    /** Constructor */
    /* Creates an empty List. */
    public LinkedListDeque() {
        Sentinel = new TNode(null,null,null);
        Sentinel.next = Sentinel;
        Sentinel.prev = Sentinel;
        size = 0;
    }

    /** Creates a deep copy of other */
    public LinkedListDeque(LinkedListDeque other){//LinkedListDeque<T> other
        size = other.size;
        LinkedListDeque p = new LinkedListDeque();
        for(int i= 0; i<size; i++ ){
            p.addLast((T) other.get(i));// (T) is cast, since type of other is unknown
            //在函数内部不需要指定p.addLast
        }
        Sentinel = p.Sentinel;
    }


    /** Adds an item of type T to the front of the deque*/
    @Override
    public void addFirst(T item){
        size += 1;
        TNode NewFirst = new TNode(Sentinel, item, Sentinel.next);
        Sentinel.next = NewFirst;
        NewFirst.next.prev = NewFirst;
    }

    /** Adds an item of type T to the back of the deque */
    @Override
    public void addLast(T item){
        size += 1;
        TNode NewLast = new TNode(Sentinel.prev, item, Sentinel);
        Sentinel.prev.next = NewLast;
        Sentinel.prev = NewLast;
    }

    /** Returns true if deque is empty, false otherwise */
    @Override
    public boolean isEmpty(){
        return (size == 0);
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    @Override
    public void printDeque(){
        TNode p = Sentinel.next;
        System.out.print("The List contains: ");
        while (p.next != Sentinel.next){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println(".");
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null */
    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T removeFirst = Sentinel.next.item;
        Sentinel.next = Sentinel.next.next;
        Sentinel.next.prev = Sentinel;
        size -= 1;
        return removeFirst;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null */
    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }
        T removeLast = Sentinel.prev.item;
        Sentinel.prev = Sentinel.prev.prev;
        Sentinel.prev.next = Sentinel;
        size -= 1;
        return removeLast;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!*/
    @Override
    public T get(int index){
        if(index + 1 > size){
            return null;
        }
        TNode p = Sentinel.next;
        while (index> 0){
            index -=1;
            p=p.next;
        }
        return p.item;
    }

    /** Same as get, but uses recursion */
    private T getRecursive(int index,TNode t){
        if(index==0){
            return t.item;//recursion最后return的是最后一层的值
        }
        return getRecursive(index-1, t.next);
    }

    public T getRecursive(int index){
        if(index>size){
            return null;
        }
        return getRecursive(index,Sentinel.next);
    }

    /** test
    public static void main (String args[]){
        LinkedListDeque<Integer> L = new LinkedListDeque<Integer> ();
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(15);
        L.addLast(20);

        boolean Empty = L.isEmpty();
        System.out.println("The list is empty? "+ Empty+".");

        int size = L.size();
        System.out.println("The list size is: "+ size+".");

        L.printDeque();

        //System.out.println("The removed first item is: "+ L.removeFirst()+".");
        //L.printDeque();

       // System.out.println("The removed last item is: "+ L.removeLast()+".");
        //L.printDeque();

        System.out.println("The item is: "+ L.get(1)+".");

        LinkedListDeque<Integer> NewDeque = new LinkedListDeque<Integer> (L);
        NewDeque.printDeque();

        System.out.println("The item is: "+ L.getRecursive(5)+".");
    }
     */
}