package greedy.programmers;

import java.util.*;

public class Camera {

    public int solution(int[][] routes) {
        int ans = 0;

        Arrays.sort(routes, (r1, r2) -> Integer.compare(r1[1], r2[1]));

        int camera = -30_001;

        for (int[] route : routes) {
            if (camera < route[0]) {
                camera = route[1];
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Camera camera = new Camera();
        int[][] routes = new int[][] {
                {-20,15}, {-14,-5}, {-18,-13}, {-5,-3}
        };
        int solution = camera.solution(routes);
        System.out.println(solution);
    }


}