import java.util.Arrays;

public class Board {
	private int N;
	private int[][] blocks;
	
	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j) {}
	public Board(int[][] blocks) {
		this.N = blocks.length;
		this.blocks = blocks;
	}

	// board dimension N
	public int dimension() {
		return N;
	}

	// number of blocks out of place
	public int hamming() {
		int result = 0;
		for (int i = 0; i < blocks.length; i++) {
	        for (int j = 0; j < blocks.length; j++) {
	            if (blocks[i][j] != i * N + j + 1) {
	                result++;
                }
            }
        }
		return --result; // handle 0
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int result = 0;
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				int b = blocks[i][j];
				if (b == 0) {
	                continue;
                }
				result += Math.abs((b - 1) / N - i);
				result += Math.abs((b - 1) % N - j);
			}
		}
		return result;
	}

	// is this board the goal board?
	public boolean isGoal() {
		return hamming() == 0;
	}

	// a board obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		return null;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		return null;
	}

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + Arrays.hashCode(blocks);
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    Board other = (Board) obj;
	    if (!Arrays.deepEquals(blocks, other.blocks))
		    return false;
	    return true;
    }

	// string representation of the board (in the output format specified
	// below)
	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", blocks[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();}
}