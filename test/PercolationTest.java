import junit.framework.TestCase;

import org.junit.Test;

public class PercolationTest extends TestCase {

    @Test
    public void testCreation() throws Exception {
        int N = 10;
        Percolation p = new Percolation(N);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                assertFalse(p.isOpen(i, j));
                assertFalse(p.isFull(i, j));
            }
        }
        assertFalse(p.percolates());
    }
    
    @Test
    public void testLeft() throws Exception {
        testColumn(1);
    }
    
    @Test
    public void testMiddle() throws Exception {
        testColumn(2);
    }
    
    @Test
    public void testRight() throws Exception {
        testColumn(3);
    }
    
    public void testColumn(int j) throws Exception {
        Percolation p = new Percolation(3);
        p.open(1, j);
        assertTrue(p.isOpen(1, j));
        assertTrue(p.isFull(1, j));
        assertFalse(p.isFull(2, j));
        assertFalse(p.percolates());
        
        p.open(2, j);
        assertTrue(p.isOpen(2, j));
        assertTrue(p.isFull(2, j));
        assertFalse(p.isFull(3, j));
        assertFalse(p.percolates());
        
        p.open(3, j);
        assertTrue(p.isOpen(3, j));
        assertTrue(p.isFull(3, j));
        assertTrue(p.percolates());
    }
    
    @Test
    public void test4x4() throws Exception {
        /*
         * ++--
         * -++-
         * +-++
         * +--+
         */
        Percolation p = new Percolation(4);
        p.open(1, 1);
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 3);
        p.open(3, 1);
        p.open(3, 3);
        p.open(3, 4);
        assertFalse(p.isFull(3, 1));
        assertTrue(p.isFull(3, 4));
        p.open(4, 1);
        assertFalse(p.isFull(4, 1));
        assertFalse(p.percolates());
        p.open(4, 4);
        assertTrue(p.isFull(4, 4));
        assertTrue(p.percolates());
    }
}
