package greedy.leetcode;

public class Leetcode55 {

    public boolean canJump(int[] nums) {
        int len = nums.length;
        int max = 0;

        if (len == 1) {
            return true;
        }

        for (int i = 0; i < len; i++) {
            int value = i + nums[i];
            max = Math.max(value, max);

            if (max >= len - 1) {
                return true;
            } else if (max < i + 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Leetcode55 leetcode55 = new Leetcode55();
        int[] nums = new int[] { 0, 2, 3};
        boolean b = leetcode55.canJump(nums);
        System.out.println(b);
    }
}
