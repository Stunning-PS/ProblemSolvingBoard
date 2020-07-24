package dp.programmers;

public class Tile {

    private static long[] sideLength;

    public static long solution(int N) {
        sideLength = new long[N];
        sideLength[0] = 1;
        sideLength[1] = 1;
        for (int i = 2; i < N; i++) {
            sideLength[i] = sideLength[i-2] + sideLength[i-1];
        }
        return 4*sideLength[N-1] + 2*(sideLength[N-2]);
    }

    public static void main(String[] args) {
        System.out.println(solution(6));
    }
}
