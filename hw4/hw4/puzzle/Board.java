package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private final int BLANK = 0;
    private int[][] tiles;
    private int N;

    //Constructs a board from an N-by-N array of tiles where
    // tiles[i][j] = tile at row i, column j
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    //Returns value of tile at row i, column j (or 0 if blank)
    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    //Returns the board size N
    public int size() {
        return N;
    }

    //Returns the neighbors of the current board
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private int xyTO1D(int i, int j) {
        return i * N + j + 1;
    }

    //The number of tiles in the wrong position.
    public int hamming() {
        int result = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                } else if (tileAt(i, j) != xyTO1D(i, j)) {
                    result += 1;
                }
            }
        }
        return result;
    }

    //find the xy coordinate of Num
    private int[] DToxy(int Num) {
        return new int[]{(Num - 1) / N, (Num - 1) % N};
    }

    //The sum of the Manhattan distances (sum of the vertical and horizontal distance)
    // from the tiles to their goal positions.
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != xyTO1D(i, j)) {
                    int xError = Math.abs((DToxy(xyTO1D(i, j))[0] - DToxy(tileAt(i, j))[0]));
                    int yError = Math.abs((DToxy(xyTO1D(i, j))[1] - DToxy(tileAt(i, j))[1]));
                    result += (xError + yError);
                }
            }
        }
        return result;
    }

    //Estimated distance to goal. This method should
    //simply return the results of manhattan() when submitted to Gradescope.
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    //Returns true if this board's tile values are the same position as y's
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        if (this.N != ((Board) y).N) {
            return false;
        }
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (this.tiles[i][j] != ((Board) y).tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
