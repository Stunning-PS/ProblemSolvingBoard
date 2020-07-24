package dp.programmers;

import java.util.Arrays;

public class CardGame {

    private static int[][] dp;

    public static int solution(int[] left, int[] right) {
        int ans = 0;
        dp = new int[left.length][right.length];

        for (int i = 0; i < left.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        ans = searchMaxSum(left, right, 0, 0);
        return ans;
    }

    private static int searchMaxSum(int[] left, int[] right, int leftLoc, int rightLoc) {
        // 종료 시점. (왼쪽 혹은 오른쪽 카드 모두 버려진 경우)
        if (leftLoc == left.length || rightLoc == right.length) {
            return 0;
        }

        // Memoization.(Overlapping 방지)
        if (dp[leftLoc][rightLoc] != -1) {
            return dp[leftLoc][rightLoc];
        }

        // 왼쪽 숫자가 오른쪽 숫자보다 큰 경우,
        if (left[leftLoc] > right[rightLoc]) {
            // 오른쪽 카드를 버린 후 현재 오른쪽 카드 숫자만큼 점수 획득.
            int currentSum = searchMaxSum(left, right, leftLoc, rightLoc+1) + right[rightLoc];

            // 오른쪽 카드 버린 후 현재까지 최대 점수 저장.
            dp[leftLoc][rightLoc] = currentSum;
            return currentSum;
        } else {
            // 왼쪽 혹은 양쪽 모두 버리는 경우를 고려해, 두 경우 중 최대 점수를 반환.
            int currentSum = Math.max(searchMaxSum(left, right, leftLoc + 1, rightLoc + 1),
                    searchMaxSum(left, right, leftLoc+1, rightLoc));
            // 두 경우 중 최대 점수를 저장.
            dp[leftLoc][rightLoc] = currentSum;
            return currentSum;
        }
    }

}


class SolutionBottomUp {
    public int solution(int[] left, int[] right) {
        int[][] dp = new int[left.length + 1][right.length + 1];

        for(int i = 1; i < left.length + 1; i++) {
            for(int j = 1; j < right.length + 1; j++) {
                // 왼쪽이 오른쪽보다 큰 경우.
                if(left[left.length - i] > right[right.length - j]) {
                    // 오른쪽을 버린 후 오른쪽 점수 획득.
                    dp[i][j] = dp[i][right.length-j];
                }else {
                    // 왼쪽만 버린 경우, 양쪽 모두 버린 경우 중 큰 점수를 저장.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[left.length][right.length];
    }
}