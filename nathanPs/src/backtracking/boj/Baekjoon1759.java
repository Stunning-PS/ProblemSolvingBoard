package backtracking.boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Baekjoon1759 {

    private static int l;

    private static int c;

    private static String[] alphabets;

    // 방문 여부 체크하는 배열 생성.
    private static boolean[] visited;

    private static char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};

    // 방문한 알파벳을 담고 있는 StringBuilder
    private static StringBuilder chosenChar = new StringBuilder();

    // 가능한 알파벳 조합을 가지고 있는 배열.
    private static List<String> answer = new ArrayList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] lc = sc.nextLine().split(" ");
        l = Integer.parseInt(lc[0]);
        c = Integer.parseInt(lc[1]);
        alphabets = new String[c];
        visited = new boolean[c];

        for (int i = 0; i < c; i++) {
            alphabets[i] = sc.next();
        }

        Arrays.sort(alphabets);

        backTracking(0, 0);

        for (String s : answer) {
            System.out.println(s);
        }

    }

    private static void backTracking(int idx, final int cnt) {
        // 방문한 알파벳이 l개이면 종료.
        if (cnt == l) {
            // 방문한 알파벳이 최소 한 개의 자음, 두 개의 모음을 가지고 있는지 확인.
            if (isPossible()) {
                answer.add(chosenChar.toString());
            }
            return;
        }

        // 정렬된 문자열로 구성해야하기 때문에 idx를 재귀 호출마다 증가할 수 있도록 설계.
        for (int i = idx; i < alphabets.length; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            chosenChar.append(alphabets[i]);
            backTracking(i+1,cnt + 1);
            visited[i] = false;
            chosenChar.delete(chosenChar.length()-1, chosenChar.length());
        }
    }

    // 현재 만들어진 암호가 최소 모음 한 개, 자음 두 개가 존재하는지 확인.
    private static boolean isPossible() {
        int vowelsCount = 0;
        int consCount = 0;

        for (int i = 0; i < chosenChar.length(); i++) {
            if (isVowel(chosenChar.charAt(i))) {
                vowelsCount++;
            } else {
                consCount++;
            }
        }

        return vowelsCount >= 1 && consCount >= 2;
    }

    private static boolean isVowel(final char c) {
        for (char vowel : vowels) {
            if (vowel == c) {
                return true;
            }
        }
        return false;
    }
}