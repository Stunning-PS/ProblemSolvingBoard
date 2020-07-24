package bruteforce.programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseballGame {

    public int solution(int[][] baseball) {
        List<String> numbers = IntStream.range(111, 1000)
                .boxed()
                .map(num -> Integer.toString(num))
                .filter(num -> !num.contains("0") && new HashSet<>(Arrays.asList(num.split(""))).size() == 3)
                .collect(Collectors.toList());


        // 가능한 모든 숫자를 만들고, 입력된 숫자 야구 결과로 나오지 못하는 숫자를 제거.
        for (int[] balls : baseball) {
            String targetNum = Integer.toString(balls[0]);
            int strike = balls[1];
            int ball = balls[2];

            numbers.removeIf(compareNum -> !isPass(compareNum, targetNum, strike, ball));
        }

        return numbers.size();
    }

    public boolean isPass(String compareNum, String targetNum, int strikeNum, int ballNum) {
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < compareNum.length(); i++) {
            if (compareNum.charAt(i) == targetNum.charAt(i)) {
                strike++;
            } else if (compareNum.charAt(i) != targetNum.charAt(i) && targetNum.contains(Character.toString(compareNum.charAt(i)))) {
                ball++;
            }
        }

        return strike == strikeNum && ball == ballNum;
    }

}