package bruteforce.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon14500 {

    private static int n;
    private static int m;
    private static int maxSum = Integer.MIN_VALUE;
    private static int[][] board;
    private static int[][][] tetromino = new int[][][] {
            {{0, 1}, {0, 2}, {0, 3}},
            {{0, 1}, {1, 0}, {1, 1}},
            {{1, 0}, {2, 0}, {2, 1}},
            {{1, 0}, {1, 1}, {2, 1}},
            {{0, 1}, {0, 2}, {1, 1}},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(row[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                fillBoard(i, j);
            }
        }

        System.out.println(maxSum);

    }

    private static void fillBoard(final int i, final int j) {
        for (int[][] shape : tetromino) {

            rotateSum(i, j, shape);
            symmetryShape(shape);
            rotateSum(i, j, shape);
            symmetryShape(shape);
        }
    }

    private static void rotateSum(final int i, final int j, final int[][] shape) {
        int sum;
        for (int k = 0; k < 4; k++) {
            sum = board[i][j];
            sum = findSum(i, j, shape, sum);

            if (maxSum < sum) {
                maxSum = sum;
            }

            rotateShape(shape);
        }

    }

    private static int findSum(final int i, final int j, final int[][] shape, int sum) {
        for (int[] dxdy : shape) {
            int dx = i + dxdy[0];
            int dy = j + dxdy[1];

            if (dx < 0 || dy < 0 || dx >= n || dy >= m) {
                sum = board[i][j];
                break;
            }

            sum += board[dx][dy];
        }
        return sum;
    }

    private static void rotateShape(int[][] shape) {
        for (int[] dxdy : shape) {
            int tempX = dxdy[0];
            int tempY = dxdy[1];
            dxdy[0] = tempY * -1;
            dxdy[1] = tempX;
        }
    }

    private static void symmetryShape(int[][] shape) {
        for (int[] dxdy : shape) {
            dxdy[1] = dxdy[1] * -1;
        }
    }
}