package bfsdfs.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon11559 {

    private static char[][] board = new char[12][6];

    private static int[][] dxdy = {
            {0,1},{0,-1},
            {1,0},{-1,0}
    };

    private static int puyoCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] row;

        for (int i = 0; i < 12; i++) {
            row = br.readLine().toCharArray();
            System.arraycopy(row, 0, board[i], 0, 6);
        }

        // 현재 보드에서 터질 수 있는 색이 존재하는지 확인.
        while (isPuyo()) {
            // 존재하는 경우 터뜨린 후, 터진 부분을 제거하면서, 보드 재구성.
            fallDownBoard();
            puyoCount++;
        }

        System.out.println(puyoCount);
    }

    private static boolean isPuyo() {
        boolean flag = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {

                if (board[i][j] == '.') {
                    continue;
                }

                // 현재 색에서 인접한 동일 색의 수를 구함.
                int sameColorCount = getSameColorCount(i, j);

                // 4개 이상인 경우 해 연결된 색을 떠뜨림.
                if (sameColorCount >= 4) {
                    boom(i, j, board[i][j]);
                    flag = true;
                }
            }
        }

        return flag;
    }

    private static int getSameColorCount(final int i, final int j) {
        Queue<Pair> queue = new LinkedList<>();
        int cnt = 1;
        queue.add(new Pair(i, j));
        boolean[][] visited = new boolean[12][6];
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();

            for (int[] move : dxdy) {
                int dx = poll.x + move[0];
                int dy = poll.y + move[1];

                if (dx < 0 || dy < 0 || dx >= 12 || dy >= 6 || visited[dx][dy]) {
                    continue;
                }


                // 연결된 색인 경우 Queue에 추가.
                if (board[dx][dy] == board[poll.x][poll.y]) {
                    cnt++;
                    queue.add(new Pair(dx, dy));
                    visited[dx][dy] = true;
                }
            }
        }

        return cnt;
    }

    private static void boom(final int x, final int y, final char color) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        board[x][y] = '.';

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();

            for (int[] move : dxdy) {
                int dx = poll.x + move[0];
                int dy = poll.y + move[1];

                if (dx < 0 || dy < 0 || dx >= 12 || dy >= 6) {
                    continue;
                }

                // 연결된 모든 색 터뜨림.
                if (board[dx][dy] == color) {
                    board[dx][dy] = '.';
                    queue.add(new Pair(dx, dy));
                }
            }
        }

    }

    // 색상이 모두 아래에 있도록 보드를 재구성.
    private static void fallDownBoard() {

        for(int y = 0 ; y < 6 ; ++y) {

            for(int x = 11 ; x >= 0 ; --x) {

                if(board[x][y] == '.') {

                    for(int nr = x - 1 ; nr >= 0 ; --nr) {

                        if(board[nr][y] != '.') {
                            board[x][y] = board[nr][y];
                            board[nr][y] = '.';
                            break;
                        }

                    }
                }
            }
        }
    }


    private static class Pair {
        int x;
        int y;

        public Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}