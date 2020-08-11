package greedy.leetcode;

public class Leetcode330 {

    public int minPatches(int[] nums, int n) {
        int count = 0;
        long priorSum = 0; // sum of elements prior to current index

        for (final int num : nums) {
            if (priorSum >= n) return count; // done
            while (priorSum < n && num > priorSum + 1) { // whenever there is a gap between priorSum and current value, add elements in a greedy manner
                ++count;
                priorSum = (priorSum << 1) + 1;
            }
            priorSum += num;
        }
        while(priorSum<n) {
            ++count;
            priorSum = (priorSum<<1) + 1;
        }
        return count;
    }
}
