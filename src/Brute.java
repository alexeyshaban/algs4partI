import java.util.Arrays;

public class Brute {

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
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            a[i].draw();
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    for (int l = k + 1; l < a.length; l++) {
                        if (a[i].slopeTo(a[j]) == a[j].slopeTo(a[k])
                                && a[j].slopeTo(a[k]) == a[k].slopeTo(a[l])) {
                            StdOut.printf("%s -> %s -> %s -> %s\n",
                                    a[i], a[j], a[k], a[l]);
                            a[i].drawTo(a[l]);
                        }
                    }
                }
            }
        }
    }
}