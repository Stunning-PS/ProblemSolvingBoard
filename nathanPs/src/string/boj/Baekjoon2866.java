package string.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class Baekjoon2866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = br.readLine().split(" ");
        int r = Integer.parseInt(rc[0]);
        int c = Integer.parseInt(rc[1]);
        char[][] table = new char[r][c];

        // table 입력 받는 코드
        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); j++) {
                table[i][j] = row.charAt(j);
            }
        }

        // 각 열로 구성된 문자열을 담는 Set
        Set<String> stringSet = new LinkedHashSet<>();

        for (int i = 0; i < c; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < r; j++) {
                sb.append(table[j][i]);
            }
            stringSet.add(sb.toString());
        }

        int ans = 0;

        // 각 열로 구성된 문자열을 담은 Set을 순회하면서 가장 위 행을 제거하면서 중복 문자열 존재 여부 판별.
        // 1 ~ r-1번째까지
        for (int i = 1; i < r; i++) {
            Set<String> subSet = new LinkedHashSet<>();

            // 각 문자열의 index위치가 입력받은 table 행의 위치이기 때문에, substring을 활용해 현재 최상위 열 제거.
            for (String s : stringSet) {
                String substring = s.substring(i, s.length());
                subSet.add(substring);
            }

            if (subSet.size() < c) {
                break;
            } else {
                ans++;
            }
        }

        System.out.println(ans);
    }
}