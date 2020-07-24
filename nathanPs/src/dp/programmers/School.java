package dp.programmers;

public class School {

    private boolean[][] isPool;

    private int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] paddles) {
        // d[x][y] = x, y에 올 수 있는 경로의 개수. (d[x-1][y], d[x][y-1]에서 올 수 있음)
        isPool = new boolean[m][n];
        int[][] dp = new int[m][n];

        dp[0][0] = 1;
        isPool[0][0] = true;

        for (int[] paddle : paddles) {
            isPool[paddle[0]][paddle[1]] = true;
        }


        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // 웅덩이인 경우 올 수 있는 경로 0
                if (isPool[i][j]) {
                    continue;
                }


                // 왼쪽에서 올 경우
                if (j >= 1) {
                    dp[i][j] += (dp[i][j - 1]) % MOD;
                }

                // 위에서 올 경우
                if (i >= 1) {
                    dp[i][j] += dp[i - 1][j] % MOD;
                }

            }
        }

        return dp[m-1][n-1] % MOD;
    }

    public static void main(String[] args) {
        School school = new School();

        int solution = school.solution(4, 3, new int[][]{{2, 2}});
        System.out.println(solution);
    }
}