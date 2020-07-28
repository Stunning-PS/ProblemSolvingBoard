package bfsdfs.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon7576 {

    // 상하 좌우 이동시켜주는 배열.
    private static int[][] dxdy = new int[][] {
            {0,1},{1,0},
            {0,-1},{-1,0}
    };

    private static int[][] box;

    private static int m;

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        m = Integer.parseInt(nm[0]);
        n = Integer.parseInt(nm[1]);

        box = new int[n][m];


        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(row[j]);
            }
        }

        // BFS Queue
        Queue<Pair> queue = new LinkedList<>();


        // 초기 입력값에 익은 토마토 위치를 Queue에 저장.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 1) {
                    queue.add(new Pair(i, j));
                }
            }
        }

        System.out.println(bfs(queue));
    }

    private static int bfs(Queue<Pair> queue) {
        int max = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();

            // 상하 좌우 이동.
            for (int[] move : dxdy) {
                int dx = poll.x + move[0];
                int dy = poll.y + move[1];

                // 배열의 범위를 초과하는 경우와 토마토가 없는 위치인 경우 무시.
                if (dx < 0 || dy < 0 || dx >= n || dy >= m || box[dx][dy] == -1) {
                    continue;
                }

                // 아직 익지 않은 토마토라면, box에 현재 일 수 반영.
                if (box[dx][dy] == 0) {
                    box[dx][dy] = box[poll.x][poll.y] + 1;
                    queue.add(new Pair(dx, dy));

                }
            }
        }

        // 모든 탐색이 끝난 후 아직 익지 않은 토마토가 있다면, -1 리턴.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    return -1;
                }

                max = Math.max(max, box[i][j]);
            }
        }

        return  max-1;
    }


    // 배열의 위치를 저장하는 Pair 객체.
    static class Pair {
        int x;
        int y;

        public Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
