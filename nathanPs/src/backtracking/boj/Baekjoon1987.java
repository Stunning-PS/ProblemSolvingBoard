package backtracking.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1987 {

    private static char[][] board;

    private static int maxCount = Integer.MIN_VALUE;

    private static int cnt = 0;

    // 상하좌우 이동하는 배열 생성.
    private static int[][] dxdy = new int[][]{
            {0,1}, {0, -1},
            {1, 0}, {-1, 0}
    };

    private static boolean[] visited = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = br.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);
        board = new char[r][c];

        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        // 시작점 방문 체크
        visited[board[0][0] - 'A'] = true;
        backTracking(0,0);

        System.out.println(maxCount);
    }

    private static void backTracking(final int x, final int y) {
        // 방문마다 최대 방문 횟수 업데이트.
        cnt++;
        maxCount = Math.max(cnt, maxCount);

        // 상하좌우로 DFS 진행.
        for (int i = 0; i < 4; i++) {
            int dx = x + dxdy[i][0];
            int dy = y + dxdy[i][1];

            // 구성된 board의 범위를 초과하는 경우 pass.
            if (dx < 0 || dy < 0 || dx >= board.length || dy >= board[0].length) {
                continue;
            }

            // 방문한 적 없는 알파벳이라면, 탐색 진행.
            if (!visited[board[dx][dy] - 'A']) {
                visited[board[dx][dy] - 'A'] = true;
                backTracking(dx, dy);
                visited[board[dx][dy] - 'A'] = false;
                cnt--;
            }
        }
    }
}