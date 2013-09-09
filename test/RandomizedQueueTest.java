
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

public class RandomizedQueueTest extends TestCase {
    @Test
    public void testEmpty() throws Exception {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        assertEmpty(q);
    }

    @Test
    public void testRandomAddDelete() throws Exception {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        int N = 1000;
        Set<Integer> added = new HashSet<>(N);
        for (int i = 0; i < N; i++) {
            added.add(i);
            q.enqueue(i);
        }
        assertEquals(N, q.size());
        for (int elem : q) {
            assertTrue(added.contains(elem));
        }
        for (int i = 0; i < N; i++) {
            assertTrue(added.remove(q.dequeue()));
        }
        assertEmpty(q);
        assertTrue(added.isEmpty());
        
        for (int i = 0; i < N; i++) {
            added.add(i);
            q.enqueue(i);
        }
        Set<Integer> sampled = new HashSet<>(N);
        for (int i = 0; sampled.size() < N && i < N * N; i++) {
            sampled.add(q.sample());
        }
        assertEquals(N, q.size());
        assertEquals(N, sampled.size());
    }
    
    private void assertEmpty(RandomizedQueue<Integer> d) {
        assertTrue(d.isEmpty());
        assertEquals(0, d.size());
        try {
            d.dequeue();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException expected) {
            // expected
        }
        try {
            d.sample();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException expected) {
            // expected
        }
        Iterator<Integer> iterator = d.iterator();
        assertFalse(iterator.hasNext());
        try {
            iterator.next();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException expected) {
            // expected
        }
        try {
            iterator.remove();
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
            // expected
        }
    }
}