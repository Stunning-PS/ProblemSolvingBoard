package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1080 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");

        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        char[][] aMatrix = new char[n][m];
        char[][] bMatrix = new char[n][m];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                aMatrix[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                bMatrix[i][j] = row.charAt(j);
            }
        }

        int count = 0;

        for (int i = 0; i < n - 2; i++) {

            for (int j = 0; j < m - 2; j++) {
                if (aMatrix[i][j] != bMatrix[i][j]) {
                    for (int k = i; k < i + 3; k++) {

                        for (int l = j; l < j + 3; l++) {
                            if (aMatrix[k][l] == '1') {
                                aMatrix[k][l] = '0';
                            } else if (aMatrix[k][l] == '0') {
                                aMatrix[k][l] = '1';
                            }
                        }
                    }
                    count++;
                }
            }
        }

        if (!isSame(aMatrix, bMatrix)) {
            count = -1;
        }

        System.out.println(count);
    }

    private static boolean isSame(final char[][] aMatrix, final char[][] bMatrix) {
        for (int i = 0; i < aMatrix.length; i++) {
            for (int j = 0; j < aMatrix[0].length; j++) {
                if (aMatrix[i][j] != bMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}