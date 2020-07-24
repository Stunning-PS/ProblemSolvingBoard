package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2662 {

    private static int[][] dp = new int[301][21];
    private static int[][] fr = new int[301][21];

    private static int[][] companyList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        companyList = new int[301][21];

        for (int i = 1; i <= n; i++) {
            String[] row = br.readLine().split(" ");

            for (int j = 0; j <= m; j++) {
                companyList[i][j] = Integer.parseInt(row[j]);
            }

        }

        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                for (int k = 0; k <= j; k++) {
                    if (dp[j][i] < dp[j - k][i - 1] + companyList[k][i]) {
                        dp[j][i] = dp[j - k][i - 1] + companyList[k][i];
                        fr[j][i] = j - k;
                    }
                }
            }
        }

        System.out.println(dp[n][m]);
        f(n, m);
        System.out.println();
    }

    private static void f(final int n, final int m) {
        if (m == 0) {
            return;
        }

        f(fr[n][m], m - 1);
        System.out.print(n - fr[n][m] + " ");
    }
}