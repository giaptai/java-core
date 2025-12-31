package leetcode;

public class BinarySearch {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int m = left + (right - left) / 2;
            if (nums[m] > target) {
                right = m - 1;
            } else if (nums[m] < target) {
                left = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] case1 = { -1, 0, 2, 4, 6, 8 };
        int[] case2 = { -1, 0, 2, 4, 6, 8 };
        System.out.println(search(case1, 4));
        System.out.println(search(case2, 3));
    }
}
