/** @author : Lu
 * @date 2020/5/
 *     CS61B proj1a create ArrayDeque
 */

public class ArrayDeque<Item> implements Deque<Item>{
    private int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque.*/
    public ArrayDeque(){
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Creates a deep copy of other.*/
    public ArrayDeque(ArrayDeque other){
        items = (Item[]) new Object[other.items.length];
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;

        int beginIndex = plusOne(other.nextFirst,items);
        for(int i = 0; i<size; i++){
            items[beginIndex] = (Item) other.get(i);//cast to (Item)
            beginIndex = plusOne(beginIndex,items);
        }
    }

    /** return true if the array is empty */
    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    /** calculate the index of nextLast and beginIndex */
    private int plusOne(int index,Item[] items){
        if(index + 1 >= items.length){
            return 0;
        }
        return index+1;
    }

    /** calculate the index of nextFirst and LastIndex*/
    private int minusOne(int index,Item[] items){
        if(index - 1 < 0){
            return items.length-1;
        }
        return index-1;
    }

    /** calculate the real index */
    private int realIndex (int index){
        int beginIndex = plusOne(nextFirst,items);
        return (beginIndex+index)%items.length;
    }

    /** up sizes the underlying array to the target capacity. */
    private void resize(int capacity){
        Item[] a = (Item []) new Object[capacity];
        int lastIndex= nextLast;
        int index = 0;
        for(int i=0; i< size; i+=1){
            /* nextLast is always behind the last item, nextFirst is always in front of the first item */
            /* locate the last item*/
            nextLast = minusOne(nextLast,a);
            index = nextLast;
            /* copy the items from last to front*/
            a[index] = (Item) get(size-1-i);//get(i),i is the index in real ascending order.
            //nextLast = index;
        }
        items = a;
        nextLast = lastIndex;
        nextFirst = minusOne(index,items);
    }

    /** down sizes the underlying array to the target capacity. */
    private void downSize(int capacity){
        Item[] a = (Item []) new Object[capacity];
        int index = 0;
        for(int i=size-1; i>=0; i-=1){
            /* copy the items from last to front*/
            a[i] = (Item) get(i);//get(i),i is the index in real ascending order.
        }
        items = a;
        nextLast = size;
        nextFirst = capacity-1;
    }

    /** Inserts X into the back of the list. */
    @Override
    public void addLast(Item x) {
        if(size == items.length){
            resize(size*2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = plusOne(nextLast,items);
    }

    /** Inserts X into the front of the list. */
    @Override
    public void addFirst(Item x) {
        if(size == items.length){
            resize(size*2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = minusOne(nextFirst,items);
    }

    /** Gets the ith item in the list (0 is the front). */
    @Override
    public Item get(int i) {
        if(isEmpty()){
            return null;
        }
        i = realIndex(i);
        return items[i];
    }

    /** Returns the number of items in the list. */
    @Override
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and returns deleted item. */
    private boolean testUsage() {
        return items.length >= 16 && size < (items.length / 4);
    }

    @Override
    public Item removeLast() {
        if(isEmpty()){
            return null;
        }
        //items[size-1] = 0; not necessary when int, but necessary for object because there's a link
        int lastIndex = minusOne(nextLast,items);
        Item x = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size -= 1;
        while(testUsage()){
            downSize(items.length/2);
        }
        return x;
    }

    /** Deletes item from front of the list and returns deleted item. */
    @Override
    public Item removeFirst() {
        if(size==0){
            return null;
        }
        int FirstIndex = plusOne(nextFirst,items);
        Item x = items[FirstIndex];
        items[FirstIndex] = null;
        nextFirst = FirstIndex;
        size -= 1;
        while(testUsage()){
            downSize(items.length/2);
        }
        return x;
    }

    /** print out the Deque */
    @Override
    public void printDeque(){
        if(size==0){
            System.out.println("empty");
        }
        for(int i=0; i<size; i++){
            System.out.print((Item) get(i)+" ");
        }
    }

    /** test
    public static void main(String[] args){
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addFirst(0);
        L.addLast(1);
        L.removeFirst();
        //ArrayDeque<Integer> N = new ArrayDeque<Integer>(L);
        //System.out.println(L.removeFirst());
        //System.out.println(L.removeLast());

        //L.printDeque();
    }*/

}
