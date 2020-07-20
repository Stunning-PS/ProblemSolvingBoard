package dp.boj;

import java.util.Scanner;

public class Baekjoon1254 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int len = s.length();

        // 0~주어진 문자열 길이만큼 순회
        for (int i = 0; i < len; i++) {
            String substring = s.substring(i);
            // 부분 문자열이 필랜드롬 조건을 만족하는지 판별.
            if (isPhilandrome(substring)) {
                // 만족하면 주어진 문자열에서, slicing한 크기만큼이 최종 필랜드롬의 문자열 크기.
                System.out.println(len + i);
                return;
            }
        }

        // 최악의 경우 짝수일때는 (주어진 문자열 길이 * 2), 홀수일때는 (주어진 문자열 길이 * 2 - 1)
        System.out.println(len % 2 == 0 ? len * 2: len * 2 - 1);
    }

    public static boolean isPhilandrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
