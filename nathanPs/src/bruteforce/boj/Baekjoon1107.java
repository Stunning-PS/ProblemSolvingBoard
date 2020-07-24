package bruteforce.boj;

import java.util.Scanner;

public class Baekjoon1107 {

    private static boolean[] brokenChannel = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int targetChannel = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());
        int minClickCount = Math.abs(targetChannel - 100);

        for (int i = 0; i < m; i++) {
            int channel = sc.nextInt();
            brokenChannel[channel] = true;
        }

        for (int channel = 0; channel <= 1_000_000; channel++) {
            int possibleLen = possibleLen(channel);

            if (possibleLen > 0) {
                int switchCount = possibleLen + Math.abs(channel - targetChannel);

                if (switchCount < minClickCount) {
                    minClickCount = switchCount;
                }
            }
        }


        System.out.println(minClickCount);
    }


    private static int possibleLen(int num) {
        if (num == 0) {
            if (brokenChannel[0]) {
                return 0;
            } else {
                return 1;
            }
        }

        int cnt = 0;

        while (num > 0) {
            if (brokenChannel[num % 10]) {
                return 0;
            }

            cnt++;
            num = num / 10;

        }

        return cnt;
    }
}