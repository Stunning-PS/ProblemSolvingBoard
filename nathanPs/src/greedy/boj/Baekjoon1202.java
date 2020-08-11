package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");

        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        List<Jewelry> jewelries = new ArrayList<>();
        List<Integer> bags = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] mv = br.readLine().split(" ");
            jewelries.add(new Jewelry(Integer.parseInt(mv[0]), Integer.parseInt(mv[1])));
        }

        for (int i = 0; i < k; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(bags);
        Collections.sort(jewelries);
        PriorityQueue<Integer> answerQueue = new PriorityQueue<>((x, y) -> Integer.compare(y, x));

        long ans = 0;
        int idx = 0;
        for (int i = 0; i < k; i++) {

            while (idx < n && bags.get(i) >= jewelries.get(idx).m) {
                answerQueue.add(jewelries.get(idx).v);
                idx++;
            }

            if (!answerQueue.isEmpty()) {
                ans += answerQueue.poll();
            }
        }

        System.out.println(ans);
    }

    private static class Jewelry implements Comparable<Jewelry> {
        int m;
        int v;

        public Jewelry(final int m, final int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(final Jewelry jewelry) {
            return this.m - jewelry.m;
        }

        @Override
        public String toString() {
            return String.format("m : %d, v : %d", m, v);
        }
    }
}