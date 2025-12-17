package leetcode;

import java.util.Arrays;

public class TwoIntegerSumII {
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        result[0] = left + 1;
        result[1] = right + 1;
        return result;
    }

    public static void main(String[] args) {
        int[] case1 = { 2, 7, 11, 15 };
        int[] case2 = { 2, 3, 4 };
        int[] case3 = { -1, 0 };
        System.out.println(Arrays.toString(twoSum(case1, 9)));
        System.out.println(Arrays.toString(twoSum(case2, 6)));
        System.out.println(Arrays.toString(twoSum(case3, -1)));
    }
}
