package greedy.programmers;

import java.util.*;

public class ConnectingIsland {

    private int[] parent;

    private PriorityQueue<Island> queue;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        queue = new PriorityQueue<>();

        for (int[] cost : costs) {
            queue.offer(new Island(cost[0], cost[1], cost[2]));
        }

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        while (!queue.isEmpty()) {
            Island poll = queue.poll();

            // circle 체크
            if (find(poll.from) == find(poll.to)) {
                continue;
            }

            // circle이 아니라면 합쳐주고, cost 더함.
            union(poll.from, poll.to);
            answer += poll.cost;
        }

        return answer;
    }

    private int find(final int from) {
        if (parent[from] == from) {
            return from;
        }

        return parent[from] = find(parent[from]);
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    private class Island implements Comparable<Island> {
        int from;
        int to;
        int cost;

        public Island(final int from, final int to, final int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Island o) {
            return this.cost - o.cost;
        }
    }
}