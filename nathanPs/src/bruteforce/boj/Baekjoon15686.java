package bruteforce.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Baekjoon15686 {

    private static int n;

    private static int m;

    private static int minDistance = Integer.MAX_VALUE;

    private static List<Location> home = new ArrayList<>();
    private static List<Location> chicken = new ArrayList<>();
    private static Stack<Location> visitedChicken = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        for (int i = 0; i < n; i++) {
            String[] rows = br.readLine().split(" ");

            for (int j = 0; j < rows.length; j++) {
                int number = Integer.parseInt(rows[j]);
                if (number == 1) {
                    home.add(new Location(i, j));
                } else if (number == 2) {
                    chicken.add(new Location(i, j));
                }
            }
        }
        br.close();

        searchMinDistance(0, 0);
        System.out.println(minDistance);
    }

    private static void searchMinDistance(int start, int cnt) {
        if (cnt == m) {
            calMinDistance();
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            visitedChicken.add(chicken.get(i));
            searchMinDistance(i + 1, cnt + 1);
            visitedChicken.pop();
        }
    }

    private static void calMinDistance() {
        int totalDistance = 0;

        for (Location homeLoc : home) {

            int min = Integer.MAX_VALUE;
            for (Location chickenDistance : visitedChicken) {
                int distance = getDistance(homeLoc.x, homeLoc.y, chickenDistance.x, chickenDistance.y);
                min = Math.min(min, distance);
            }

            totalDistance += min;
        }

        minDistance = Math.min(minDistance, totalDistance);
    }


    public static int getDistance(int x, int y, int dx, int dy) {
        return Math.abs(x - dx)  + Math.abs(y - dy);
    }

    public static class Location {
        int x;
        int y;

        public Location(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

    }
}