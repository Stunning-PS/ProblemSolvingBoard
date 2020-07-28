package bfsdfs.programmers;

import java.util.Arrays;
import java.util.List;

public class ConvertWord {

    private int minCnt = Integer.MAX_VALUE;

    private boolean[] visited;

    public int solution(String begin, String target, String[] words) {
        List<String> wordList = Arrays.asList(words);
        visited = new boolean[words.length];

        // Target 단어가 words에 존재하지 않으면 0.
        if (!wordList.contains(target)) {
            return 0;
        }

        // DFS 함수 시작.
        dfs(begin, target, words, 0);
        return minCnt;
    }

    private void dfs(final String prevWord, final String target, final String[] words, int cnt) {

        // 기저. 이전 단어가 타켓 단어인 경우 종료.
        if (prevWord.equals(target)) {

            // 종료시마다 최소 변환 횟수 업데이트.
            minCnt = Math.min(minCnt, cnt);
            return;
        }


        // 변환 가능한 단어를 순회하면서, 재귀 동작.
        for (int i = 0; i < words.length; i++) {
            String nextWord = words[i];

            // 단어의 알파벳 차이가 1인 경우에만 변환 가능.
            if (isDiffCountOne(prevWord, nextWord)) {

                // 이전에 방문하지 않은 경우만 탐색 진행.
                if (!visited[i]) {
                    visited[i] = true;
                    cnt++;
                    // 변환된 단어를 prevWord에 넣고, 변환 횟수 증가.
                    dfs(nextWord, target, words, cnt);

                    // 재귀가 끝난 경우, 방문 여부와 변환 횟수 되돌리기.
                    cnt--;
                    visited[i] = false;
                }
            }
        }
    }

    private boolean isDiffCountOne(final String prevWord, final String convertWord) {
        int diffCount = 0;
        for (int i = 0; i < prevWord.length(); i++) {
            if (prevWord.charAt(i) != convertWord.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount == 1;
    }

    public static void main(String[] args) {
        ConvertWord convertWord = new ConvertWord();
        String begin = "hit";
        String end = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        int solution = convertWord.solution(begin, end, words);
        System.out.println(solution);
    }
}
