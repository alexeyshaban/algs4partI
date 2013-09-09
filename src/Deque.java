import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private int N;          // size
    private Node<Item> first;
    private Node<Item> last;
    
    /** construct an empty deque */
    public Deque() {
    }

    /** is the deque empty? */
    public boolean isEmpty() {
        return N == 0;
    } 

    /** return the number of items on the deque */
    public int size() {
        return N;
    } 

    /** insert the item at the front */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("add null");
        }
        if (first == null) {
            first = new Node<Item>(item);
            last = first;
        } else {
            Node<Item> node = new Node<Item>(item, null, first);
            first.prev = node;
            first = node;
        }
        N++;
    } 

    /** insert the item at the end */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("add null");
        }
        if (last == null) {
            last = new Node<Item>(item);
            first = last;
        } else {
            Node<Item> node = new Node<Item>(item, last, null);
            last.next = node;
            last = node;
        }
        N++;
    } 
    
    /** delete and return the item at the front */
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("empty");
        }
        Item result = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first.next.prev = null;
            first = first.next;
        }
        N--;
        return result;
    } 

    /** delete and return the item at the end */
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException("empty");
        }
        Item result = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last.prev.next = null;
            last = last.prev;
        }
        N--;
        return result;
    } 

    /** return an iterator over items in order from front to end */
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(first);
    } 
    
    private static final class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = new Node<Item>(null, null, first);
        }
        
        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;
        public Node(T item) {
            this.item = item;
        }
        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}