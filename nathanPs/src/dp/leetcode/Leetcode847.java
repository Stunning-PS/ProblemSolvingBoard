package dp.leetcode;

import java.util.*;

public class Leetcode847 {


    public int shortestPathLength(int[][] graph) {
        int N = graph.length;

        Queue<Tuple> queue = new LinkedList<>();

        // guaranteed to be path with lowest cost
        Set<Tuple> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int tmp = (1 << i);
            set.add(new Tuple(tmp, i, 0));
            queue.add(new Tuple(tmp, i, 1));
        }

        while (!queue.isEmpty()) {
            // 각 노드를 시작점으로 잡고, 순회 진행.
            Tuple curr = queue.remove();

            // 모든 노드를 방문 했다면, 현재까지 cost(방문 횟수) 리턴
            if (curr.bitMask == (1 << N) - 1) {
                return curr.cost - 1;
            } else {
                int[] neighbors = graph[curr.curr];

                for (int v : neighbors) {
                    int bitMask = curr.bitMask;
                    // bit 연산자(or)를 활용해 현재까지 방문한 경로 비트로 표현.
                    bitMask = bitMask | (1 << v);

                    Tuple tuple = new Tuple(bitMask, v, 0);

                    if (!set.contains(tuple)) {
                        // 기존에 존재하지 않았던 경로라면, cost를 증가시키고 queue에 넣기.
                        queue.add(new Tuple(bitMask, v, curr.cost + 1));
                        set.add(tuple);
                    }
                }
            }
        }

        return -1;
    }

    private static class Tuple {
        // current visited bit mask
        int bitMask;
        int curr;
        int cost;

        public Tuple(final int bitMask, final int curr, final int cost) {
            this.bitMask = bitMask;
            this.curr = curr;
            this.cost = cost;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Tuple tuple = (Tuple) o;
            return bitMask == tuple.bitMask &&
                    curr == tuple.curr &&
                    cost == tuple.cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(bitMask, curr, cost);
        }
    }

}