package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private int moves = 0;
    private ArrayList<WorldState> solution = new ArrayList<>();

    //Create SearchNode
    private class SearchNode {
        private WorldState state;
        private int moves;
        private SearchNode pre;

        private SearchNode(WorldState state, int moves, SearchNode pre) {
            this.state = state;
            this.moves = moves;
            this.pre = pre;
        }
    }


    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode n1, SearchNode n2) {
            int n1EDtg = getEDtg(n1);
            int n2EDtg = getEDtg(n2);
            int n1Priority = n1EDtg + n1.moves;
            int n2Priority = n2EDtg + n2.moves;
            return n1Priority - n2Priority;
        }
    }

    private int getEDtg(SearchNode N) {
        return N.state.estimatedDistanceToGoal();
    }

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        SearchNode currentNode = new SearchNode(initial, 0, null);
        pq.insert(currentNode);
        while (!pq.isEmpty()) {
            currentNode = pq.delMin();
            if (currentNode.state.isGoal()) {
                break;
            }
            for (WorldState w : currentNode.state.neighbors()) {
                //if (w != currentNode.pre.state && currentNode.pre != null) {
                //    pq.insert(new SearchNode(w, currentNode.moves + 1, currentNode));
                //}
                if (currentNode.pre != null && w.equals(currentNode.pre.state)) {
                    continue;
                }
                pq.insert(new SearchNode(w, currentNode.moves + 1, currentNode));
            }
        }

        ArrayDeque<WorldState> p = new ArrayDeque<>();
        //Stack<WorldState> path = new Stack<>();
        //for (SearchNode n = currentNode; n != null; n = n.pre) {
        //    path.push(n.state);
        //}
        //while (!path.isEmpty()) {
        //    solution.add(path.pop());
        //}
        SearchNode n = currentNode;
        while (n != null) {
            p.addLast(n.state);
            n = n.pre;
        }
        while (!p.isEmpty()) {
            solution.add(p.removeFirst());
        }
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }

}
