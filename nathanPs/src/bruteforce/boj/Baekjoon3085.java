package bruteforce.boj;

import java.util.Scanner;

public class Baekjoon3085 {

    private static char[][] board;

    private static int[][] dxdy = new int[][]{{0,1}, {1,0}};

    private static int maxNum = Integer.MIN_VALUE;

    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String row = sc.nextLine();
            for (int j = 0; j < row.length(); j++) {
                board[i][j] = row.charAt(j);
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                findMaxNum(i, j);
            }
        }

        System.out.println(maxNum);
    }

    public static void findMaxNum(final int x, final int y) {

        for (int i = 0; i < 2; i++) {
            int dx = x + dxdy[i][0];
            int dy = y + dxdy[i][1];

            // index 위치 넘어갈 시 처리.
            if (dx < 0 || dy < 0 || dx >= n || dy >= n) {
                continue;
            }

            // 인접 색상이 같은 경우.
            if (board[x][y] == board[dx][dy]) {
                continue;
            }

            swap(x, y, dx, dy);
            searchBoard();
            swap(dx, dy, x, y);
        }

    }

    private static void searchBoard() {
        for (int i = 0; i < n; i++) {
            int cnt = Math.max(findSameCandyInRow(i), findSameCandyInCol(i));

            if (maxNum < cnt) {
                maxNum = cnt;
            }
        }
    }

    private static int findSameCandyInRow(final int x) {
        int sameCount = 0;
        int maxSameCount = 0;

        for (int i = 0; i < n-1; i++) {
            if (board[x][i] == board[x][i+1]) {
                sameCount++;
            } else {
                if (maxSameCount < sameCount) {
                    maxSameCount = sameCount;
                }
                sameCount = 0;
            }

        }

        return Math.max(sameCount, maxSameCount) + 1;
    }

    private static int findSameCandyInCol(final int y) {
        int sameCount = 0;
        int maxSameCount = 0;

        for (int i = 0; i < n-1; i++) {
            if (board[i+1][y] == board[i][y]) {
                sameCount++;
            } else {
                if (maxSameCount < sameCount) {
                    maxSameCount = sameCount;
                }
                sameCount = 0;
            }
        }

        return Math.max(sameCount, maxSameCount) + 1;
    }

    public static void swap(int x, int y, int dx, int dy) {
        char temp = board[x][y];
        board[x][y] = board[dx][dy];
        board[dx][dy] = temp;
    }
}