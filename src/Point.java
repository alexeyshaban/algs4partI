import java.util.Comparator;


public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(slopeTo(o1), slopeTo(o2));
        }
    };
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point that) {
        if (y == that.y)
            return compare(x, that.x);
        else
            return compare(y, that.y);
    }

    private int compare(int a, int b) {
        if (a < b) {
            return -1;
        }
        if (a == b) {
            return 0;
        }
        return 1;
    }

    public double slopeTo(Point that) {
        if (y == that.y) {
            if (x == that.x) {
                return Double.NEGATIVE_INFINITY;
            }
            return +0.0;
        }
        if (x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        return ((double) (that.y - y)) / (that.x -x);
    }
}