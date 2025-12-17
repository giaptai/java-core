package leetcode;

import java.util.HashSet;
import java.util.Set;

public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int result = 0;

        // sum from 0 to n;
        // for (int i = 0; i < nums.length + 1; i++) { // O(n)
        // result += i;
        // }

        // or math formula
        result = nums.length * (nums.length + 1) / 2;

        // sum from original nums
        for (int j = 0; j < nums.length; j++) { // O(n)
            result -= nums[j];
        }
        return result;
    }

    // solution 2 XOR
    public static int missingNumber2(int[] nums) {
        int result = nums.length; // Bắt đầu từ n

        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i]; // XOR với cả index và value
        }

        return result;
    }

    // solution 3 HashSet
    public static int missingNumber3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] case1 = { 3, 0, 1 };
        int[] case2 = { 0, 1 };
        int[] case3 = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };

        System.out.println(missingNumber(case1));
        System.out.println(missingNumber(case2));
        System.out.println(missingNumber(case3));
    }
}