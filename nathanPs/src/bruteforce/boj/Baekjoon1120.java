package bruteforce.boj;

import java.util.Scanner;

public class Baekjoon1120 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String A = input[0];
        String B = input[1];
        int diffLength = B.length() - A.length();
        int minDiffLength = A.length();

        for (int i = 0; i <= diffLength; i++) {
            int cnt = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j) != B.charAt(j + i)) {
                    cnt++;
                }
            }

            if (minDiffLength > cnt) {
                minDiffLength = cnt;
            }
        }
        System.out.println(minDiffLength);
    }


}