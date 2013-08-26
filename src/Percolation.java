public class Percolation {

    private int N; // dimensions
    private int sites; // total number of sites
    private boolean[][] open;
    private QuickFindUF uf;
    
    /** create N-by-N grid, with all sites blocked */
    public Percolation(int N) {
        this.N = assertPositive(N);
        sites = N * N + 2 /* virtual nodes */;
        open = new boolean[N][N];
        uf = new QuickFindUF(sites);
        // connect virtual top site with the 1st row
        for (int i = 1; i <= N; i++) {
            uf.union(0, i);
        }
        // connect virtual bottom site with the last row
        for (int i = 1; i <= N; i++) {
            uf.union(sites - 1, sites - 2 - N + i);
        }
    }

    private int getUFIndex(int i, int j) {
        return (i - 1) * N + j;
    }
    
    private void union(int i1, int j1, int i2, int j2) {
        open[i1 - 1][j1 - 1] = true;
        if (isOpen(i2, j2)) {
            uf.union(getUFIndex(i1, j1), getUFIndex(i2, j2));
        }
    }
    
    /** open site (row i, column j) if it is not already */
    public void open(int i, int j) {
        assertInRange(i, 1, N);
        assertInRange(j, 1, N);
        if (isOpen(i, j)) {
            return;
        }
        if (i > 1) {
            if (j > 1) {
                union(i, j, i, j - 1);
            }
            union(i, j, i - 1, j);
        }
        if (i < N) {
            if (j < N) {
                union(i, j, i, j + 1);
            }
            union(i, j, i + 1, j);
        }
    }

    /** is site (row i, column j) open? */
    public boolean isOpen(int i, int j) {
        assertInRange(i, 1, N);
        assertInRange(j, 1, N);
        return open[i - 1][j - 1];
    }

    /** is site (row i, column j) full? */
    public boolean isFull(int i, int j) {
        assertInRange(i, 1, N);
        assertInRange(j, 1, N);
        return isOpen(i, j) && uf.connected(0, getUFIndex(i, j));
    }

    /** does the system percolate? */
    public boolean percolates() {
        return uf.connected(0, sites - 1);
    }

    static int assertPositive(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("out of bounds");
        }
        return n;
    }
    
    static int assertInRange(int n, int lo, int hi) {
        if (lo > hi || n < lo || n > hi) {
            throw new IllegalArgumentException("out of bounds");
        }
        return n;
    }
}
