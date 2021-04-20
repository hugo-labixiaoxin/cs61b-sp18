package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean findCircle = false;
    private int s; // the source point
    private int t;
    private Stack<Integer> stack;
    private int start;
    private int end;

    public MazeCycles(Maze m) {
        super(m);
        s = maze.xyTo1D(1,1);
        t = maze.xyTo1D(maze.N(), maze.N());
        distTo[s] = 0;
        edgeTo[s] = s;
        stack = new Stack<>();
    }

    private void dfs(int v) {
        marked[v] = true;
        announce();
        stack.push(v);

        for (int u : maze.adj(v)) {
            if (!marked[u]) {
                distTo[u] = distTo[v] + 1;
                stack.push(u);
                dfs(u);
                if (findCircle) {
                    return;
                }
            } else {
                if (distTo[u] != distTo[v] - 1) {
                    findCircle = true;
                    start = u;
                    return;
                }
            }

        }
    }

    private void pop(int start) {
        end = stack.peek();
        while (stack.peek() != start) {
            edgeTo[stack.pop()] = stack.peek();
            announce();
        }
        edgeTo[start] = end;
        announce();
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(s);
        if(start != 0){
            pop(start);
        }
    }

    // Helper methods go here
}

