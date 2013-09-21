import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] a = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            a[i] = new Point(x, y);
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < a.length; i++) {
            a[i].draw();
        }
//        System.out.println();
        int rootIndex = 0;
        while (rootIndex < a.length - 3) {
            Point root = a[rootIndex];
            Arrays.sort(a, rootIndex, a.length, root.SLOPE_ORDER);
//            for (int j = 0; j < a.length; j++) {
//                System.out.println(a[j] + " " + root.slopeTo(a[j]));
//            }
//            System.out.println();
            
            double slope = root.slopeTo(a[rootIndex + 1]);
            int n = 2;
            for (int j = rootIndex + 2; j <= a.length; j++) {
                if (j < a.length && root.slopeTo(a[j]) == slope) {
                    // on the line
                    n++;
                    continue;
                } else if (n >= 4) {
                    for (int k = 1; k < n; k++) {
                        swap(a, ++rootIndex, j - n + k);
                    }
                    Arrays.sort(a, rootIndex - n + 1, rootIndex + 1);
                    for (int i = rootIndex - n + 1; i <= rootIndex; i++) {
                        StdOut.print(a[i]);
                        if (i < rootIndex) {
                        	a[i].drawTo(a[i + 1]);
                            StdOut.print(" -> ");
                        } else {
                            StdOut.println();
                        }
                    }
//                    System.out.println();
                } 
                if (j < a.length) {
                    slope = root.slopeTo(a[j]);
                }
                n = 2;
            }
            rootIndex++; // ????
//            System.out.println("----");
        }
    }
    
    private static void swap(Point[] a, int i, int j) {
//        System.out.printf("swap %d %s -> %d %s\n", i, a[i], j, a[j]);
        Point tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}