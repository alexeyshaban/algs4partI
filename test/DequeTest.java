
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

public class DequeTest extends TestCase {
    @Test
    public void testEmpty() throws Exception {
        Deque<Integer> d = new Deque<>();
        assertEmpty(d);
    }
    
    @Test
    public void testAddRemoveFirst() throws Exception {
        Deque<Integer> d = new Deque<>();
        d.addFirst(2);
        assertEquals(2, (int) d.removeFirst());
        assertEmpty(d);
    }
    
    @Test
    public void testAddRemoveLast() throws Exception {
        Deque<Integer> d = new Deque<>();
        d.addLast(2);
        assertEquals(2, (int) d.removeLast());
        assertEmpty(d);
    }
    
    @Test
    public void testAddRemoveFirstLast() throws Exception {
        Deque<Integer> d = new Deque<>();
        d.addFirst(2);
        assertEquals(2, (int) d.removeLast());
        assertEmpty(d);
    }
    
    @Test
    public void testAddRemoveLastFirst() throws Exception {
        Deque<Integer> d = new Deque<>();
        d.addLast(2);
        assertEquals(2, (int) d.removeFirst());
        assertEmpty(d);
    }
    
    @Test
    public void testAddRemove() throws Exception {
        Deque<Integer> d = new Deque<>();
        for (int i = 0; i < 10; i++) {
            d.addLast(-i);
            d.addFirst(i);
        }
        assertEquals(20, d.size());
        int expected = 9;
        boolean zeroPassed = false;
        for (int elem : d) {
            assertEquals(expected, elem);
            if (expected-- == 0) {
                if (!zeroPassed) {
                    expected = 0;
                    zeroPassed = true;
                }
            }
        }
    }
    
    @Test
    public void testAddRemoveRandom() throws Exception {
        int N = 1000;
        Deque<Integer> d = new Deque<>();
        Set<Integer> added = new HashSet<>(N);
        for (int i = 0; i < N; i++) {
            added.add(i);
            if (Math.random() < 0.5) {
                d.addFirst(i);
            } else {
                d.addLast(i);
            }
        }
        assertEquals(N, d.size());
        int countI = 0;
        for (int i : d) {
            assertTrue(added.contains(i));
            countI++;
            int countJ = 0;
            for (int j : added) {
                assertTrue(added.contains(j));
                countJ++;
            }
            assertEquals(N, countJ);
        }
        assertEquals(N, countI);
        for (int i = 0; i < N; i++) {
            int removed;
            if (Math.random() < 0.5) {
                removed = d.removeFirst();
            } else {
                removed = d.removeLast();
            }
            assertTrue(added.remove(removed));
        }
        assertEmpty(d);
        assertTrue(added.isEmpty());
    }
    
    private void assertEmpty(Deque<Integer> d) {
        assertTrue(d.isEmpty());
        assertEquals(0, d.size());
        try {
            d.removeFirst();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException expected) {
            // expected
        }
        try {
            d.removeLast();
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