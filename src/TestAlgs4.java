/*************************************************************************
 *  Compilation:  javac TestAlgs4.java
 *  Execution:    java TestAlgs4 
 *  Dependencies: StdDraw.java
 *                http://introcs.cs.princeton.edu/mac/cover.jpg
 *  
 *  Draws a blue bullseye and textbook graphic.
 * 
 *************************************************************************/

public class TestAlgs4 {
    public static void main(String[] args) {
        StdDraw.setScale(-1, 1);
        StdDraw.clear(StdDraw.BLACK);
        
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.square(0, 0, 1.06);
        
        // write some text
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0, +0.98, "Hello, world! This is a test Java program.");
        StdDraw.text(0, -0.98, "Close this window to finish the installation.");
        
        // draw the bullseye
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.filledCircle(0, 0, 0.9);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(0, 0, 0.8);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.filledCircle(0, 0, 0.7);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(0, 0, 0.6);
    }
}
