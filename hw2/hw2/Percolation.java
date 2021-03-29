package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private boolean[][] grid;
    private WeightedQuickUnionUF UF;
    private int virtualTop;
    private int virtualBottom;
    private int OpenNum;
    private int size;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        UF = new WeightedQuickUnionUF(N * N + 2);
        grid = new boolean[N][N];
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        OpenNum = 0;
        size = N;
    }

    //turns the coordinate r c into the Nth of grid
    private int xyTo1D(int r, int c) {
        return (r + 1) * (c + 1) - 1;
    }

    private void judge(int r, int c) {
        if (r < 0 || c < 0 || r >= size || c >= size) {
            throw new IllegalArgumentException();
        }
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        judge(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        OpenNum += 1;

        if (row == 0) {
            UF.union(xyTo1D(row, col), virtualTop);
        }

        if (row == size - 1) {
            UF.union(xyTo1D(row, col), virtualBottom);
        }

        if (row >= 1 && isOpen(row - 1, col)) {
            UF.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }

        if (row < size - 1 && isOpen(row + 1, col)) {
            UF.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }

        if (col >= 1 && isOpen(row, col - 1)) {
            UF.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

        if (col < size - 1 && isOpen(row, col + 1)) {
            UF.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        judge(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        judge(row, col);
        return UF.connected(xyTo1D(row, col), virtualTop);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return OpenNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return UF.connected(virtualTop, virtualBottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
