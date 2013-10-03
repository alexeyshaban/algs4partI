
public class Solver {

    private MinPQ<SearchNode> q1 = new MinPQ<SearchNode>();
    private MinPQ<SearchNode> q2 = new MinPQ<SearchNode>();
    private Stack<Board> solution = null;
    private boolean solved;
    
    // find a solution to the initial board (using the A* algorithm) {}
    public Solver(Board initial) {
        q1.insert(new SearchNode(initial, null, 0));
        q2.insert(new SearchNode(initial.twin(), null, 0));
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solution() != null;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (solution() == null) {
            return -1;
        }
        return solution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (solved) {
            return solution;
        }
        while (true) {
            SearchNode lastTwin = q2.delMin();
            if (lastTwin.board.isGoal()) {
                solved = true;
                solution = null;
                return solution;
            }
            SearchNode last = q1.delMin();
            if (last.board.isGoal()) {
                solved = true;
                solution = new Stack<Board>();
                solution.push(last.board);
                while (last.prev != null) {
                    last = last.prev;
                    solution.push(last.board);
                }
                return solution;
            } else {
                addNeighbors(last, q1);
                addNeighbors(lastTwin, q2);
            }
        }
    }

    private static void addNeighbors(SearchNode last, MinPQ<SearchNode> q) {
        for (Board neighbor : last.board.neighbors()) {
            SearchNode x = new SearchNode(neighbor, last, last.steps + 1);
            if (last.prev == null || !x.board.equals(last.prev.board)) {
                q.insert(x);
            }
        }
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
    
    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode prev;
        private int steps;
        private int priority;
        public SearchNode(Board board, SearchNode prev, int steps) {
            this.board = board;
            this.prev = prev;
            this.steps = steps;
            this.priority = steps + board.manhattan();
        }
        @Override
        public int compareTo(SearchNode o) {
            return new Integer(priority).compareTo(o.priority);
        }
    }
}