package hw2;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

import java.util.Random;

public class PercolationStats {

    private double[] XtFractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        Percolation P = pf.make(N);
        XtFractions = new double[N * N];

        for (int i = 0; i <= T; i += 1) {
            while (! P.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                P.open(row, col);
            }
            int NumOfOpen = P.numberOfOpenSites();
            XtFractions[i] = (double) NumOfOpen / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(XtFractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(XtFractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(XtFractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(XtFractions.length);
    }

    public static void main(String[] args) {
        int N = 400;
        int T = 10000;
        Stopwatch sw = new Stopwatch();
        PercolationFactory PF = new PercolationFactory();
        PercolationStats PS = new PercolationStats(N, T, PF);
        StdOut.printf("N_%d, T_%d, cost time: %.2f", N, T, sw.elapsedTime());
    }
}
