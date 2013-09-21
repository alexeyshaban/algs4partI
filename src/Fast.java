import java.util.Arrays;
import java.util.LinkedList;

public class Fast {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] sortable = new Point[N];
        Point[] original = new Point[N];
        Slopes[] slopes = new Slopes[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            sortable[i] = new Point(x, y);
            original[i] = sortable[i];
            slopes[i] = new Slopes();
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < sortable.length; i++) {
            sortable[i].draw();
        }
        if (N < 4) {
            return;
        }
        for (int r = 0; r < sortable.length; r++) {
            Point root = original[r];
            Arrays.sort(sortable, root.SLOPE_ORDER);
            
            double slope = root.slopeTo(sortable[1]);
            int n = 2;
            for (int j = 2; j <= sortable.length; j++) {
                if (j < sortable.length && root.slopeTo(sortable[j]) == slope) {
                    // on the line
                    n++;
                    continue;
                } else if (n >= 4) {
                    if (!slopes[r].s.contains(slope)) {
                        Point[] line = new Point[n];
                        line[0] = root;
                        addSlope(original, slopes, slope, root);
                        for (int k = 1; k < n; k++) {
                            Point current = sortable[j - n + k];
                            line[k] = current;
                            addSlope(original, slopes, slope, current);
                        }
                        Arrays.sort(line);
                        line[0].drawTo(line[n - 1]);
                        for (int i = 0; i < n; i++) {
                            StdOut.print(line[i]);
                            if (i < n - 1) {
                                StdOut.print(" -> ");
                            } else {
                                StdOut.println();
                            }
                        }
                    }
                } 
                if (j < sortable.length) {
                    slope = root.slopeTo(sortable[j]);
                }
                n = 2;
            }
        }
//        new Point(13000, 0).drawTo(new Point(5000, 12000));
    }

    private static void addSlope(Point[] original, Slopes[] slopes,
            double slope, Point current) {
        for (int i = 0; i < original.length; i++) {
            if (current.equals(original[i])) {
                slopes[i].s.addLast(slope);
            }
        }
    }
    
    private static class Slopes {
        private LinkedList<Double> s = new LinkedList<Double>();
    }
}