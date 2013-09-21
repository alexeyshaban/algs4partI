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
        for (int i = 0; i < a.length; i++) {
            a[i].draw();
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < a.length; k++) {
                    if (k == i || k == j 
                            || a[i].compareTo(a[j]) != a[j].compareTo(a[k])) {
                        continue;
                    }
                    for (int l = 0; l < a.length; l++) {
                        if (l == k || l == j || l == i
                                || a[j].compareTo(a[k]) != a[k].compareTo(a[l])) {
                            continue;
                        }
                        if (a[i].slopeTo(a[j]) == a[j].slopeTo(a[k])
                                && a[j].slopeTo(a[k]) == a[k].slopeTo(a[l])) {
                            StdOut.printf("%s -> %s -> %s -> %s\n",
                                    a[i], a[j], a[k], a[l]);
                            a[i].drawTo(a[j]);
                            a[j].drawTo(a[k]);
                            a[k].drawTo(a[l]);
                        }
                    }
                }
            }
        }
    }
}