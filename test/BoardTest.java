import junit.framework.TestCase;

import org.junit.Test;

public class BoardTest extends TestCase {

    @Test
    public void testHamming() throws Exception {
        Board b = new Board(new int[][] { new int[] { 0, 1, 3 },
                new int[] { 4, 2, 5 }, new int[] { 7, 8, 6 } });
        assertEquals(4, b.hamming());
    }

    @Test
    public void testManhattan() throws Exception {
        Board b = new Board(new int[][] { new int[] { 4, 1, 3 },
                new int[] { 0, 2, 5 }, new int[] { 7, 8, 6 } });
        assertEquals(5, b.manhattan());
    }

    @Test
    public void testManhattan0() throws Exception {
        Board b = new Board(new int[][] { new int[] { 1, 2, 3 },
                new int[] { 4, 5, 6 }, new int[] { 7, 8, 0 } });
        assertEquals(0, b.manhattan());
    }
}
