public class PercolationStats {

    private double[] results;
    
    /** perform T independent computational experiments on an N-by-N grid */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("out of bounds");
        }
        results = new double[T];
        for (int i = 0; i < T; i++) {
            results[i] = runExperiment(N);
        }
    }

    private double runExperiment(int N) {
        Percolation p = new Percolation(N);
        int count = 0;
        while (!p.percolates()) {
            int random = StdRandom.uniform(N * N);
            int i = random / N + 1;
            int j = random % N + 1;
            if (!p.isOpen(i, j)) {
                p.open(i, j);
                count++;
            }
        }
        return ((double) count) / N / N;
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(results);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(results);
    }

    /** returns lower bound of the 95% confidence interval */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    /** returns upper bound of the 95% confidence interval */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
//        Stopwatch stopwatch = new Stopwatch();
        PercolationStats stats = new PercolationStats(N, T);
//        StdOut.printf("Elapsed: %f\n", stopwatch.elapsedTime());
        StdOut.printf("mean                    = %.16f\n", stats.mean());
        StdOut.printf("stddev                  = %.16f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = %.16f, %.16f",
                stats.confidenceLo(), stats.confidenceHi());
    }
}