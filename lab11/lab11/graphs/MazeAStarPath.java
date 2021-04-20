package lab11.graphs;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.zip.DeflaterInputStream;
import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private int targetX;
    private int targetY;

    private class Dict {
        private int v;
        private int priority;

        private Dict(int v) {
            this.v = v;
            this.priority = distTo[v] + h(v);
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        this.targetX = targetX;
        this.targetY = targetY;
        this.s = maze.xyTo1D(sourceX, sourceY);
        this.t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        return Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    private class NodeComparator implements Comparator<Dict> {
        @Override
        public int compare(Dict o1, Dict o2) {
            return o1.priority - o2.priority;
        }
    }


    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        PriorityQueue<Dict> q = new PriorityQueue<>(new NodeComparator());
        Dict currVertex =  new Dict(s);
        q.add(currVertex);
        marked[s] = true;
        while (!q.isEmpty()) {
            currVertex = q.poll();
            for (int w : maze.adj(currVertex.v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = currVertex.v;
                    distTo[w] = distTo[currVertex.v] + 1;
                    announce();
                    q.add(new Dict(w));
                    if (w == t) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

