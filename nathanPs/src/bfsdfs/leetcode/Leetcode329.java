package bfsdfs.leetcode;

public class Leetcode329 {

    private int[][] dp;

    private int r;

    private int c;

    private int[][] matrix;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        r = matrix.length;
        c = matrix[0].length;
        this.matrix = matrix;

        dp = new int[r][c];

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // 각 matrix 위치를 시작점으로 탐색 진행한 후 증가 경로의 길이를 업데이트.
                ans = Math.max(ans, dfs(i, j, -1));
            }
        }

        return ans;
    }

    private int dfs(final int x, final int y, final int prevNum) {
        // 기저 사례. 배열 범위 초과 & 이전 번호가 현재 번호보다 크거나 같은 경우,
        if (x < 0 || y < 0 || x >= r || y >= c || prevNum >= matrix[x][y]) {
            return 0;
        }

        // memoization
        if (dp[x][y] > 0) {
            return dp[x][y];
        }

        // 상하좌우 탐색하면서 비교.
        int len = dfs(x, y+1, matrix[x][y]);
        len = Math.max(len, dfs(x, y-1, matrix[x][y]));
        len = Math.max(len, dfs(x+1, y, matrix[x][y]));
        len = Math.max(len, dfs(x-1, y, matrix[x][y]));

        // 경로 증가.
        dp[x][y] = len + 1;

        return dp[x][y];
    }

    public static void main(String[] args) {
        Leetcode329 leetcode329 = new Leetcode329();
        int[][] nums = new int[][] {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };

        int i = leetcode329.longestIncreasingPath(nums);
        System.out.println(i);
    }
}