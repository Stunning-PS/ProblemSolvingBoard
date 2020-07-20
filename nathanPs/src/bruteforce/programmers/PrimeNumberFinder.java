package bruteforce.programmers;

import java.util.HashSet;

public class PrimeNumberFinder {

    public int solution(String numbers) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();

        permutation("", numbers, set);

        while (set.iterator().hasNext()) {
            int a = set.iterator().next();
            set.remove(a);

            if (a == 2) answer++;
            if (a % 2 != 0 && isPrime(a)) {
                answer++;
            }
        }
        return answer;
    }

    public void permutation(String prefix, String str, HashSet<Integer> set) {
        int n = str.length();

        if (!prefix.equals("")) {
            set.add(Integer.valueOf(prefix));
        }

        for (int i = 0; i < n; i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), set);
        }
    }

    public boolean isPrime(int num) {
        if (num <= 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); ++i) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        PrimeNumberFinder p = new PrimeNumberFinder();

        System.out.println(p.solution("17"));
    }
}