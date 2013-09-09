import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int N;            // number of elements
    
    /** construct an empty randomized queue */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    /** is the queue empty? */
    public boolean isEmpty() {
        return N == 0;
    }

    /** return the number of items on the queue */
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        System.arraycopy(a, 0, temp, 0, N);
        a = temp;
    }
    
    /** add the item */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("add null");
        }
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    /** delete and return a random item */
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException("empty");
        }
        int random = StdRandom.uniform(N);
        Item result = a[random];
        a[random] = a[N - 1];
        a[N - 1] = null;
        N--;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return result;
    }

    /** return (but do not delete) a random item */
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException("empty");
        }
        return a[StdRandom.uniform(N)];
    }

    /** return an independent iterator over items in random order */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private final class QueueIterator implements Iterator<Item> {
        private int i = 0;
        private int[] idx;
        
        public QueueIterator() {
            idx = new int[N];
            for (int j = 0; j < idx.length; j++) {
                idx[j] = j;
            }
            StdRandom.shuffle(idx);
        }
        
        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[idx[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }
    }
}