
public class Subset {

    public static void main(String[] args) {
        int N = Integer.valueOf(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            q.enqueue(string);
        }
        for (int i = 0; i < N; i++) {
            StdOut.println(q.dequeue());
        }
    }
}